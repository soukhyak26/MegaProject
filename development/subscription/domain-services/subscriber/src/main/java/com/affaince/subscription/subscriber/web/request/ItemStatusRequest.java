package com.affaince.subscription.subscriber.web.request;

import com.affaince.subscription.common.type.DeliveryStatus;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
public class ItemStatusRequest {

    private String itemId;
    private DeliveryStatus itemDeliveryStatus;

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
