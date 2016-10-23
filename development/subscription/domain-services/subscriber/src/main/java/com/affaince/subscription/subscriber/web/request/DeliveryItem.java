package com.affaince.subscription.subscriber.web.request;

/**
 * Created by rbsavaliya on 23-10-2016.
 */
public class DeliveryItem {
    private String deliveryItemId;
    private int quantity;

    public String getDeliveryItemId() {
        return deliveryItemId;
    }

    public void setDeliveryItemId(String deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
