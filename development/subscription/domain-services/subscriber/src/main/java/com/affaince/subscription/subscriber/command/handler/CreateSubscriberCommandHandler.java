package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.subscriber.command.CreateSubscriberCommand;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.common.vo.SubscriberName;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class CreateSubscriberCommandHandler {

    private final Repository<Subscriber> repository;

    @Autowired
    public CreateSubscriberCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateSubscriberCommand command) {
        Subscriber subscriber = new Subscriber(command.getSubscriberId(),
                command.getSubscriberName(), command.getBillingAddress(), command.getShippingAddress(),
                command.getContactDetails());
        repository.add(subscriber);
    }
}
