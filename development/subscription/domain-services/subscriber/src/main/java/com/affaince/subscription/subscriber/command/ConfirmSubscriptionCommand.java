package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class ConfirmSubscriptionCommand {
    @TargetAggregateIdentifier
    private final String subscriptionId;

    public ConfirmSubscriptionCommand(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
