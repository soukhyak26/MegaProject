package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.common.type.Discount;
import com.affaince.subscription.common.type.DiscountUnit;
import com.affaince.subscription.subscriber.command.AddSubscriptionRulesCommand;
import com.affaince.subscription.subscriber.command.domain.SubscriptionRule;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
@Component
public class AddSubscriptionRulesCommandHandler {

    private final Repository<SubscriptionRule> basketRuleRepository;

    @Autowired
    public AddSubscriptionRulesCommandHandler(Repository<SubscriptionRule> basketRuleRepository) {
        this.basketRuleRepository = basketRuleRepository;
    }

    @CommandHandler
    public void handle(AddSubscriptionRulesCommand command) {
        final Discount discount = new Discount(command.getMaximumPermissibleDiscount(),
                DiscountUnit.valueOf(command.getMaximumPermissibleDiscountUnit()));
        final SubscriptionRule subscriptionRule = new SubscriptionRule(command.getBasketRuleId(), command.getMaximumPermissibleAmount(),
                command.getMinimumAmountForDiscountEligibility(), discount, command.getMinimumAmountEligibleForFreeShipping());
        basketRuleRepository.add(subscriptionRule);
    }
}
