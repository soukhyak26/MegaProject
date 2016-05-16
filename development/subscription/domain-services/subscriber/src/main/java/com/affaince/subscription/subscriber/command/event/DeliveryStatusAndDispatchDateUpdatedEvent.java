package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.subscriber.command.ItemDispatchStatus;

import java.util.List;

/**
 * Created by rbsavaliya on 10-10-2015.
 */
public class DeliveryStatusAndDispatchDateUpdatedEvent {
    private String subscriptionId;
    private String basketId;
    private int basketDeliveryStatus;
    private String dispatchDate;
    private List<ItemDispatchStatus> itemDispatchStatuses;
    private double deliveryCharges;
    private double totalDeliveryPrice;

    public DeliveryStatusAndDispatchDateUpdatedEvent(String subscriptionId, String basketId, int basketDeliveryStatus, String dispatchDate, List<ItemDispatchStatus> itemDispatchStatuses, double deliveryCharges, double totalDeliveryPrice) {
        this.subscriptionId = subscriptionId;
        this.basketId = basketId;
        this.basketDeliveryStatus = basketDeliveryStatus;
        this.dispatchDate = dispatchDate;
        this.itemDispatchStatuses = itemDispatchStatuses;
        this.deliveryCharges = deliveryCharges;
        this.totalDeliveryPrice = totalDeliveryPrice;
    }

    public DeliveryStatusAndDispatchDateUpdatedEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getBasketId() {
        return basketId;
    }

    public int getBasketDeliveryStatus() {
        return basketDeliveryStatus;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public List<ItemDispatchStatus> getItemDispatchStatuses() {
        return itemDispatchStatuses;
    }

    public double getDeliveryCharges() {
        return deliveryCharges;
    }

    public double getTotalDeliveryPrice() {
        return totalDeliveryPrice;
    }
}
