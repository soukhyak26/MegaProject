package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.AddAddressToSubscriptionCommand;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-08-2015.
 */
@Component
public class AddAddressToSubscriptionCommandHandler {

    private final Repository<Subscription> repository;

    @Autowired
    public AddAddressToSubscriptionCommandHandler(Repository<Subscription> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddAddressToSubscriptionCommand command) {
        Subscription subscription = repository.load(command.getSubscriptionId());
        subscription.addAddress(command);
    }
}
