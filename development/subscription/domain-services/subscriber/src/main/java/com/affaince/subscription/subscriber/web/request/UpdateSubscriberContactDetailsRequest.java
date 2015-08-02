package com.affaince.subscription.subscriber.web.request;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class UpdateSubscriberContactDetailsRequest {

    private String email;
    private String mobileNumber;
    private String alternativeNumber;

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
