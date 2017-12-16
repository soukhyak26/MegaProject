package com.affaince.subscription.subscriber.event;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class ContactDetailsAddedEvent {
    private String subscriptionId;
    private String email;
    private String mobileNumber;
    private String alternativeNumber;

    public ContactDetailsAddedEvent(String subscriptionId, String email, String mobileNumber, String alternativeNumber) {
        this.subscriptionId = subscriptionId;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.alternativeNumber = alternativeNumber;
    }

    public ContactDetailsAddedEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAlternativeNumber() {
        return alternativeNumber;
    }

    public void setAlternativeNumber(String alternativeNumber) {
        this.alternativeNumber = alternativeNumber;
    }
}
