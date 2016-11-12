package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.SubscriptionRuleAddedEvent;
import com.affaince.subscription.subscriber.query.repository.BasketRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.BasketRuleView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 27-09-2015.
 */
@Component
public class SubscriptionRuleAddedEventListener {

    private final BasketRuleViewRepository basketRuleViewRepository;

    @Autowired
    public SubscriptionRuleAddedEventListener(BasketRuleViewRepository basketRuleViewRepository) {
        this.basketRuleViewRepository = basketRuleViewRepository;
    }

    @EventHandler
    public void on(SubscriptionRuleAddedEvent event) {
        BasketRuleView basketRuleView = new BasketRuleView(event.getBasketRuleId(),
                event.getMaximumPermissibleAmount(), event.getMinimumAmountForDiscountEligibility(),
                event.getMaximumPermissibleDiscount(), event.getMinimumAmountEligibleForFreeShipping());
        basketRuleViewRepository.save(basketRuleView);
    }
}
