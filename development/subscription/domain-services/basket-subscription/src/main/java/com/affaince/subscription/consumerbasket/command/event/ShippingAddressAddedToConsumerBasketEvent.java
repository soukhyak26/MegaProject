package com.affaince.subscription.consumerbasket.command.event;

/**
 * Created by rbsavaliya on 10-08-2015.
 */
public class ShippingAddressAddedToConsumerBasketEvent {
    private String basketId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pinCode;

    public ShippingAddressAddedToConsumerBasketEvent(String basketId, String addressLine1, String addressLine2, String city, String state, String country, String pinCode) {
        this.basketId = basketId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }

    public ShippingAddressAddedToConsumerBasketEvent() {
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

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