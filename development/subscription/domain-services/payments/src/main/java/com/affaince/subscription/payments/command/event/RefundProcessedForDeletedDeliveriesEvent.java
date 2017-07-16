package com.affaince.subscription.payments.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 7/16/2017.
 */
public class RefundProcessedForDeletedDeliveriesEvent {
    private String subscriptionId;
    private String deliveryId;
    private double paymentReceived;
    private LocalDate refundProcessingDate;

    public RefundProcessedForDeletedDeliveriesEvent(String subscriptionId, String deliveryId, double paymentReceived, LocalDate refundProcessingDate) {
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.paymentReceived = paymentReceived;
        this.refundProcessingDate = refundProcessingDate;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public double getPaymentReceived() {
        return paymentReceived;
    }

    public LocalDate getRefundProcessingDate() {
        return refundProcessingDate;
    }
}
