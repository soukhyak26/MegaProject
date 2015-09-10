package com.affaince.subscription.consumerbasket.command.event;

/**
 * Created by rbsavaliya on 10-09-2015.
 */
public class ItemRemovedEvent {

    private String basketId;
    private String itemId;

    public ItemRemovedEvent(String basketId, String itemId) {
        this.basketId = basketId;
        this.itemId = itemId;
    }

    public ItemRemovedEvent() {
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
