package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 7/13/2017.
 */
public class DeliveryStatusUpdatedToPaymentAccountEvent {
    private String subscriptionId;
    private String deliveryId;
    private LocalDate deliveryDate;
    private DeliveryStatus deliveryStatus;
    private List<ItemDispatchStatus> itemWiseDispatchStatus;
    private double deliveryCharges;

    public DeliveryStatusUpdatedToPaymentAccountEvent(String subscriptionId, String deliveryId, LocalDate deliveryDate, DeliveryStatus deliveryStatus, List<ItemDispatchStatus> itemWiseDispatchStatus, double deliveryCharges) {
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
        this.itemWiseDispatchStatus = itemWiseDispatchStatus;
        this.deliveryCharges = deliveryCharges;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public List<ItemDispatchStatus> getItemWiseDispatchStatus() {
        return itemWiseDispatchStatus;
    }

    public double getDeliveryCharges() {
        return deliveryCharges;
    }
}
