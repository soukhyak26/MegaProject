package com.affaince.subscription.subscriber.command.event;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
public class SubscriptionActivatedEvent {
    private String subscriptionId;
    private double totalSubscriptionAmountAfterDiscount;
    private double totalDiscount;

    public SubscriptionActivatedEvent(String subscriptionId, double totalSubscriptionAmountAfterDiscount, double totalDiscount) {
        this.subscriptionId = subscriptionId;
        this.totalSubscriptionAmountAfterDiscount = totalSubscriptionAmountAfterDiscount;
        this.totalDiscount = totalDiscount;
    }

    public SubscriptionActivatedEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public double getTotalSubscriptionAmountAfterDiscount() {
        return totalSubscriptionAmountAfterDiscount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }
}
