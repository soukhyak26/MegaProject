package com.affaince.subscription.subscriber.services.recommendations;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.configuration.DeliveriesWeightRangeConfig;
import com.affaince.subscription.subscriber.query.repository.DeliveryForecastViewRepository;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryForecastTrendView;
import com.affaince.subscription.subscriber.query.view.DeliveryForecastView;
import com.affaince.subscription.subscriber.query.view.SubscriptionRuleView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 9/23/2017.
 */
public class DeliveryBasedRecommendationService {

    @Autowired
    DeliveryForecastViewRepository deliveryForecastViewRepository;
    @Autowired
    SubscriptionRuleViewRepository subscriptionRuleViewRepository;

    public List<DeliveryForecastTrendView> determineTrendChange(String id,double minWeight,double maxWeight){
        List<DeliveryForecastView> activeProductForecastList = deliveryForecastViewRepository.findByForecastContentStatusAndWeightRangeMinGreaterThanEqualAndWeightRangeMaxLessThanOrderByForecastDateDesc(ForecastContentStatus.ACTIVE,minWeight,maxWeight);
        List<DeliveryForecastView> expiredForecastList = deliveryForecastViewRepository.findByForecastContentStatusAndWeightRangeMinGreaterThanEqualAndWeightRangeMaxLessThanOrderByForecastDateDesc(ForecastContentStatus.EXPIRED,minWeight,maxWeight);
        LocalDate referenceForecastDate = expiredForecastList.get(0).getForecastDate();
        List<DeliveryForecastView> latestExpiredForecastList = expiredForecastList.stream().filter(forecast -> forecast.getForecastDate().equals(referenceForecastDate)).collect(Collectors.toList());
        LocalDate dateOfComparison = SysDate.now();
        List<DeliveryForecastTrendView> changeInSubscriptionCountPerPeriod = new ArrayList<>();
        //find if aggregation period is weekly monthly or quarterly
        //find if aggregation period is weekly monthly or quarterly
        SubscriptionRuleView configView=subscriptionRuleViewRepository.findAll().iterator().next();
        int aggregationPeriod = configView.getActualsAggregationPeriodForTargetForecast();
        double contingencyStockPercentage=configView.getContingencyStockPercentage();

        int recordsForComparision = determineNumberOfRecordsTobeCompared(aggregationPeriod, dateOfComparison);
        if(activeProductForecastList.size()>=recordsForComparision && latestExpiredForecastList.size()>=recordsForComparision)
            for (int i = 0; i < recordsForComparision; i++) {
                DeliveryForecastView activeForecast = activeProductForecastList.get(i);
                DeliveryForecastView expiredForecast = latestExpiredForecastList.get(i);
                if (activeForecast.getStartDate().equals(expiredForecast.getStartDate())) {
                    long trendChange = activeForecast.getDeliveryCount() - expiredForecast.getDeliveryCount();
                    //If change of trend(visible in active forecast) is more than contingency stock percent limit,it means additional demand needs to be raised.
                    if((trendChange/activeForecast.getDeliveryCount())> contingencyStockPercentage) {
                        DeliveryForecastTrendView trend = new DeliveryForecastTrendView(dateOfComparison, activeForecast.getStartDate(), activeForecast.getEndDate(), trendChange);
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
