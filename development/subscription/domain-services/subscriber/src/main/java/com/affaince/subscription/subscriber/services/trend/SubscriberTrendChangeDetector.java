package com.affaince.subscription.subscriber.services.trend;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityMetricType;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.query.repository.SubscriberForecastTrendViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscribersForecastViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.*;
import com.affaince.subscription.subscriber.vo.SubscriberVersionId;
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
public class SubscriberTrendChangeDetector {
    @Autowired
    SubscribersForecastViewRepository subscribersForecastViewRepository;
    @Autowired
    SubscriptionRuleViewRepository subscriptionRuleViewRepository;
    @Autowired
    SubscriberForecastTrendViewRepository subscriberForecastTrendViewRepository;

    public List<SubscriberForecastTrendView> determineTrendChange(String id, EntityMetadata entityMetadata){
        EntityMetricType entityMetricType = null;
        double minValue = 0;
        double maxValue = 0;
        Map<String, Object> namedMetadata = entityMetadata.getNamedEntries();
        for (String s : namedMetadata.keySet()) {
            switch (s) {
                case "ENTITY_METRIC_TYPE":
                    entityMetricType = (EntityMetricType) namedMetadata.get(s);
                    break;
            }
        }

        List<SubscribersForecastView> activeProductForecastList = subscribersForecastViewRepository.findByForecastContentStatusOrderBySubscriberVersionId_ForecastDateDesc(ForecastContentStatus.ACTIVE);
        List<SubscribersForecastView> expiredForecastList = subscribersForecastViewRepository.findByForecastContentStatusOrderBySubscriberVersionId_ForecastDateDesc(ForecastContentStatus.EXPIRED);
        LocalDate referenceForecastDate = expiredForecastList.get(0).getSubscriberVersionId().getForecastDate();
        List<SubscribersForecastView> latestExpiredForecastList = expiredForecastList.stream().filter(forecast -> forecast.getSubscriberVersionId().getForecastDate().equals(referenceForecastDate)).collect(Collectors.toList());
        LocalDate dateOfComparison = SysDate.now();
        List<SubscriberForecastTrendView> changeInSubscriptionCountPerPeriod = new ArrayList<>();
        //find if aggregation period is weekly monthly or quarterly
        //find if aggregation period is weekly monthly or quarterly
        SubscriptionRuleView configView=subscriptionRuleViewRepository.findAll().iterator().next();
        int aggregationPeriod = configView.getActualsAggregationPeriodForTargetForecast();
        double contingencyStockPercentage=configView.getContingencyStockPercentage();

        int recordsForComparision = determineNumberOfRecordsTobeCompared(aggregationPeriod, dateOfComparison);
        if(activeProductForecastList.size()>=recordsForComparision && latestExpiredForecastList.size()>=recordsForComparision)
            for (int i = 0; i < recordsForComparision; i++) {
                SubscribersForecastView activeForecast = activeProductForecastList.get(i);
                for(int j=0;j<recordsForComparision;j++) {
                    SubscribersForecastView expiredForecast = latestExpiredForecastList.get(j);
                    if (activeForecast.getSubscriberVersionId().getStartDate().equals(expiredForecast.getSubscriberVersionId().getStartDate())) {
                        SubscriberVersionId subscriberVersionId = new SubscriberVersionId(dateOfComparison ,activeForecast.getSubscriberVersionId().getStartDate());
                        SubscriberForecastTrendView trend=subscriberForecastTrendViewRepository.findOne(subscriberVersionId);
                        if(null == trend){
                            trend = new SubscriberForecastTrendView(dateOfComparison, activeForecast.getSubscriberVersionId().getStartDate(), activeForecast.getEndDate());
                        }
                        if (entityMetricType == EntityMetricType.TOTAL) {
                            double trendChangeInTotalCount = (activeForecast.getTotalSubscribers() - expiredForecast.getTotalSubscribers())/expiredForecast.getTotalSubscribers();
                            trend.setChangeInTotalSubscriberCount(trendChangeInTotalCount);
                        }else if(entityMetricType == EntityMetricType.NEW){
                            double trendChangeInNewCount= (activeForecast.getNewSubscribers() - expiredForecast.getNewSubscribers())/expiredForecast.getNewSubscribers();
                            trend.setChangeInNewSubscriberCount(trendChangeInNewCount);
                        }else if(entityMetricType == EntityMetricType.CHURN){
                            double trendChangeInChurnCount= (activeForecast.getChurnedSubscribers() - expiredForecast.getChurnedSubscribers())/expiredForecast.getChurnedSubscribers();
                            trend.setChangeInChurnedSubscriberCount(trendChangeInChurnCount);
                        }
                        changeInSubscriptionCountPerPeriod.set(i, trend);
                        break;
                    }
                }
            }
        subscriberForecastTrendViewRepository.save(changeInSubscriptionCountPerPeriod);
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
