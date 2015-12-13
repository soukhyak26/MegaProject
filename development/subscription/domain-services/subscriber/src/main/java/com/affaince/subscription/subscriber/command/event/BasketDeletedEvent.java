package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.common.type.DeliveryStatus;

/**
 * Created by rbsavaliya on 11-10-2015.
 */
public class BasketDeletedEvent {
    private String basketId;
    private DeliveryStatus deliveryStatus;

    public BasketDeletedEvent(String basketId, DeliveryStatus deliveryStatus) {
        this.basketId = basketId;
        this.deliveryStatus = deliveryStatus;
    }

    public BasketDeletedEvent() {
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
