package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.common.vo.SubscriberName;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class SubscriberCreatedEvent {
    private String subscriberId;
    private SubscriberName subscriberName;
    private Address billingAddress;
    private Address shippingAddress;
    private ContactDetails contactDetails;
    private int subscriberStatusCode;

    public SubscriberCreatedEvent(String subscriberId, SubscriberName subscriberName, Address billingAddress, Address shippingAddress, ContactDetails contactDetails, int subscriberStatusCode) {
        this.subscriberId = subscriberId;
        this.subscriberName = subscriberName;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.contactDetails = contactDetails;
        this.subscriberStatusCode = subscriberStatusCode;
    }

    public SubscriberCreatedEvent() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public SubscriberName getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(SubscriberName subscriberName) {
        this.subscriberName = subscriberName;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public int getSubscriberStatusCode() {
        return subscriberStatusCode;
    }

    public void setSubscriberStatusCode(int subscriberStatusCode) {
        this.subscriberStatusCode = subscriberStatusCode;
    }
}
