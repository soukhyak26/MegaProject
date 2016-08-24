package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 24/8/16.
 */
public class DeliveryInitiatedEvent {
    private String deliveryId;
    private String subscriptionId;

    public DeliveryInitiatedEvent(String deliveryId, String subscriptionId) {
        this.deliveryId = deliveryId;
        this.subscriptionId = subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
