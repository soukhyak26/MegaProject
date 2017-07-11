package com.affaince.subscription.payments.command;

/**
 * Created by mandar on 7/11/2017.
 */
public class ApproveDeliveryDispatchCommand {
    private String subscriptionId;
    private String deliveryId;
    private int sequence;

    public ApproveDeliveryDispatchCommand(String subscriptionId, String deliveryId, int sequence) {
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.sequence = sequence;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public int getSequence() {
        return sequence;
    }
}
