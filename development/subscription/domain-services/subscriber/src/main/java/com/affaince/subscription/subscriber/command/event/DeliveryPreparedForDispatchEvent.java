package com.affaince.subscription.subscriber.command.event;

/**
 * Created by rbsavaliya on 11-12-2016.
 */
public class DeliveryPreparedForDispatchEvent {
    private String subscriberId;
    private String deliveryId;

    public DeliveryPreparedForDispatchEvent(String subscriberId, String deliveryId) {
        this.subscriberId = subscriberId;
        this.deliveryId = deliveryId;
    }

    public DeliveryPreparedForDispatchEvent() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }
}
