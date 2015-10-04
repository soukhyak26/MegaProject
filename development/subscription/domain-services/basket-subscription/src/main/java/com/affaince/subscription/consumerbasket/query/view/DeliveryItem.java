package com.affaince.subscription.consumerbasket.query.view;

import com.affaince.subscription.common.type.DeliveryStatus;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class DeliveryItem {

    private String deliveryItemId;
    private DeliveryStatus deliveryStatus;

    public DeliveryItem(String deliveryItemId, DeliveryStatus deliveryStatus) {
        this.deliveryItemId = deliveryItemId;
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryItemId() {
        return deliveryItemId;
    }

    public void setDeliveryItemId(String deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
