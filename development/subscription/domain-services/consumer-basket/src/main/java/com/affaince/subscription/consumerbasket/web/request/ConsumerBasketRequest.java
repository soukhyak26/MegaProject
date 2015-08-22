package com.affaince.subscription.consumerbasket.web.request;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class ConsumerBasketRequest {

    private String userId;
    private ContactDetailsRequest contactDetails;
    private double totalAmount;
    private double totalAmountAfterDiscount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ContactDetailsRequest getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetailsRequest contactDetails) {
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
