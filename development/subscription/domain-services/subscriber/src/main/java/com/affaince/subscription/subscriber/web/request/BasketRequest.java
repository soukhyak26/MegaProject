package com.affaince.subscription.subscriber.web.request;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class BasketRequest {
    private String[] deliveryItems;
    private String deliveryDate;

    public String[] getDeliveryItems() {
        return deliveryItems;
    }

    public void setDeliveryItems(String[] deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
