package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.DeleteItemFromSubscriptionCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-09-2015.
 */
@Component
public class DeleteItemFromSubscriptionCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public DeleteItemFromSubscriptionCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DeleteItemFromSubscriptionCommand command) {
        final Subscriber subscriber = repository.load(command.getSubscriberId());
        final Subscription subscription = subscriber.getSubscription();
        subscription.deleteItem(command.getItemId());
    }
}
