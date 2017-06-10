package com.affaince.subscription.payments.command;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.payments.vo.DeliveryItem;
import org.joda.time.LocalDate;

import java.util.List;

public class CreateDeliveryCommand {
    private String deliveryId;
    private String subscriberId;
    private String subscriptionId;
    private List<DeliveryItem> deliveryItems;
    private LocalDate deliveryDate;
    private LocalDate dispatchDate;
    private DeliveryStatus status;
    private double deliveryWeightInGrms;

    public CreateDeliveryCommand(String deliveryId, String subscriberId, String subscriptionId, List<DeliveryItem> deliveryItems, LocalDate deliveryDate, LocalDate dispatchDate, DeliveryStatus status, double deliveryWeightInGrms) {
        this.deliveryId = deliveryId;
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.dispatchDate = dispatchDate;
        this.status = status;
        this.deliveryWeightInGrms = deliveryWeightInGrms;
    }

    public CreateDeliveryCommand() {
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public List<DeliveryItem> getDeliveryItems() {
        return deliveryItems;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public double getDeliveryWeightInGrms() {
        return deliveryWeightInGrms;
    }
}