package com.affaince.subscription.subscriber.query.repository;

/**
 * Created by rbsavaliya on 16-07-2016.
 */
public class Product {

    private String deliveryItemId;
    private double weightInGrms;
    private double offeredPriceWithBasketLevelDiscount;

    public String getDeliveryItemId() {
        return deliveryItemId;
    }

    public void setDeliveryItemId(String deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    public double getWeightInGrms() {
        return weightInGrms;
    }

    public void setWeightInGrms(double weightInGrms) {
        this.weightInGrms = weightInGrms;
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
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return deliveryItemId.equals(product.deliveryItemId);

    }

    @Override
    public int hashCode() {
        return deliveryItemId.hashCode();
    }
}
