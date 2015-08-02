package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.CreateSubscriberCommand;
import com.affaince.subscription.subscriber.command.domain.Address;
import com.affaince.subscription.subscriber.command.domain.ContactDetails;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.SubscriberName;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class CreateSubscriberCommandHandler {

    private final Repository <Subscriber> repository;
    @Autowired
    public CreateSubscriberCommandHandler(Repository<Subscriber> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle (CreateSubscriberCommand command) {
        SubscriberName subscriberName = new SubscriberName(
                command.getFirstName(),
                command.getMiddleName(),
                command.getLastName()
        );
        Address address = new Address(
                command.getAddressLine1(),
                command.getAddressLine2(),
                command.getCity(),
                command.getState(),
                command.getCountry(),
                command.getPinCode()
        );
        ContactDetails contactDetails = new ContactDetails(
                command.getEmail(),
                command.getMobileNumber(),
                command.getAlternativeNumber()
        );
        Subscriber subscriber = new Subscriber(command.getSubscriberId(), subscriberName, address, contactDetails);
        repository.add(subscriber);
    }
}
