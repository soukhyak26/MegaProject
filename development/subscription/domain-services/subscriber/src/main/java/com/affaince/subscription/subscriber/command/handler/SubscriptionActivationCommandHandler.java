package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.SubscriptionActivationCommand;
import com.affaince.subscription.subscriber.domain.Subscriber;
import com.affaince.subscription.subscriber.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
@Component
public class SubscriptionActivationCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public SubscriptionActivationCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(SubscriptionActivationCommand command) {
        Subscriber subscriber = repository.load(command.getSubscriberId());
        final Subscription subscription = subscriber.getSubscription();
        subscription.activateSubscription();
    }
}
