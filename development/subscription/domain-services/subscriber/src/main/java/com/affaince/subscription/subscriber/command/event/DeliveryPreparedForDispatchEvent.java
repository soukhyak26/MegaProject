package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.subscriber.command.domain.Delivery;

/**
 * Created by rbsavaliya on 11-12-2016.
 */
public class DeliveryPreparedForDispatchEvent {
    private String subscriberId;
    private Delivery delivery;

    public DeliveryPreparedForDispatchEvent(String subscriberId, Delivery delivery) {
        this.subscriberId = subscriberId;
        this.delivery = delivery;
    }

    public DeliveryPreparedForDispatchEvent() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public Delivery getDelivery() {
        return delivery;
    }
}
