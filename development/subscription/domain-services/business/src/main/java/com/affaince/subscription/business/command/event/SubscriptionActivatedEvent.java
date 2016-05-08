package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
//TODO : Remove
public class SubscriptionActivatedEvent {
    private String subscriptionId;
    private double totalSubscriptionAmountAfterDiscount;
    private double totalDiscount;
    private double subscriptionAmountPaid;

    public SubscriptionActivatedEvent(String subscriptionId, double totalSubscriptionAmountAfterDiscount, double totalDiscount, double subscriptionAmountPaid) {
        this.subscriptionId = subscriptionId;
        this.totalSubscriptionAmountAfterDiscount = totalSubscriptionAmountAfterDiscount;
        this.totalDiscount = totalDiscount;
        this.subscriptionAmountPaid = subscriptionAmountPaid;
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

    public double getSubscriptionAmountPaid() {
        return subscriptionAmountPaid;
    }

    public void setSubscriptionAmountPaid(double subscriptionAmountPaid) {
        this.subscriptionAmountPaid = subscriptionAmountPaid;
    }
}
