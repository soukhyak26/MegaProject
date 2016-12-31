package com.affaince.subscription.product.command.event;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ReasonCode;

import java.util.List;

/**
 * Created by rbsavaliya on 10-10-2015.
 */
public class DeliveryStatusAndDispatchDateUpdatedEvent {
    private String subscriptionId;
    private String deliveryId;
    private DeliveryStatus deliveryStatus;
    private String dispatchDate;
    private List<ItemDispatchStatus> itemDispatchStatuses;
    private double deliveryCharges;
    private double totalDeliveryPrice;
    private ReasonCode reasonCode;

    public DeliveryStatusAndDispatchDateUpdatedEvent(String subscriptionId, String deliveryId, DeliveryStatus deliveryStatus, String dispatchDate, List<ItemDispatchStatus> itemDispatchStatuses, double deliveryCharges, double totalDeliveryPrice, ReasonCode reasonCode) {
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.deliveryStatus = deliveryStatus;
        this.dispatchDate = dispatchDate;
        this.itemDispatchStatuses = itemDispatchStatuses;
        this.deliveryCharges = deliveryCharges;
        this.totalDeliveryPrice = totalDeliveryPrice;
        this.reasonCode = reasonCode;
    }

    public DeliveryStatusAndDispatchDateUpdatedEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
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

    public ReasonCode getReasonCode() {
        return reasonCode;
    }
}
