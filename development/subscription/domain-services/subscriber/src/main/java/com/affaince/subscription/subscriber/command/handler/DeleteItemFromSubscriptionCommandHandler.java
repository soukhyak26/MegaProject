package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.DeleteItemFromSubscriptionCommand;
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

    private final Repository<Subscription> repository;

    @Autowired
    public DeleteItemFromSubscriptionCommandHandler(Repository<Subscription> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(DeleteItemFromSubscriptionCommand command) {
        final Subscription subscription = repository.load(command.getSubscriptionId());
        subscription.deleteItem(command.getItemId());
    }
}
