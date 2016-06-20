package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.type.DeliveryStatus;

/**
 * Created by rsavaliya on 18/1/16.
 */
public class DeliveryItemInfoView {
    private String deliveryItemId;
    private DeliveryStatus deliveryStatus;
    private double weightInGrms;

    public DeliveryItemInfoView(String deliveryItemId, DeliveryStatus deliveryStatus, double weightInGrms) {
        this.deliveryItemId = deliveryItemId;
        this.deliveryStatus = deliveryStatus;
        this.weightInGrms = weightInGrms;
    }

    public DeliveryItemInfoView() {
    }

    public String getDeliveryItemId() {
        return deliveryItemId;
    }

    public void setDeliveryItemId(String deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public double getWeightInGrms() {
        return weightInGrms;
    }

    public void setWeightInGrms(double weightInGrms) {
        this.weightInGrms = weightInGrms;
    }
}
