package com.affaince.subscription.testdata.generator;

import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.common.vo.SubscriberName;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class Subscriber {
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pinCode;
    private String email;
    private String mobileNumber;
    private String alternativeNumber;

    public Subscriber(String title, String firstName, String middleName, String lastName, String addressLine1, String addressLine2, String city, String state, String country, String pinCode, String email, String mobileNumber, String alternativeNumber) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.alternativeNumber = alternativeNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
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

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAlternativeNumber() {
        return alternativeNumber;
    }
}
