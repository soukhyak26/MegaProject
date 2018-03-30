package com.verifier.domains.payments.vo;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;

public class DeliveryItem {
    private String deliveryItemId;
    private DeliveryStatus deliveryStatus;
    private double weightInGrms;
    private double deliveryCharges;
    private String priceBucketId;
    private double offeredPricePerUnit;
    private ProductPricingCategory productPricingCategory;

    public DeliveryItem(String deliveryItemId, DeliveryStatus deliveryStatus, double weightInGrms, double deliveryCharges, String priceBucketId, double offeredPricePerUnit, ProductPricingCategory productPricingCategory) {
        this.deliveryItemId = deliveryItemId;
        this.deliveryStatus = deliveryStatus;
        this.weightInGrms = weightInGrms;
        this.deliveryCharges = deliveryCharges;
        this.priceBucketId = priceBucketId;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.productPricingCategory = productPricingCategory;
    }

    public DeliveryItem(String deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    public DeliveryItem() {
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

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public void setPriceBucketId(String priceBucketId) {
        this.priceBucketId = priceBucketId;
    }

    public double getOfferedPricePerUnit() {
        return offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryItem that = (DeliveryItem) o;

        if (!deliveryItemId.equals(that.deliveryItemId)) return false;
        return priceBucketId.equals(that.priceBucketId);

    }

    @Override
    public int hashCode() {
        int result = deliveryItemId.hashCode();
        result = 31 * result + priceBucketId.hashCode();
        return result;
    }
}