package com.affaince.subscription.forecast.build;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.forecast.query.repository.SubscriberPseudoActualsViewRepository;
import com.affaince.subscription.forecast.query.repository.SubscribersActualsViewRepository;
import com.affaince.subscription.forecast.query.view.SubscriberPseudoActualsView;
import com.affaince.subscription.forecast.query.view.SubscribersActualsView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

/**
 * Created by mandar on 10/29/2017.
 */
public class SubscriberMetricsForecastingTrigger {
    @Autowired
    SubscribersActualsViewRepository subscribersActualsViewRepoitory;

    @Autowired
    SubscriberPseudoActualsViewRepository subscriberPseudoActualsViewRepository;
    
    public boolean triggerSubscribersMetricsForecast() {
        //get the actuals data from last to first in descending order
        LocalDate currentDay = SysDate.now();
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        final SubscribersActualsView latestSubscribersActualsView = subscribersActualsViewRepoitory.findByRegistrationDateDesc().get(0);
        final double totalActualSubscribersCount = latestSubscribersActualsView.getTotalSubscribers();

        //find total subscription count for the current day(day of execution)
        SubscriberPseudoActualsView activePseudoActualsView = subscriberPseudoActualsViewRepository.findByForecastContentStatusAndSubscriberVersionId_RegistrationDateDesc(ForecastContentStatus.ACTIVE).get(0);

        //find value of total subscriptions in PseudoActuals on date of execution.
        double totalPseudoActualSubsctiptionCount = activePseudoActualsView.getTotalSubscribers();

        final double changeOfActualDemandAgainstForecast = (totalActualSubscribersCount - totalPseudoActualSubsctiptionCount) / totalPseudoActualSubsctiptionCount;
        final double changeThresholdForPriceChange = 0.1;

        if (totalActualSubscribersCount > totalPseudoActualSubsctiptionCount && Math.abs(changeOfActualDemandAgainstForecast) > changeThresholdForPriceChange) {
            return true;
        }else {
            return false;
        }
    }
}
