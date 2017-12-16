package com.affaince.subscription.subscriber.event;

/**
 * Created by rbsavaliya on 24-10-2016.
 */
public class DeliveryDeletedEvent {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private int sequence;

    public DeliveryDeletedEvent(String subscriberId, String subscriptionId, String deliveryId,int sequence) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.sequence=sequence;
    }

    public DeliveryDeletedEvent() {
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

    public int getSequence() {
        return sequence;
    }
}
