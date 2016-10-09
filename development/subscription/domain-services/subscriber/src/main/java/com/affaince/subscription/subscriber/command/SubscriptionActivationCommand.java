package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
public class SubscriptionActivationCommand {
    @TargetAggregateIdentifier
    private String subscriberId;

    public SubscriptionActivationCommand(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public SubscriptionActivationCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }
}
