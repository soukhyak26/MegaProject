package com.affaince.subscription.payments.event;

public class DeliveryDeletedEvent {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private int sequence;

    public DeliveryDeletedEvent() {
    }

    public DeliveryDeletedEvent(String subscriberId,String subscriptionId, String deliveryId, int sequence) {
        this.subscriberId = subscriberId;
        this.subscriptionId=subscriptionId;
        this.deliveryId = deliveryId;
        this.sequence=sequence;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public int getSequence() {
        return sequence;
    }
}
