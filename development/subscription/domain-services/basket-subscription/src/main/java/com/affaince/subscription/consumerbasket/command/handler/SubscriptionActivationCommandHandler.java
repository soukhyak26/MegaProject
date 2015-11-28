package com.affaince.subscription.consumerbasket.command.handler;

import com.affaince.subscription.consumerbasket.command.SubscriptionActivationCommand;
import com.affaince.subscription.consumerbasket.command.domain.ConsumerBasket;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
@Component
public class SubscriptionActivationCommandHandler {

    private final Repository<ConsumerBasket> repository;

    @Autowired
    public SubscriptionActivationCommandHandler(Repository<ConsumerBasket> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(SubscriptionActivationCommand command) {
        ConsumerBasket consumerBasket = repository.load(command.getBasketId());
        consumerBasket.activateSubscription();
    }
}
