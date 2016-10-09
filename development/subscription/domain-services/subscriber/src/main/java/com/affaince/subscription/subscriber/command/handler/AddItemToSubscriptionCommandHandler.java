package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.AddItemToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 23-08-2015.
 */
@Component
public class AddItemToSubscriptionCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public AddItemToSubscriptionCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddItemToSubscriptionCommand command) {
        final Subscriber subscriber = repository.load(command.getSubscriberId());
        Subscription subscription = subscriber.getSubscription();
        subscription.addItemToBasket(command);
    }
}
