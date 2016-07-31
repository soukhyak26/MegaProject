package com.affaince.subscription.business.command;

/**
 * Created by anayonkar on 8/5/16.
 */
public class SubscriptionActivatedCommand {
    private String subscriptionId;
    private Double totalSubscriptionAmountAfterDiscount;
    private Double totalDiscount;

    public SubscriptionActivatedCommand(String subscriptionId, Double totalSubscriptionAmountAfterDiscount, Double totalDiscount) {
        this.subscriptionId = subscriptionId;
        this.totalSubscriptionAmountAfterDiscount = totalSubscriptionAmountAfterDiscount;
        this.totalDiscount = totalDiscount;
    }

    public SubscriptionActivatedCommand(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Double getTotalSubscriptionAmountAfterDiscount() {
        return totalSubscriptionAmountAfterDiscount;
    }

    public void setTotalSubscriptionAmountAfterDiscount(Double totalSubscriptionAmountAfterDiscount) {
        this.totalSubscriptionAmountAfterDiscount = totalSubscriptionAmountAfterDiscount;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

}
