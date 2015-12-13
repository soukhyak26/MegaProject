package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class CreateBasketCommand {
    @TargetAggregateIdentifier
    private String basketId;
    private List<String> deliveryItems;
    private String deliveryDate;

    public CreateBasketCommand(String basketId, List<String> deliveryItems, String deliveryDate) {
        this.basketId = basketId;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public List<String> getDeliveryItems() {
        return deliveryItems;
    }

    public void setDeliveryItems(List<String> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
