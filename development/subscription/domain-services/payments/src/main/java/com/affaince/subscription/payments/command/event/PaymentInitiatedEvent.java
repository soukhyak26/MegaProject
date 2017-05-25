package com.affaince.subscription.payments.command.event;


import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by anayonkar on 24/8/16.
 */
public class PaymentInitiatedEvent {
    private String subscriptionId;
    private Double paidAmount;
    private Map<String,Double> paymentToBeAdjustedAgainstDeliveries;
    private LocalDate paymentDate;

    public PaymentInitiatedEvent(String subscriptionId, Double paidAmount,Map<String,Double> paymentToBeAdjustedAgainstDeliveries, LocalDate paymentDate) {
        this.subscriptionId = subscriptionId;
        this.paidAmount = paidAmount;
        this.paymentDate=paymentDate;
        this.paymentToBeAdjustedAgainstDeliveries=paymentToBeAdjustedAgainstDeliveries;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public Map<String, Double> getPaymentToBeAdjustedAgainstDeliveries() {
        return paymentToBeAdjustedAgainstDeliveries;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}
