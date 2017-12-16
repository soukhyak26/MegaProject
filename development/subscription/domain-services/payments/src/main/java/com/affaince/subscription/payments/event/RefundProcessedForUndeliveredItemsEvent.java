package com.affaince.subscription.payments.event;

import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by mandar on 7/13/2017.
 */
public class RefundProcessedForUndeliveredItemsEvent {
    private String subscriptionId;
    private String deliveryId;
    private double totalRefundableAmount;
    private Map<String, Double> itemWiseRefundAmountDetails;
    private LocalDate refundDate;

    public RefundProcessedForUndeliveredItemsEvent(String subscriptionId, String deliveryId, double totalRefundableAmount, Map<String, Double> itemWiseRefundAmountDetails,LocalDate refundDate) {
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.totalRefundableAmount = totalRefundableAmount;
        this.itemWiseRefundAmountDetails = itemWiseRefundAmountDetails;
        this.refundDate=refundDate;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public double getTotalRefundableAmount() {
        return totalRefundableAmount;
    }

    public Map<String, Double> getItemWiseRefundAmountDetails() {
        return itemWiseRefundAmountDetails;
    }

    public LocalDate getRefundDate() {
        return refundDate;
    }
}
