package com.affaince.subscription.consumerbasket.command;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
public class ItemDispatchStatus {

    private String itemId;
    private int itemDeliveryStatus;

    public ItemDispatchStatus(String itemId, int itemDeliveryStatus) {
        this.itemId = itemId;
        this.itemDeliveryStatus = itemDeliveryStatus;
    }

    public ItemDispatchStatus() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getItemDeliveryStatus() {
        return itemDeliveryStatus;
    }

    public void setItemDeliveryStatus(int itemDeliveryStatus) {
        this.itemDeliveryStatus = itemDeliveryStatus;
    }
}
