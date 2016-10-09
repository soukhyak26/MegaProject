package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
public class AddContactDetailsCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String email;
    private String mobileNumber;
    private String alternativeNumber;

    public AddContactDetailsCommand(String subscriberId, String email, String mobileNumber, String alternativeNumber) {
        this.subscriberId = subscriberId;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.alternativeNumber = alternativeNumber;
    }

    public AddContactDetailsCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
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
