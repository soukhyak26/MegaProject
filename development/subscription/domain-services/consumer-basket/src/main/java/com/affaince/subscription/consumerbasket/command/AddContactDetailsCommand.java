package com.affaince.subscription.consumerbasket.command;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class AddContactDetailsCommand {

    private String basketId;
    private String email;
    private String mobileNumber;
    private String alternativeNumber;

    public AddContactDetailsCommand(String basketId, String email, String mobileNumber, String alternativeNumber) {
        this.basketId = basketId;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.alternativeNumber = alternativeNumber;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
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
