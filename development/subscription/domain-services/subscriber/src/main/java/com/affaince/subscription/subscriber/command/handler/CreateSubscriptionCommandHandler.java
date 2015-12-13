package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.CreateSubscriptionCommand;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
@Component
public class CreateSubscriptionCommandHandler {

    private final Repository<Subscription> repository;

    @Autowired
    public CreateSubscriptionCommandHandler(Repository<Subscription> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateSubscriptionCommand command) {
        Subscription subscription = new Subscription(command.getSubscriptionId(), command.getUserId());
        repository.add(subscription);
    }
}
