package com.affaince.subscription.payments.command;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;

import java.util.List;


public class DeliveryStatusAndDispatchDateUpdatedCommand {
    private String subscriptionId;
    private String basketId;
    private DeliveryStatus basketDeliveryStatus;
    private String dispatchDate;
    private List<ItemDispatchStatus> itemDispatchStatuses;
    private double deliveryCharges;
    private double totalDeliveryPrice;

    public DeliveryStatusAndDispatchDateUpdatedCommand(String subscriptionId, String basketId, DeliveryStatus basketDeliveryStatus, String dispatchDate, List<ItemDispatchStatus> itemDispatchStatuses, double deliveryCharges, double totalDeliveryPrice) {
        this.subscriptionId = subscriptionId;
        this.basketId = basketId;
        this.basketDeliveryStatus = basketDeliveryStatus;
        this.dispatchDate = dispatchDate;
        this.itemDispatchStatuses = itemDispatchStatuses;
        this.deliveryCharges = deliveryCharges;
        this.totalDeliveryPrice = totalDeliveryPrice;
    }

    public DeliveryStatusAndDispatchDateUpdatedCommand() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getBasketId() {
        return basketId;
    }

    public DeliveryStatus getBasketDeliveryStatus() {
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
