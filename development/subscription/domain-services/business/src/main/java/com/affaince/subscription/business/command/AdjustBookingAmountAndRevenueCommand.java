package com.affaince.subscription.business.command;

import com.affaince.subscription.command.ItemDispatchStatus;

import java.util.List;

public class AdjustBookingAmountAndRevenueCommand {
    private String subscriptionId;
    private String basketId;
    private Integer basketDeliveryStatus;
    private String dispatchDate;
    private List<ItemDispatchStatus> itemDispatchStatuses;
    private Double deliveryCharges;
    private Double totalDeliveryPrice;

    public AdjustBookingAmountAndRevenueCommand(String subscriptionId, String basketId, Integer basketDeliveryStatus, String dispatchDate, List<ItemDispatchStatus> itemDispatchStatuses, Double deliveryCharges, Double totalDeliveryPrice) {
        this.subscriptionId = subscriptionId;
        this.basketId = basketId;
        this.basketDeliveryStatus = basketDeliveryStatus;
        this.dispatchDate = dispatchDate;
        this.itemDispatchStatuses = itemDispatchStatuses;
        this.deliveryCharges = deliveryCharges;
        this.totalDeliveryPrice = totalDeliveryPrice;
    }

    public AdjustBookingAmountAndRevenueCommand(Double deliveryCharges, Double totalDeliveryPrice) {
        this.deliveryCharges = deliveryCharges;
        this.totalDeliveryPrice = totalDeliveryPrice;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getBasketId() {
        return basketId;
    }

    public Integer getBasketDeliveryStatus() {
        return basketDeliveryStatus;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public List<ItemDispatchStatus> getItemDispatchStatuses() {
        return itemDispatchStatuses;
    }

    public Double getDeliveryCharges() {
        return deliveryCharges;
    }

    public Double getTotalDeliveryPrice() {
        return totalDeliveryPrice;
    }
}