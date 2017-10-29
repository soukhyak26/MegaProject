package com.affaince.subscription.forecast.build;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.forecast.query.repository.SubscriptionActualsViewRepoitory;
import com.affaince.subscription.forecast.query.repository.SubscriptionPseudoActualsViewRepository;
import com.affaince.subscription.forecast.query.view.SubscriptionActualsView;
import com.affaince.subscription.forecast.query.view.SubscriptionPseudoActualsView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

/**
 * Created by mandar on 10/29/2017.
 */
public class SubscriptionMetricsForecastingTrigger {
    @Autowired
    SubscriptionActualsViewRepoitory subscriptionActualsViewRepoitory;

    @Autowired
    SubscriptionPseudoActualsViewRepository subscriptionPseudoActualsViewRepository;
    
    public boolean triggerSubscriptionMetricsForecast() {
        //get the actuals data from last to first in descending order
        LocalDate currentDay = SysDate.now();
        final Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        final SubscriptionActualsView latestSubscriptionActualsView = subscriptionActualsViewRepoitory.findByRegistrationDateDesc().get(0);
        final double totalActualSubscriptionCount = latestSubscriptionActualsView.getTotalSubscriptions();

        //find total subscription count for the current day(day of execution)
        SubscriptionPseudoActualsView activePseudoActualsView = subscriptionPseudoActualsViewRepository.findByForecastContentStatusAndSubscriptionVersionId_StartDateDesc(ForecastContentStatus.ACTIVE).get(0);

        //find value of total subscriptions in PseudoActuals on date of execution.
        double totalPseudoActualSubsctiptionCount = activePseudoActualsView.getTotalSubscriptions();

        final double changeOfActualDemandAgainstForecast = (totalActualSubscriptionCount - totalPseudoActualSubsctiptionCount) / totalPseudoActualSubsctiptionCount;
        final double changeThresholdForPriceChange = 0.1;

        if (totalActualSubscriptionCount > totalPseudoActualSubsctiptionCount && Math.abs(changeOfActualDemandAgainstForecast) > changeThresholdForPriceChange) {
            return true;
        }else {
            return false;
        }
    }


}
