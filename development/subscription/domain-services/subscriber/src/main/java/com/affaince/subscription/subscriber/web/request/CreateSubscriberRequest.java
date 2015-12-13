package com.affaince.subscription.subscriber.web.request;

import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.common.vo.SubscriberName;

import javax.validation.Valid;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class CreateSubscriberRequest {
    @Valid
    private SubscriberName subscriberName;
    @Valid
    private Address address;
    @Valid
    private ContactDetails contactDetails;

    public SubscriberName getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(SubscriberName subscriberName) {
        this.subscriberName = subscriberName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
