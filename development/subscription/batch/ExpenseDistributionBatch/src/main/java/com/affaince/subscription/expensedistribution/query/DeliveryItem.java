package com.affaince.subscription.expensedistribution.query;

import com.affaince.subscription.common.type.DeliveryStatus;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class DeliveryItem {

    private String deliveryItemId;
    private DeliveryStatus deliveryStatus;
    private double weightInGrms;
    private double deliveryCharges;
    private double offeredPriceWithBasketLevelDiscount;

    public DeliveryItem(String deliveryItemId, DeliveryStatus deliveryStatus, double weightInGrms, double offeredPriceWithBasketLevelDiscount) {
        this.deliveryItemId = deliveryItemId;
        this.deliveryStatus = deliveryStatus;
        this.weightInGrms = weightInGrms;
        this.offeredPriceWithBasketLevelDiscount = offeredPriceWithBasketLevelDiscount;
    }

    public DeliveryItem() {
    }

    public DeliveryItem(String deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
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

    public double getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(double deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public double getOfferedPriceWithBasketLevelDiscount() {
        return offeredPriceWithBasketLevelDiscount;
    }

    public void setOfferedPriceWithBasketLevelDiscount(double offeredPriceWithBasketLevelDiscount) {
        this.offeredPriceWithBasketLevelDiscount = offeredPriceWithBasketLevelDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryItem)) return false;

        DeliveryItem that = (DeliveryItem) o;

        return deliveryItemId.equals(that.deliveryItemId);

    }

    @Override
    public int hashCode() {
        return deliveryItemId.hashCode();
    }
}
