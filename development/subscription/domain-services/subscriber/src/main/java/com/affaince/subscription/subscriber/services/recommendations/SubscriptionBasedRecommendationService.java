package com.affaince.subscription.subscriber.services.recommendations;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.subscriber.query.repository.SubscriptionForecastViewRepository;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionForecastTrendView;
import com.affaince.subscription.subscriber.query.view.SubscriptionForecastView;
import com.affaince.subscription.subscriber.query.view.SubscriptionRuleView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 9/21/2017.
 */
public class SubscriptionBasedRecommendationService {

    @Autowired
    SubscriptionForecastViewRepository subscriptionForecastViewRepository;
    @Autowired
    SubscriptionRuleViewRepository subscriptionRuleViewRepository;

    public List<SubscriptionForecastTrendView> determineTrendChange(String id){
        List<SubscriptionForecastView> activeProductForecastList = subscriptionForecastViewRepository.findByForecastContentStatusOrderByForecastDateDesc(ForecastContentStatus.ACTIVE);
        List<SubscriptionForecastView> expiredForecastList = subscriptionForecastViewRepository.findByForecastContentStatusOrderByForecastDateDesc(ForecastContentStatus.EXPIRED);
        LocalDate referenceForecastDate = expiredForecastList.get(0).getForecastDate();
        List<SubscriptionForecastView> latestExpiredForecastList = expiredForecastList.stream().filter(forecast -> forecast.getForecastDate().equals(referenceForecastDate)).collect(Collectors.toList());
        LocalDate dateOfComparison = SysDate.now();
        List<SubscriptionForecastTrendView> changeInSubscriptionCountPerPeriod = new ArrayList<>();
        //find if aggregation period is weekly monthly or quarterly
        //find if aggregation period is weekly monthly or quarterly
        SubscriptionRuleView configView=subscriptionRuleViewRepository.findAll().iterator().next();
        int aggregationPeriod = configView.getActualsAggregationPeriodForTargetForecast();
        double contingencyStockPercentage=configView.getContingencyStockPercentage();

        int recordsForComparision = determineNumberOfRecordsTobeCompared(aggregationPeriod, dateOfComparison);
        if(activeProductForecastList.size()>=recordsForComparision && latestExpiredForecastList.size()>=recordsForComparision)
            for (int i = 0; i < recordsForComparision; i++) {
                SubscriptionForecastView activeForecast = activeProductForecastList.get(i);
                SubscriptionForecastView expiredForecast = latestExpiredForecastList.get(i);
                if (activeForecast.getRegistrationDate().equals(expiredForecast.getRegistrationDate())) {
                    long trendChange = activeForecast.getTotalSubscriptions() - expiredForecast.getTotalSubscriptions();
                    //If change of trend(visible in active forecast) is more than contingency stock percent limit,it means additional demand needs to be raised.
                    if((trendChange/activeForecast.getTotalSubscriptions())>contingencyStockPercentage) {
                        SubscriptionForecastTrendView trend = new SubscriptionForecastTrendView(dateOfComparison, activeForecast.getRegistrationDate(), activeForecast.getEndDate(), trendChange);
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
