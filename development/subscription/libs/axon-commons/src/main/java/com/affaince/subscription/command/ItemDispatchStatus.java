package com.affaince.subscription.command;

import com.affaince.subscription.common.type.DeliveryStatus;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
public class ItemDispatchStatus {

    private String itemId;
    private DeliveryStatus itemDeliveryStatus;

    public ItemDispatchStatus(String itemId, DeliveryStatus itemDeliveryStatus) {
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

    public DeliveryStatus getItemDeliveryStatus() {
        return itemDeliveryStatus;
    }

    public void setItemDeliveryStatus(DeliveryStatus itemDeliveryStatus) {
        this.itemDeliveryStatus = itemDeliveryStatus;
    }
}
