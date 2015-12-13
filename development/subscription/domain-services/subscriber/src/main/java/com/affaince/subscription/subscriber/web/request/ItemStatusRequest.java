package com.affaince.subscription.subscriber.web.request;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
public class ItemStatusRequest {

    private String itemId;
    private int itemDeliveryStatus;

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
