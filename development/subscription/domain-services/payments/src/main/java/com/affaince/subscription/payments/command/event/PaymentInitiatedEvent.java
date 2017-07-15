package com.affaince.subscription.payments.command.event;


import com.affaince.subscription.payments.vo.InstalmentPaymentTracker;
import org.joda.time.LocalDate;

import java.util.Map;

public class PaymentInitiatedEvent {
    private String subscriptionId;
    private Double paidAmount;
    private Map<String,Double> paymentToBeAdjustedAgainstDeliveries;
    private Map<InstalmentPaymentTracker, Double> trackersGettingFulfilledByIncomingPayment;
    private int deliverySequenceFulfilledWithPayment;
    private LocalDate paymentDate;

    public PaymentInitiatedEvent(String subscriptionId, Double paidAmount, Map<String,Double> paymentToBeAdjustedAgainstDeliveries, Map<InstalmentPaymentTracker, Double> trackersGettingFulfilledByIncomingPayment, int deliverySequenceFulfilledWithPayment,LocalDate paymentDate) {
        this.subscriptionId = subscriptionId;
        this.paidAmount = paidAmount;
        this.paymentDate=paymentDate;
        this.paymentToBeAdjustedAgainstDeliveries=paymentToBeAdjustedAgainstDeliveries;
        this.trackersGettingFulfilledByIncomingPayment=trackersGettingFulfilledByIncomingPayment;
        this.deliverySequenceFulfilledWithPayment=deliverySequenceFulfilledWithPayment;
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

    public Map<InstalmentPaymentTracker, Double> getTrackersGettingFulfilledByIncomingPayment() {
        return trackersGettingFulfilledByIncomingPayment;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public int getDeliverySequenceFulfilledWithPayment() {
        return deliverySequenceFulfilledWithPayment;
    }
}
