package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.payments.vo.Delivery;

/**
 * Created by mandar on 6/5/2017.
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
