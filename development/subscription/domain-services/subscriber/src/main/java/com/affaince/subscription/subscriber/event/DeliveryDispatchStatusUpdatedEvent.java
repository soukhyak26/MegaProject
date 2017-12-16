package com.affaince.subscription.subscriber.event;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ReasonCode;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandark on 30-08-2015.
 */
public class DeliveryDispatchStatusUpdatedEvent {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private DeliveryStatus deliveryStatus;
    private LocalDate dispatchDate;
    private List<ItemDispatchStatus> itemDispatchStatuses;
    private double deliveryCharges;
    private double totalDeliveryPrice;
    private ReasonCode reasonCode;

    public DeliveryDispatchStatusUpdatedEvent() {
    }

    public DeliveryDispatchStatusUpdatedEvent(String subscriberId, String subscriptionId, String deliveryId, DeliveryStatus deliveryStatus, LocalDate dispatchDate, List<ItemDispatchStatus> itemDispatchStatuses, double deliveryCharges, double totalDeliveryPrice, ReasonCode reasonCode) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.deliveryStatus = deliveryStatus;
        this.dispatchDate = dispatchDate;
        this.itemDispatchStatuses = itemDispatchStatuses;
        this.deliveryCharges = deliveryCharges;
        this.totalDeliveryPrice = totalDeliveryPrice;
        this.reasonCode = reasonCode;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public DeliveryStatus getBasketDeliveryStatus() {
        return deliveryStatus;
    }

    public LocalDate getDispatchDate() {
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
