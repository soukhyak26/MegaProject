package com.affaince.subscription.subscriber.command.event;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
public class SubscriptionActivatedEvent {
    private String subscriptionId;

    public SubscriptionActivatedEvent(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public SubscriptionActivatedEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

}
