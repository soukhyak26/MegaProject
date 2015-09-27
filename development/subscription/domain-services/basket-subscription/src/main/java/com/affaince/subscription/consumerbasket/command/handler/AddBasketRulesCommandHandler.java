package com.affaince.subscription.consumerbasket.command.handler;

import com.affaince.subscription.common.type.Discount;
import com.affaince.subscription.common.type.DiscountUnit;
import com.affaince.subscription.consumerbasket.command.AddBasketRulesCommand;
import com.affaince.subscription.consumerbasket.command.domain.BasketRule;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
@Component
public class AddBasketRulesCommandHandler {

    private final Repository<BasketRule> basketRuleRepository;

    @Autowired
    public AddBasketRulesCommandHandler(Repository<BasketRule> basketRuleRepository) {
        this.basketRuleRepository = basketRuleRepository;
    }

    @CommandHandler
    public void handle(AddBasketRulesCommand command) {
        Discount discount = new Discount(command.getMaximumPermissibleDiscount(),
                DiscountUnit.valueOf(command.getMaximumPermissibleDiscountUnit()));
        BasketRule basketRule = new BasketRule(command.getBasketRuleId(), command.getMaximumPermissibleAmount(),
                command.getMinimumAmountForDiscountEligibility(), discount);
        basketRuleRepository.add(basketRule);
    }
}
