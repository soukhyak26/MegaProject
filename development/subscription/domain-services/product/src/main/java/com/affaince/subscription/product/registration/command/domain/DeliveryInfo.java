package com.affaince.subscription.product.registration.command.domain;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class DeliveryInfo {
    private String deliveryId;
    private LocalDate deliveryDate;
    private double deliveryWeight;

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getDeliveryWeight() {
        return deliveryWeight;
    }

    public void setDeliveryWeight(double deliveryWeight) {
        this.deliveryWeight = deliveryWeight;
    }
}
