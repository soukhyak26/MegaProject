package com.affaince.subscription.common.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class SubscriberName {
    @NotNull
    private String title;
    @NotNull(message = "firstName cannot be null")
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;

    public SubscriberName(String title, String firstName, String middleName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public SubscriberName() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
