package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class CreateBasketCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String basketId;
    private List<String> deliveryItems;
    private String deliveryDate;

    public CreateBasketCommand(String subscriberId, String basketId, List<String> deliveryItems, String deliveryDate) {
        this.subscriberId = subscriberId;
        this.basketId = basketId;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
    }

    public CreateBasketCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getBasketId() {
        return basketId;
    }

    public List<String> getDeliveryItems() {
        return deliveryItems;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }
}
