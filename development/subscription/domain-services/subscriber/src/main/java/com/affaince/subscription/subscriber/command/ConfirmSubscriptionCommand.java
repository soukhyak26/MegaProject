package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class ConfirmSubscriptionCommand {
    @TargetAggregateIdentifier
    private final String subscriberId;

    public ConfirmSubscriptionCommand(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }
}
