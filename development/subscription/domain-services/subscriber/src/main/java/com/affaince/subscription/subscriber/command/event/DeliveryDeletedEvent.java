package com.affaince.subscription.subscriber.command.event;

/**
 * Created by rbsavaliya on 24-10-2016.
 */
public class DeliveryDeletedEvent {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;

    public DeliveryDeletedEvent(String subscriberId, String subscriptionId, String deliveryId) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
