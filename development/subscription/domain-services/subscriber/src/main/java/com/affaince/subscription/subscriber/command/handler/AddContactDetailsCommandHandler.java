package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.AddContactDetailsCommand;
import com.affaince.subscription.subscriber.domain.Subscriber;
import com.affaince.subscription.subscriber.domain.Subscription;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class AddContactDetailsCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public AddContactDetailsCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(AddContactDetailsCommand command) {
        final Subscriber subscriber = repository.load(command.getSubscriberId());
        Subscription subscription = subscriber.getSubscription();
        subscription.updateContactDetails(command.getEmail(), command.getMobileNumber(), command.getAlternativeNumber());
    }
}
