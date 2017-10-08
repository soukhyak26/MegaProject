package com.affaince.subscription.product.services.trend;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastTrendViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.query.view.ProductForecastTrendView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mandar on 9/21/2017.
 */
@Component
public class ProductTrendChangeDetector {
    @Autowired
    ProductForecastViewRepository productForecastViewRepository;
    @Autowired
    ProductConfigurationViewRepository productConfigurationViewRepository;
    @Autowired
    ProductForecastTrendViewRepository productForecastTrendViewRepository;

    public List<ProductForecastTrendView> determineTrendChange(String productId, EntityMetadata entityMetadata) {
        //first obtain latest active forecast
        //then obtain immediate previous forecast
        //compare active forecast with previous forecast and determine if there is a change in trend.
        //in case trend change is more than contingency stock then it needs additional demand for stock and budget required for the same.
        LocalDate dateOfComparison = SysDate.now();
        List<ProductForecastTrendView> changeInSubscriptionCountPerPeriod = new ArrayList<>();
        ProductConfigurationView configView = productConfigurationViewRepository.findOne(productId);
        int aggregationPeriod = configView.getActualsAggregationPeriodForTargetForecast();
        double contingencyStockPercentage = configView.getContingencyStockPercentage();
        int recordsForComparision = determineNumberOfRecordsTobeCompared(aggregationPeriod, dateOfComparison);
        EntityMetricType entityMetricType = null;
        Map<String, Object> namedMetadata = entityMetadata.getNamedEntries();
        for (String s : namedMetadata.keySet()) {
            switch (s) {
                case "ENTITY_METRIC_TYPE":
                    entityMetricType = (EntityMetricType) namedMetadata.get(s);
                    break;
            }
        }

        List<ProductForecastView> activeProductForecastList = productForecastViewRepository.findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastDateDesc(productId, ForecastContentStatus.ACTIVE);
        List<ProductForecastView> expiredForecastList = productForecastViewRepository.findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastDateDesc(productId, ForecastContentStatus.EXPIRED);
        //for the first time of forecast there is no expired forecast
        if (null == expiredForecastList && expiredForecastList.isEmpty()) {
            for (int i = 0; i < activeProductForecastList.size(); i++) {
                ProductForecastView activeForecast = activeProductForecastList.get(i);

                ForecastVersionId forecastVersionId = new ForecastVersionId(productId, dateOfComparison, activeForecast.getProductVersionId().getFromDate());
                ProductForecastTrendView trend = productForecastTrendViewRepository.findOne(forecastVersionId);
                if (null == trend) {
                    trend = new ProductForecastTrendView(productId, dateOfComparison, activeForecast.getProductVersionId().getFromDate(), activeForecast.getEndDate());
                }
                if (entityMetricType == EntityMetricType.TOTAL) {
                    trend.setReferenceTotalSubscriptionCount(activeForecast.getTotalNumberOfExistingSubscriptions());
                    trend.setChangeInTotalSusbcriptionCount(Double.MAX_VALUE);
                } else if (entityMetricType == EntityMetricType.NEW) {
                    long trendChangeInNewCount = activeForecast.getNewSubscriptions();
                    trend.setReferenceNewSubscriptionCount(activeForecast.getNewSubscriptions());
                    trend.setChangeInNewSubscriptionCount(Double.MAX_VALUE);
                } else if (entityMetricType == EntityMetricType.CHURN) {
                    trend.setReferenceChurnedSubscriptionCount(activeForecast.getChurnedSubscriptions());
                    trend.setChangeInChurnedSubscriptionCount(Double.MAX_VALUE);
                }
                changeInSubscriptionCountPerPeriod.set(i, trend);
            }
        }else {
            LocalDate referenceForecastDate = expiredForecastList.get(0).getForecastDate();
            List<ProductForecastView> latestExpiredForecastList = expiredForecastList.stream().filter(forecast -> forecast.getForecastDate().equals(referenceForecastDate)).collect(Collectors.toList());
            //find if aggregation period is weekly monthly or quarterly

            if (activeProductForecastList.size() >= recordsForComparision && latestExpiredForecastList.size() >= recordsForComparision) {
                for (int i = 0; i < recordsForComparision; i++) {
                    ProductForecastView activeForecast = activeProductForecastList.get(i);
                    for (int j = 0; j < recordsForComparision; j++) {
                        ProductForecastView expiredForecast = latestExpiredForecastList.get(j);
                        if (activeForecast.getProductVersionId().getFromDate().equals(expiredForecast.getProductVersionId().getFromDate())) {
                            ForecastVersionId forecastVersionId = new ForecastVersionId(productId, dateOfComparison, activeForecast.getProductVersionId().getFromDate());
                            ProductForecastTrendView trend = productForecastTrendViewRepository.findOne(forecastVersionId);
                            if (null == trend) {
                                trend = new ProductForecastTrendView(productId, dateOfComparison, activeForecast.getProductVersionId().getFromDate(), activeForecast.getEndDate());
                            }
                            if (entityMetricType == EntityMetricType.TOTAL) {
                                long trendChangeInTotalCount = activeForecast.getTotalNumberOfExistingSubscriptions() - expiredForecast.getTotalNumberOfExistingSubscriptions();
                                trend.setReferenceTotalSubscriptionCount(expiredForecast.getTotalNumberOfExistingSubscriptions());
                                trend.setChangeInTotalSusbcriptionCount(trendChangeInTotalCount / expiredForecast.getTotalNumberOfExistingSubscriptions());
                            } else if (entityMetricType == EntityMetricType.NEW) {
                                long trendChangeInNewCount = activeForecast.getNewSubscriptions() - expiredForecast.getNewSubscriptions();
                                trend.setReferenceNewSubscriptionCount(expiredForecast.getNewSubscriptions());
                                trend.setChangeInNewSubscriptionCount(trendChangeInNewCount / expiredForecast.getNewSubscriptions());
                            } else if (entityMetricType == EntityMetricType.CHURN) {
                                long trendChangeInChurnedCount = activeForecast.getChurnedSubscriptions() - expiredForecast.getChurnedSubscriptions();
                                trend.setReferenceChurnedSubscriptionCount(expiredForecast.getChurnedSubscriptions());
                                trend.setChangeInChurnedSubscriptionCount(trendChangeInChurnedCount / expiredForecast.getChurnedSubscriptions());
                            }
                            changeInSubscriptionCountPerPeriod.set(i, trend);
                            break;
                        }
                    }
                }
            }
        }
        productForecastTrendViewRepository.save(changeInSubscriptionCountPerPeriod);
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
