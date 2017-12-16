package com.affaince.subscription.payments.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 7/16/2017.
 */
public class RefundProcessedForDeletedDeliveriesEvent {
    private String subscriptionId;
    private String deliveryId;
    private int deliverySequence;
    private double paymentReceived;
    private LocalDate refundProcessingDate;

    public RefundProcessedForDeletedDeliveriesEvent(String subscriptionId, String deliveryId, int deliverySequence,double paymentReceived, LocalDate refundProcessingDate) {
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.deliverySequence=deliverySequence;
        this.paymentReceived = paymentReceived;
        this.refundProcessingDate = refundProcessingDate;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public int getDeliverySequence() {
        return deliverySequence;
    }

    public double getPaymentReceived() {
        return paymentReceived;
    }

    public LocalDate getRefundProcessingDate() {
        return refundProcessingDate;
    }
}
