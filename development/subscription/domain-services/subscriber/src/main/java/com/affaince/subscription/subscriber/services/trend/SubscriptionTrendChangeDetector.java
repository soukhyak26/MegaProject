package com.affaince.subscription.subscriber.services.trend;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.query.repository.SubscriptionForecastTrendViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionForecastViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionForecastTrendView;
import com.affaince.subscription.subscriber.query.view.SubscriptionForecastView;
import com.affaince.subscription.subscriber.query.view.SubscriptionRuleView;
import com.affaince.subscription.subscriber.vo.SubscriptionVersionId;
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
public class SubscriptionTrendChangeDetector {

    @Autowired
    SubscriptionForecastViewRepository subscriptionForecastViewRepository;
    @Autowired
    SubscriptionRuleViewRepository subscriptionRuleViewRepository;
    @Autowired
    SubscriptionForecastTrendViewRepository subscriptionForecastTrendViewRepository;

    public List<SubscriptionForecastTrendView> determineTrendChange(String id, EntityMetadata entityMetadata) {
        EntityMetricType entityMetricType = null;
        double minValue = 0;
        double maxValue = 0;
        Map<String, Object> namedMetadata = entityMetadata.getNamedEntries();
        for (String s : namedMetadata.keySet()) {
            switch (s) {
                case "ENTITY_METRIC_TYPE":
                    entityMetricType = (EntityMetricType) namedMetadata.get(s);
                    break;
                case "MIN_WEIGHT":
                    minValue = Double.valueOf((Double) namedMetadata.get(s)).doubleValue();
                    break;
                case "MAX_WEIGHT":
                    maxValue = Double.valueOf((Double) namedMetadata.get(s)).doubleValue();
                    break;

            }
        }

        List<SubscriptionForecastView> activeProductForecastList = subscriptionForecastViewRepository.findByForecastContentStatusAndSubscriptionVersionId_ValueRangeMinGreaterThanEqualAndSubscriptionVersionId_ValueRangeMaxLessThanOrderBySubscriptionVersionId_ForecastDateDesc(ForecastContentStatus.ACTIVE, minValue, maxValue);
        List<SubscriptionForecastView> expiredForecastList = subscriptionForecastViewRepository.findByForecastContentStatusAndSubscriptionVersionId_ValueRangeMinGreaterThanEqualAndSubscriptionVersionId_ValueRangeMaxLessThanOrderBySubscriptionVersionId_ForecastDateDesc(ForecastContentStatus.EXPIRED, minValue, maxValue);
        LocalDate referenceForecastDate = expiredForecastList.get(0).getSubscriptionVersionId().getForecastDate();
        List<SubscriptionForecastView> latestExpiredForecastList = expiredForecastList.stream().filter(forecast -> forecast.getSubscriptionVersionId().getForecastDate().equals(referenceForecastDate)).collect(Collectors.toList());
        LocalDate dateOfComparison = SysDate.now();
        List<SubscriptionForecastTrendView> changeInSubscriptionCountPerPeriod = new ArrayList<>();
        //find if aggregation period is weekly monthly or quarterly
        //find if aggregation period is weekly monthly or quarterly
        SubscriptionRuleView configView = subscriptionRuleViewRepository.findAll().iterator().next();
        int aggregationPeriod = configView.getActualsAggregationPeriodForTargetForecast();
        double contingencyStockPercentage = configView.getContingencyStockPercentage();

        int recordsForComparision = determineNumberOfRecordsTobeCompared(aggregationPeriod, dateOfComparison);

        if (activeProductForecastList.size() >= recordsForComparision && latestExpiredForecastList.size() >= recordsForComparision)
            for (int i = 0; i < recordsForComparision; i++) {
                SubscriptionForecastView activeForecast = activeProductForecastList.get(i);
                for (int j = 0; j < recordsForComparision; j++) {
                    SubscriptionForecastView expiredForecast = latestExpiredForecastList.get(j);
                    if (activeForecast.getSubscriptionVersionId().getStartDate().equals(expiredForecast.getSubscriptionVersionId().getStartDate())) {
                        SubscriptionVersionId subscriptionVersionId = new SubscriptionVersionId(dateOfComparison, activeForecast.getSubscriptionVersionId().getStartDate(), minValue, maxValue);
                        SubscriptionForecastTrendView trend = subscriptionForecastTrendViewRepository.findOne(subscriptionVersionId);
                        if (null == trend) {
                            trend = new SubscriptionForecastTrendView(dateOfComparison, activeForecast.getSubscriptionVersionId().getStartDate(), activeForecast.getEndDate(), minValue, maxValue);
                        }
                        if (entityMetricType == EntityMetricType.TOTAL) {
                            double trendChangeInTotalCount = (activeForecast.getTotalSubscriptions() - expiredForecast.getTotalSubscriptions())/expiredForecast.getTotalSubscriptions();
                            trend.setChangeInTotalSubscriptionCount(trendChangeInTotalCount);
                        } else if (entityMetricType == EntityMetricType.NEW) {
                            double trendChangeInNewCount = (activeForecast.getNewSubscriptions() - expiredForecast.getNewSubscriptions())/expiredForecast.getNewSubscriptions();
                            trend.setChangeInNewSubscriptionCount(trendChangeInNewCount);
                        } else if (entityMetricType == EntityMetricType.CHURN) {
                            double trendChangeInChurnedSubscriptionCount = (activeForecast.getChurnedSubscriptions() - expiredForecast.getChurnedSubscriptions())/expiredForecast.getChurnedSubscriptions();
                            trend.setChangeInChurnedSubscriptionCount(trendChangeInChurnedSubscriptionCount);
                        }
                        changeInSubscriptionCountPerPeriod.set(i, trend);
                        break;
                    }
                }
            }
        subscriptionForecastTrendViewRepository.save(changeInSubscriptionCountPerPeriod);
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
