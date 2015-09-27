package com.affaince.subscription.consumerbasket.web.request;

import javax.validation.constraints.NotNull;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class AddressRequest {

    @NotNull
    private String addressLine1;
    private String addressLine2;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;
    @NotNull
    private String pinCode;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
