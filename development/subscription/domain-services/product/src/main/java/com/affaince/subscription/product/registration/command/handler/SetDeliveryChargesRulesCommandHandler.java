package com.affaince.subscription.product.registration.command.handler;

import com.affaince.subscription.product.registration.command.SetDeliveryChargesRulesCommand;
import com.affaince.subscription.product.registration.command.domain.SubscriptionSpecificOperatingExpense;
import com.affaince.subscription.product.registration.vo.RangeRule;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Component
public class SetDeliveryChargesRulesCommandHandler {
    private final Repository<SubscriptionSpecificOperatingExpense> repository;

    @Autowired
    public SetDeliveryChargesRulesCommandHandler(Repository<SubscriptionSpecificOperatingExpense> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(SetDeliveryChargesRulesCommand command) {
        final String expenseHeader = command.getExpenseHeader();
        final List<RangeRule> deliveryChargesRules = command.getDeliveryChargesRules();
        final SubscriptionSpecificOperatingExpense subscriptionSpecificOperatingExpense = new SubscriptionSpecificOperatingExpense(command.getId(),
                expenseHeader, deliveryChargesRules);
        repository.add(subscriptionSpecificOperatingExpense);
    }

}
