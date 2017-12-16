package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class SubscriptionActivatedEvent {
    private String subscriptionId;
    private double totalSubscriptionAmountAfterDiscount;
    private double totalDiscount;

    public SubscriptionActivatedEvent() {
    }

    public SubscriptionActivatedEvent(String subscriptionId, double totalSubscriptionAmountAfterDiscount, double totalDiscount, double subscriptionAmountPaid) {
        this.subscriptionId = subscriptionId;
        this.totalSubscriptionAmountAfterDiscount = totalSubscriptionAmountAfterDiscount;
        this.totalDiscount = totalDiscount;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public double getTotalSubscriptionAmountAfterDiscount() {
        return totalSubscriptionAmountAfterDiscount;
    }

    public void setTotalSubscriptionAmountAfterDiscount(double totalSubscriptionAmountAfterDiscount) {
        this.totalSubscriptionAmountAfterDiscount = totalSubscriptionAmountAfterDiscount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}
