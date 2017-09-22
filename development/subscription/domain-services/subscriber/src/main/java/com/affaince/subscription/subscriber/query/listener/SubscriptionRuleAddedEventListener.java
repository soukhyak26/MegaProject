
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
        SubscriptionRuleView subscriptionRuleView = new SubscriptionRuleView(event.getBasketRuleId(),
                event.getMaximumPermissibleAmount(), event.getMinimumAmountForDiscountEligibility(),
                event.getMaximumPermissibleDiscount(), event.getMinimumAmountEligibleForFreeShipping(),
                event.getDiffBetweenDeliveryPreparationAndDispatchDate(),event.getActualsAggregationPeriodForTargetForecast(),event.getContingencyStockPercentage());
        subscriptionRuleViewRepository.save(subscriptionRuleView);
    }
}
