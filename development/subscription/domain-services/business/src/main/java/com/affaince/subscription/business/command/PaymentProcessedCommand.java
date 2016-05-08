package com.affaince.subscription.business.command;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 8/5/16.
 */
public class PaymentProcessedCommand {
    private String subscriptionId;
    private String subscriberId;
    private double paymentAmount;
    private LocalDate paymentDate;

    public PaymentProcessedCommand(String subscriptionId, String subscriberId, double paymentAmount, LocalDate paymentDate) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}
