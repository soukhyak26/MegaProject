package com.affaince.subscription.payments.command.event;

public class DeliveryDeletedEvent {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;

    public DeliveryDeletedEvent() {
    }

    public DeliveryDeletedEvent(String subscriberId,String subscriptionId, String deliveryId) {
        this.subscriberId = subscriberId;
        this.subscriptionId=subscriptionId;
        this.deliveryId = deliveryId;
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
}
