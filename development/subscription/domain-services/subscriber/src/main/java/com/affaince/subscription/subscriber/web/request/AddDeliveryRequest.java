package com.affaince.subscription.subscriber.web.request;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 23-10-2016.
 */
public class AddDeliveryRequest {
    private DeliveryItem[] deliveryItems;
    private LocalDate deliveryDate;

    public DeliveryItem[] getDeliveryItems() {
        return deliveryItems;
    }

    public void setDeliveryItems(DeliveryItem[] deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
