package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class CreateSubscriptionCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String subscriptionId;

    public CreateSubscriptionCommand(String subscriberId, String subscriptionId) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
    }

    public CreateSubscriptionCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
