package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.NetWorthSubscriberStatus;
import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.common.vo.SubscriberName;
import com.affaince.subscription.subscriber.command.UpdateSubscriberAddressCommand;
import com.affaince.subscription.subscriber.command.event.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class Subscriber extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String subscriberId;
    private SubscriberName subscriberName;
    private Address billingAddress;
    private Address shippingAddress;
    private ContactDetails contactDetails;
    private NetWorthSubscriberStatus status;
    private List<String> couponCodes;
    private int rewardPoints;

    public Subscriber(String subscriberId, SubscriberName subscriberName, Address billingAddress, Address shippingAddress, ContactDetails contactDetails) {
        apply(new SubscriberCreatedEvent(subscriberId, subscriberName, billingAddress, shippingAddress, contactDetails, NetWorthSubscriberStatus.NORMAL.getSubscriberStatusCode()));
    }

    public Subscriber() {
    }

    @EventSourcingHandler
    public void on(SubscriberCreatedEvent event) {
        this.subscriberId = event.getSubscriberId();
        this.subscriberName = event.getSubscriberName();
        this.billingAddress = event.getBillingAddress();
        this.shippingAddress = event.getShippingAddress();
        this.contactDetails = event.getContactDetails();
        this.status = NetWorthSubscriberStatus.valueOf(event.getSubscriberStatusCode());
    }

    @EventSourcingHandler
    public void on(SubscriberContactDetailsUpdatedEvent event) {
        ContactDetails contactDetails = new ContactDetails(
                event.getEmail(),
                event.getMobileNumber(),
                event.getAlternativeNumber()
        );
        this.contactDetails = contactDetails;
    }

    @EventSourcingHandler
    public void on(SubscriberAddressUpdatedEvent event) {
        this.subscriberName = subscriberName;
        Address address = new Address(
                event.getAddressLine1(),
                event.getAddressLine2(),
                event.getCity(),
                event.getState(),
                event.getCountry(),
                event.getPinCode()
        );
        this.billingAddress = address;
    }

    @EventSourcingHandler
    public void on(RewardPointsAddedEvent event) {
        this.rewardPoints = this.rewardPoints + event.getRewardPoints();
    }

    @EventSourcingHandler
    public void on(CouponCodeAddedEvent event) {
        if (this.couponCodes == null) {
            this.couponCodes = new ArrayList<>();
        }
        this.couponCodes.add(event.getCouponCode());
    }

    public void updateContactDetails(String email, String mobileNumber, String alternativeNumber) {
        apply(new SubscriberContactDetailsUpdatedEvent(this.subscriberId, email, mobileNumber, alternativeNumber));
    }

    public void updateAddress(UpdateSubscriberAddressCommand command) {
        apply(new SubscriberAddressUpdatedEvent(
                this.subscriberId,
                command.getAddressLine1(),
                command.getAddressLine2(),
                command.getCity(),
                command.getState(),
                command.getCountry(),
                command.getPinCode()));
    }

    public void addRewardPoints(int rewardPoints) {
        apply(new RewardPointsAddedEvent(this.subscriberId, rewardPoints));
    }

    public void addCouponCode(String couponCode) {
        apply(new CouponCodeAddedEvent(this.subscriberId, couponCode));
    }
}
