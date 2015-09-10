package com.affaince.subscription.consumerbasket.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 10-09-2015.
 */
public class DeleteItemCommand {
    @TargetAggregateIdentifier
    private String basketId;
    private String itemId;

    public DeleteItemCommand(String basketId, String itemId) {
        this.basketId = basketId;
        this.itemId = itemId;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
