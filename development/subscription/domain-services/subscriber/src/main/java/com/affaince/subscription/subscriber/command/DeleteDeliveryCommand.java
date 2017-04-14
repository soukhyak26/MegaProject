package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 24-10-2016.
 */
public class DeleteDeliveryCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;

    public DeleteDeliveryCommand(String subscriberId, String subscriptionId, String deliveryId) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
    }

    public DeleteDeliveryCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }
}
