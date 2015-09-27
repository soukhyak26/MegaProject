package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.consumerbasket.command.event.BasketRuleAddedEvent;
import com.affaince.subscription.consumerbasket.query.repository.BasketRuleRepository;
import com.affaince.subscription.consumerbasket.query.view.BasketRuleView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 27-09-2015.
 */
@Component
public class BasketRuleAddedEventListener {

    private final BasketRuleRepository basketRuleRepository;

    @Autowired
    public BasketRuleAddedEventListener(BasketRuleRepository basketRuleRepository) {
        this.basketRuleRepository = basketRuleRepository;
    }

    @EventHandler
    public void on(BasketRuleAddedEvent event) {
        BasketRuleView basketRuleView = new BasketRuleView(event.getBasketRuleId(),
                event.getMaximumPermissibleAmount(), event.getMinimumAmountForDiscountEligibility(),
                event.getMaximumPermissibleDiscount());
        basketRuleRepository.save(basketRuleView);
    }
}
