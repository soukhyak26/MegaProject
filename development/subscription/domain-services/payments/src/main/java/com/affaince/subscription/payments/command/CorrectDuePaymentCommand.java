package com.affaince.subscription.payments.command;

import com.affaince.subscription.common.type.DeliveryStatus;
import org.joda.time.LocalDate;


public class CorrectDuePaymentCommand {
    private String subscriptionId;
    private String basketId;
    private DeliveryStatus basketDeliveryStatus;
    private LocalDate dispatchDate;
    private double deliveryCharges;
    private double totalDeliveryPrice;

    public CorrectDuePaymentCommand(String subscriptionId, String basketId, DeliveryStatus basketDeliveryStatus, LocalDate dispatchDate, double deliveryCharges, double totalDeliveryPrice) {
        this.subscriptionId = subscriptionId;
        this.basketId = basketId;
        this.basketDeliveryStatus = basketDeliveryStatus;
        this.dispatchDate = dispatchDate;
        this.deliveryCharges = deliveryCharges;
        this.totalDeliveryPrice = totalDeliveryPrice;
    }

    public CorrectDuePaymentCommand() {
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

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public double getDeliveryCharges() {
        return deliveryCharges;
    }

    public double getTotalDeliveryPrice() {
        return totalDeliveryPrice;
    }
}
