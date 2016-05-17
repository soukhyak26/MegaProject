package com.affaince.subscription.business.command;

/**
 * Created by anayonkar on 8/5/16.
 */
public class SubscriptionActivatedCommand {
    private String subscriptionId;
    private double totalSubscriptionAmountAfterDiscount;
    private double totalDiscount;

    public SubscriptionActivatedCommand(String subscriptionId, double totalSubscriptionAmountAfterDiscount, double totalDiscount) {
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
