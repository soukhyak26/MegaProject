
package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.SubscriptionRuleAddedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionRuleView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 27-09-2015.
 */
@Component
public class SubscriptionRuleAddedEventListener {

    private final SubscriptionRuleViewRepository subscriptionRuleViewRepository;

    @Autowired
    public SubscriptionRuleAddedEventListener(SubscriptionRuleViewRepository subscriptionRuleViewRepository) {
        this.subscriptionRuleViewRepository = subscriptionRuleViewRepository;
    }

    @EventHandler
    public void on(SubscriptionRuleAddedEvent event) {
        SubscriptionRuleView subscriptionRuleView = subscriptionRuleViewRepository.findOne(event.getBasketRuleId());
        if (null == subscriptionRuleView) {
            subscriptionRuleView = new SubscriptionRuleView(event.getBasketRuleId(),
                    event.getMaximumPermissibleAmount(), event.getMinimumAmountForDiscountEligibility(),
                    event.getMaximumPermissibleDiscount(), event.getMinimumAmountEligibleForFreeShipping(),
                    event.getDiffBetweenDeliveryPreparationAndDispatchDate(), event.getActualsAggregationPeriodForTargetForecast(), event.getContingencyStockPercentage(), event.getSubscriptionValueRanges());
            subscriptionRuleViewRepository.save(subscriptionRuleView);
        } else {
            if(0 !=event.getMaximumPermissibleAmount())
            subscriptionRuleView.setMaximumPermissibleAmount(event.getMaximumPermissibleAmount());
            if(0 != event.getMinimumAmountForDiscountEligibility() )
            subscriptionRuleView.setMinimumAmountForDiscountEligibility(event.getMinimumAmountForDiscountEligibility());
            if(0 != event.getMaximumPermissibleDiscount().getValue())
            subscriptionRuleView.setMaximumPermissibleDiscount(event.getMaximumPermissibleDiscount());
            if(0 != event.getMinimumAmountEligibleForFreeShipping() )
            subscriptionRuleView.setMinimumAmountEligibleForFreeShipping(event.getMinimumAmountEligibleForFreeShipping());
            if(0 != event.getDiffBetweenDeliveryPreparationAndDispatchDate())
            subscriptionRuleView.setDiffBetweenDeliveryPreparationAndDispatchDate(event.getDiffBetweenDeliveryPreparationAndDispatchDate());
            if(0 != event.getActualsAggregationPeriodForTargetForecast())
            subscriptionRuleView.setActualsAggregationPeriodForTargetForecast(event.getActualsAggregationPeriodForTargetForecast());
            if(0 !=event.getContingencyStockPercentage() )
            subscriptionRuleView.setContingencyStockPercentage(event.getContingencyStockPercentage());
            if(null != event.getSubscriptionValueRanges())
            subscriptionRuleView.setSubscriptionValueRanges(event.getSubscriptionValueRanges());
        }
    }
}
