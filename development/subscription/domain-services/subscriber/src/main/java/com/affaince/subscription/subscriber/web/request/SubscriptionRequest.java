package com.affaince.subscription.subscriber.web.request;

import com.affaince.subscription.common.vo.ContactDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class SubscriptionRequest {

    @NotNull
    private String subscriberId;
    @Valid
    private ContactDetails contactDetails;
    private double totalAmount;
    private double totalAmountAfterDiscount;

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmountAfterDiscount() {
        return totalAmountAfterDiscount;
    }

    public void setTotalAmountAfterDiscount(double totalAmountAfterDiscount) {
        this.totalAmountAfterDiscount = totalAmountAfterDiscount;
    }
}
