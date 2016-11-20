package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 20/11/16.
 */
public class DeliveryDeletedEvent {
    private String subscriberId;
    private String deliveryId;

    public DeliveryDeletedEvent() {
    }

    public DeliveryDeletedEvent(String subscriberId, String deliveryId) {
        this.subscriberId = subscriberId;
        this.deliveryId = deliveryId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }
}
