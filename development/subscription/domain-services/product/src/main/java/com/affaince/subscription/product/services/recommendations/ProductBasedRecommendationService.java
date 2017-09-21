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

    public List<ProductForecastTrendView> recommendIncrementalPurchaseBudget(String productId) {
        //first obtain latest active forecast
        //then obtain immediate previous forecast
        //lastly obtain current actuals
        List<ProductForecastView> activeProductForecastList = productForecastViewRepository.findByProductVersionId_ProductIdAndForecastContentStatusOrderByForecastDateDesc(productId, ForecastContentStatus.ACTIVE);
        List<ProductForecastView> expiredForecastList = productForecastViewRepository.findByProductVersionId_ProductIdAndForecastContentStatusOrderByForecastDateDesc(productId, ForecastContentStatus.EXPIRED);
        LocalDate referenceForecastDate = expiredForecastList.get(0).getForecastDate();
        List<ProductForecastView> latestExpiredForecastList = expiredForecastList.stream().filter(forecast -> forecast.getForecastDate().equals(referenceForecastDate)).collect(Collectors.toList());
        LocalDate dateOfComparison = SysDate.now();
        List<ProductForecastTrendView> changeInSubscriptionCountPerPeriod = new ArrayList<>();
        //find if aggregation period is weekly monthly or quarterly
        int aggregationPeriod = productConfigurationViewRepository.findOne(productId).getActualsAggregationPeriodForTargetForecast();

        int recordsForComparision = determineNumberOfRecordsTobeCompared(aggregationPeriod, dateOfComparison);
        if(activeProductForecastList.size()>=recordsForComparision && latestExpiredForecastList.size()>=recordsForComparision)
        for (int i = 0; i < recordsForComparision; i++) {
            ProductForecastView activeForecast = activeProductForecastList.get(i);
            ProductForecastView expiredForecast = latestExpiredForecastList.get(i);
            if (activeForecast.getProductVersionId().getFromDate().equals(expiredForecast.getProductVersionId().getFromDate())) {
                long trendChange = activeForecast.getTotalNumberOfExistingSubscriptions() - expiredForecast.getTotalNumberOfExistingSubscriptions();
                ProductForecastTrendView trend = new ProductForecastTrendView(productId, dateOfComparison, activeForecast.getProductVersionId().getFromDate(), activeForecast.getEndDate(), trendChange);
                changeInSubscriptionCountPerPeriod.set(i, trend);
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
