package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ReasonCode;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 10-10-2015.
 */
public class DeliveryStatusAndDispatchDateUpdatedEvent {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private DeliveryStatus deliveryStatus;
    private LocalDate dispatchDate;
    private Map<String, String> itemPriceBucketMapping;
    private List<ItemDispatchStatus> itemDispatchStatuses;
    private double deliveryCharges;
    private double totalDeliveryPrice;
    private ReasonCode reasonCode;

    public DeliveryStatusAndDispatchDateUpdatedEvent(String subscriberId, String subscriptionId, String deliveryId, DeliveryStatus deliveryStatus, LocalDate dispatchDate, Map<String, String> itemPriceBucketMapping, List<ItemDispatchStatus> itemDispatchStatuses, double deliveryCharges, double totalDeliveryPrice, ReasonCode reasonCode) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.deliveryStatus = deliveryStatus;
        this.dispatchDate = dispatchDate;
        this.itemPriceBucketMapping = itemPriceBucketMapping;
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

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public List<ItemDispatchStatus> getItemDispatchStatuses() {
        return itemDispatchStatuses;
    }

    public Map<String, String> getItemPriceBucketMapping() {
        return itemPriceBucketMapping;
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

    public String getSubscriberId() {
        return subscriberId;
    }
}
