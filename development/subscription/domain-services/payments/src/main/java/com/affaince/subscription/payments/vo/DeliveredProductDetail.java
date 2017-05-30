package com.affaince.subscription.payments.vo;

import com.affaince.subscription.common.type.ProductPricingCategory;


public class DeliveredProductDetail {
    private String deliveryItemId;
    private String priceBucketId;
    private double deliveryCharges;
    private double offeredPricePerUnitAtSubscription;
    private double offeredPricePerUnitAtDispatch;
    private double purchasePriceAtSubscription;
    private double purchasePriceAtDispatch;
    private double MRPAtSubscription;
    private double MRPAtDispatch;
    private ProductPricingCategory productPricingCategory;


    public DeliveredProductDetail(String deliveryItemId){
        this.deliveryItemId = deliveryItemId;
    }
    public DeliveredProductDetail(String deliveryItemId, String priceBucketId) {
        this.deliveryItemId = deliveryItemId;
        this.priceBucketId = priceBucketId;
    }

    public String getDeliveryItemId() {
        return deliveryItemId;
    }

    public void setDeliveryItemId(String deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }


    public double getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(double deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }

    public double getOfferedPricePerUnitAtSubscription() {
        return offeredPricePerUnitAtSubscription;
    }

    public void setOfferedPricePerUnitAtSubscription(double offeredPricePerUnitAtSubscription) {
        this.offeredPricePerUnitAtSubscription = offeredPricePerUnitAtSubscription;
    }

    public double getOfferedPricePerUnitAtDispatch() {
        return offeredPricePerUnitAtDispatch;
    }

    public void setOfferedPricePerUnitAtDispatch(double offeredPricePerUnitAtDispatch) {
        this.offeredPricePerUnitAtDispatch = offeredPricePerUnitAtDispatch;
    }

    public double getPurchasePriceAtSubscription() {
        return purchasePriceAtSubscription;
    }

    public void setPurchasePriceAtSubscription(double purchasePriceAtSubscription) {
        this.purchasePriceAtSubscription = purchasePriceAtSubscription;
    }

    public double getPurchasePriceAtDispatch() {
        return purchasePriceAtDispatch;
    }

    public void setPurchasePriceAtDispatch(double purchasePriceAtDispatch) {
        this.purchasePriceAtDispatch = purchasePriceAtDispatch;
    }

    public double getMRPAtSubscription() {
        return MRPAtSubscription;
    }

    public void setMRPAtSubscription(double MRPAtSubscription) {
        this.MRPAtSubscription = MRPAtSubscription;
    }

    public double getMRPAtDispatch() {
        return MRPAtDispatch;
    }

    public void setMRPAtDispatch(double MRPAtDispatch) {
        this.MRPAtDispatch = MRPAtDispatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveredProductDetail)) return false;

        DeliveredProductDetail that = (DeliveredProductDetail) o;

        return deliveryItemId.equals(that.deliveryItemId);

    }

    @Override
    public int hashCode() {
        return deliveryItemId.hashCode();
    }
}
