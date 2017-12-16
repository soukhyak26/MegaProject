package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateSubscriberContactDetailsCommand;
import com.affaince.subscription.subscriber.domain.Subscriber;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class UpdateSubscriberContactDetailsCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public UpdateSubscriberContactDetailsCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateSubscriberContactDetailsCommand command) {
        Subscriber subscriber = repository.load(command.getSubscriberId());
        subscriber.updateContactDetails(command.getEmail(), command.getMobileNumber(), command.getAlternativeNumber());
    }
}
