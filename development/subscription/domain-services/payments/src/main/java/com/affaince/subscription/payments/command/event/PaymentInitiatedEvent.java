package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 24/8/16.
 */
public class PaymentInitiatedEvent {
    private String subscriptionId;
    private Double totalBalance;

    public PaymentInitiatedEvent(String subscriptionId, Double totalBalance) {
        this.subscriptionId = subscriptionId;
        this.totalBalance = totalBalance;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }
}
