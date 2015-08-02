package com.affaince.subscription.subscriber.command.event;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class SubscriberContactDetailsUpdatedEvent {
    private String subscriberId;
    private String email;
    private String mobileNumber;
    private String alternativeNumber;

    public SubscriberContactDetailsUpdatedEvent(String subscriberId, String email, String mobileNumber, String alternativeNumber) {
        this.subscriberId = subscriberId;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.alternativeNumber = alternativeNumber;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
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
