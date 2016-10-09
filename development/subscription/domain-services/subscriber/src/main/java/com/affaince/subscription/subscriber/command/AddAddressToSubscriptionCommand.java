package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 10-08-2015.
 */
public class AddAddressToSubscriptionCommand {
    @TargetAggregateIdentifier
    private String SubscriberId;
    private String addressType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pinCode;

    public AddAddressToSubscriptionCommand(String subscriberId, String addressType, String addressLine1, String addressLine2, String city, String state, String country, String pinCode) {
        SubscriberId = subscriberId;
        this.addressType = addressType;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }

    public AddAddressToSubscriptionCommand() {
    }

    public String getAddressType() {
        return addressType;
    }

    public String getSubscriberId() {
        return SubscriberId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPinCode() {
        return pinCode;
    }
}
