package com.affaince.subscription.payments.command;

/**
 * Created by anayonkar on 20/11/16.
 */
public class DeliveryDeletedCommand {
    private String subscriberId;
    private String deliveryId;

    public DeliveryDeletedCommand() {
    }

    public DeliveryDeletedCommand(String subscriberId, String deliveryId) {
        this.subscriberId = subscriberId;
        this.deliveryId = deliveryId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }
}
