package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.subscriber.command.domain.Delivery;

/**
 * Created by rbsavaliya on 11-12-2016.
 */
public class DeliveryPreparedForDispatchEvent {
    private String subscriberId;
    private String subscriptionId;
    private Delivery delivery;

    public DeliveryPreparedForDispatchEvent(String subscriberId, String subscriptionId, Delivery delivery) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
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

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
