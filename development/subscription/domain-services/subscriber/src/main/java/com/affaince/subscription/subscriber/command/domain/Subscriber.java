package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.subscriber.command.event.SubscriberContactDetailsUpdatedEvent;
import com.affaince.subscription.subscriber.command.event.SubscriberCreatedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class Subscriber extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String subscriberId;
    private SubscriberName subscriberName;
    private Address address;
    private ContactDetails contactDetails;

    public Subscriber(String subscriberId, SubscriberName subscriberName, Address address, ContactDetails contactDetails) {
        this.subscriberId = subscriberId;
        this.subscriberName = subscriberName;
        this.address = address;
        this.contactDetails = contactDetails;
        apply(new SubscriberCreatedEvent(
                subscriberId, subscriberName.getFirstName(), subscriberName.getMiddleName(), subscriberName.getLastName(),
                address.getAddressLine1(), address.getAddressLine2(), address.getCity(), address.getState(), address.getCountry(), address.getPinCode(),
                contactDetails.getEmail(), contactDetails.getMobileNumber(), contactDetails.getAlternativeNumber()
        ));
    }

    public Subscriber() {
    }

    @EventSourcingHandler
    public void on (SubscriberCreatedEvent event) {
        this.subscriberId = event.getSubscriberId();
        SubscriberName subscriberName = new SubscriberName(
                event.getFirstName(),
                event.getMiddleName(),
                event.getLastName()
        );
        this.subscriberName = subscriberName;
        Address address = new Address(
                event.getAddressLine1(),
                event.getAddressLine2(),
                event.getCity(),
                event.getState(),
                event.getCountry(),
                event.getPinCode()
        );
        this.address = address;
        ContactDetails contactDetails = new ContactDetails(
                event.getEmail(),
                event.getMobileNumber(),
                event.getAlternativeNumber()
        );
        this.contactDetails = contactDetails;
    }

    @EventSourcingHandler
    public void on (SubscriberContactDetailsUpdatedEvent event) {
        ContactDetails contactDetails = new ContactDetails(
                event.getEmail(),
                event.getMobileNumber(),
                event.getAlternativeNumber()
        );
        this.contactDetails = contactDetails;
    }

    public void updateContactDetails(String email, String mobileNumber, String alternativeNumber) {
        ContactDetails contactDetails = new ContactDetails(email, mobileNumber, alternativeNumber);
        apply(new SubscriberContactDetailsUpdatedEvent(this.subscriberId, email, mobileNumber, alternativeNumber));
    }
}
