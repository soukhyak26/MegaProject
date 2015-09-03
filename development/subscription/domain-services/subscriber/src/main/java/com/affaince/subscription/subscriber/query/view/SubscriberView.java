package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.NetWorthSubscriberStatus;
import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.common.vo.SubscriberName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Document(collection = "SubscriberView")
public class SubscriberView {
    @Id
    private String subscriberId;
    private SubscriberName subscriberName;
    private Address billingAddress;
    private Address shippingAddress;
    private ContactDetails contactDetails;
    private NetWorthSubscriberStatus status;

    public SubscriberView(String subscriberId, SubscriberName subscriberName, Address billingAddress, Address shippingAddress, ContactDetails contactDetails, NetWorthSubscriberStatus status) {
        this.subscriberId = subscriberId;
        this.subscriberName = subscriberName;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.contactDetails = contactDetails;
        this.status = status;
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

    public NetWorthSubscriberStatus getStatus() {
        return status;
    }

    public void setStatus(NetWorthSubscriberStatus status) {
        this.status = status;
    }
}
