package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class CreateSubscriptionCommand {
    @TargetAggregateIdentifier
    private String subscriptionId;
    private String userId;

    public CreateSubscriptionCommand(String subscriptionId, String userId) {
        this.subscriptionId = subscriptionId;
        this.userId = userId;
    }

    public CreateSubscriptionCommand() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
