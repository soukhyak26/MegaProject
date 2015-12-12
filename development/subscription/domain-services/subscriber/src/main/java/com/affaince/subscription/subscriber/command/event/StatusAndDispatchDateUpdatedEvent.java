package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.subscriber.command.ItemDispatchStatus;

import java.util.List;

/**
 * Created by rbsavaliya on 10-10-2015.
 */
public class StatusAndDispatchDateUpdatedEvent {
    private String basketId;
    private int basketDeliveryStatus;
    private String dispatchDate;
    private List<ItemDispatchStatus> itemDispatchStatuses;

    public StatusAndDispatchDateUpdatedEvent(String basketId, int basketDeliveryStatus, String dispatchDate, List<ItemDispatchStatus> itemDispatchStatuses) {
        this.basketId = basketId;
        this.basketDeliveryStatus = basketDeliveryStatus;
        this.dispatchDate = dispatchDate;
        this.itemDispatchStatuses = itemDispatchStatuses;
    }

    public StatusAndDispatchDateUpdatedEvent() {
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public int getBasketDeliveryStatus() {
        return basketDeliveryStatus;
    }

    public void setBasketDeliveryStatus(int basketDeliveryStatus) {
        this.basketDeliveryStatus = basketDeliveryStatus;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public List<ItemDispatchStatus> getItemDispatchStatuses() {
        return itemDispatchStatuses;
    }

    public void setItemDispatchStatuses(List<ItemDispatchStatus> itemDispatchStatuses) {
        this.itemDispatchStatuses = itemDispatchStatuses;
    }
}
