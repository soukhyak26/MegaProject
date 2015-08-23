package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateSubscriberAddressCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class UpdateSubscriberAddressCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public UpdateSubscriberAddressCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateSubscriberAddressCommand command) {
        Subscriber subscriber = repository.load(command.getSubscriberId());
        subscriber.updateAddress(command);
    }
}
