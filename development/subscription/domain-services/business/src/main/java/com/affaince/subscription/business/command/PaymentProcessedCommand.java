package com.affaince.subscription.business.command;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 8/5/16.
 */
public class PaymentProcessedCommand {
    private String subscriptionId;
    private String subscriberId;
    private Double paymentAmount;
    private LocalDate paymentDate;

    public PaymentProcessedCommand(String subscriptionId, String subscriberId, Double paymentAmount, LocalDate paymentDate) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public PaymentProcessedCommand(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}
