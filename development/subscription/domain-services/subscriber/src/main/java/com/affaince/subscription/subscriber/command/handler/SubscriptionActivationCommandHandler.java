package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.SubscriptionActivationCommand;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
@Component
public class SubscriptionActivationCommandHandler {

    private final Repository<Subscription> repository;

    @Autowired
    public SubscriptionActivationCommandHandler(Repository<Subscription> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(SubscriptionActivationCommand command) {
        Subscription subscription = repository.load(command.getSubscriptionId());
        subscription.activateSubscription();
    }
}
