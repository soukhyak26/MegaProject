package com.affaince.subscription.consumerbasket.web.request;

import com.affaince.subscription.common.vo.ContactDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class ConsumerBasketRequest {

    @NotNull
    private String userId;
    @Valid
    private ContactDetails contactDetails;
    private double totalAmount;
    private double totalAmountAfterDiscount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
