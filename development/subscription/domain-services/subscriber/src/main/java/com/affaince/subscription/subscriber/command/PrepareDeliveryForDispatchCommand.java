package com.affaince.subscription.subscriber.command;

import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by rbsavaliya on 11-12-2016.
 */
public class PrepareDeliveryForDispatchCommand {
    @AggregateIdentifier
    private String subscriberId;
    private String deliveryId;

    public PrepareDeliveryForDispatchCommand(String subscriberId, String deliveryId) {
        this.subscriberId = subscriberId;
        this.deliveryId = deliveryId;
    }

    public PrepareDeliveryForDispatchCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }
}
