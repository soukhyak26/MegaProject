package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 20/11/16.
 */
public class DeliveryDestroyedEvent {
    private String deliveryId;
    private String subscriptionId;

    public DeliveryDestroyedEvent() {
    }

    public DeliveryDestroyedEvent(String deliveryId, String subscriptionId) {
        this.deliveryId = deliveryId;
        this.subscriptionId = subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
