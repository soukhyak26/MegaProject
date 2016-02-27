package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.AddDeliveryChargesRuleCommand;
import com.affaince.subscription.subscriber.command.domain.DeliveryChargesRule;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rsavaliya on 27/2/16.
 */
@Component
public class AddDeliveryChargesRuleCommandHandler {

    private final Repository<DeliveryChargesRule> deliveryChargesRuleRepository;

    @Autowired
    public AddDeliveryChargesRuleCommandHandler(Repository<DeliveryChargesRule> deliveryChargesRuleRepository) {
        this.deliveryChargesRuleRepository = deliveryChargesRuleRepository;
    }

    @CommandHandler
    public void handle (AddDeliveryChargesRuleCommand command) {
        final DeliveryChargesRule deliveryChargesRule = new DeliveryChargesRule(
            command.getRuleId(), command.getDeliveryChargesRules()
        );
        deliveryChargesRuleRepository.add(deliveryChargesRule);
    }
}
