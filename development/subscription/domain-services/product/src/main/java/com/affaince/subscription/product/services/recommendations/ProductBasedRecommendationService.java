package com.affaince.subscription.product.services.recommendations;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.query.view.ProductForecastTrendView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 9/21/2017.
 */
public class ProductBasedRecommendationService {
    @Autowired
    ProductForecastViewRepository productForecastViewRepository;
    @Autowired
    ProductConfigurationViewRepository productConfigurationViewRepository;

    public List<ProductForecastTrendView> determineTrendChange(String productId) {
        //first obtain latest active forecast
        //then obtain immediate previous forecast
        //compare active forecast with previous forecast and determine if there is a change in trend.
        //in case trend change is more than contingency stock then it needs additional demand for stock and budget required for the same.
        List<ProductForecastView> activeProductForecastList = productForecastViewRepository.findByProductVersionId_ProductIdAndForecastContentStatusOrderByForecastDateDesc(productId, ForecastContentStatus.ACTIVE);
        List<ProductForecastView> expiredForecastList = productForecastViewRepository.findByProductVersionId_ProductIdAndForecastContentStatusOrderByForecastDateDesc(productId, ForecastContentStatus.EXPIRED);
        LocalDate referenceForecastDate = expiredForecastList.get(0).getForecastDate();
        List<ProductForecastView> latestExpiredForecastList = expiredForecastList.stream().filter(forecast -> forecast.getForecastDate().equals(referenceForecastDate)).collect(Collectors.toList());
        LocalDate dateOfComparison = SysDate.now();
        List<ProductForecastTrendView> changeInSubscriptionCountPerPeriod = new ArrayList<>();
        //find if aggregation period is weekly monthly or quarterly
        ProductConfigurationView configView=productConfigurationViewRepository.findOne(productId);
        int aggregationPeriod = configView.getActualsAggregationPeriodForTargetForecast();
        double contingencyStockPercentage=configView.getContingencyStockPercentage();

        int recordsForComparision = determineNumberOfRecordsTobeCompared(aggregationPeriod, dateOfComparison);
        if(activeProductForecastList.size()>=recordsForComparision && latestExpiredForecastList.size()>=recordsForComparision)
        for (int i = 0; i < recordsForComparision; i++) {
            ProductForecastView activeForecast = activeProductForecastList.get(i);
            ProductForecastView expiredForecast = latestExpiredForecastList.get(i);
            if (activeForecast.getProductVersionId().getFromDate().equals(expiredForecast.getProductVersionId().getFromDate())) {
                long trendChange = activeForecast.getTotalNumberOfExistingSubscriptions() - expiredForecast.getTotalNumberOfExistingSubscriptions();
                //If change of trend(visible in active forecast) is more than contingency stock percent limit,it means additional demand needs to be raised.
                if((trendChange/activeForecast.getTotalNumberOfExistingSubscriptions())>contingencyStockPercentage) {
                    ProductForecastTrendView trend = new ProductForecastTrendView(productId, dateOfComparison, activeForecast.getProductVersionId().getFromDate(), activeForecast.getEndDate(), trendChange);
                    changeInSubscriptionCountPerPeriod.set(i, trend);
                }
            }
        }
        return changeInSubscriptionCountPerPeriod;
    }

    private int determineNumberOfRecordsTobeCompared(int aggregationPeriod, LocalDate dateOfComparision) {
        int monthOfYear = -1;
        switch (aggregationPeriod) {
            case 7:
                return dateOfComparision.withYear(dateOfComparision.getYear()).weekOfWeekyear().getMaximumValue() - dateOfComparision.getWeekOfWeekyear();
            case 30:
                monthOfYear = dateOfComparision.getMonthOfYear();
                return 12 - monthOfYear + 1;
            case 90:
                monthOfYear = dateOfComparision.getMonthOfYear();
                return 4 - (monthOfYear / 3 + 1);
        }
        return -1;
    }
}
