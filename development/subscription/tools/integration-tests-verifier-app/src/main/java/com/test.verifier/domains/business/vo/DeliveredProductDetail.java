package com.test.verifier.domains.business.vo;

import com.affaince.subscription.common.type.ProductPricingCategory;


public class DeliveredProductDetail {
    private String deliveryItemId;
    private String priceBucketId;
    private double deliveryCharges;
    private double offeredPriceOrPercent;
    private double offeredPricePerUnitOld;
    private double offeredPricePerUnitNew;
    private double purchasePriceOld;
    private double purchasePriceNew;
    private double MRPOld;
    private double MRPNew;
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

    public double getOfferedPricePerUnitOld() {
        return offeredPricePerUnitOld;
    }

    public void setOfferedPricePerUnitOld(double offeredPricePerUnitOld) {
        this.offeredPricePerUnitOld = offeredPricePerUnitOld;
    }

    public double getOfferedPricePerUnitNew() {
        return offeredPricePerUnitNew;
    }

    public void setOfferedPricePerUnitNew(double offeredPricePerUnitNew) {
        this.offeredPricePerUnitNew = offeredPricePerUnitNew;
    }

    public double getPurchasePriceOld() {
        return purchasePriceOld;
    }

    public void setPurchasePriceOld(double purchasePriceOld) {
        this.purchasePriceOld = purchasePriceOld;
    }

    public double getPurchasePriceNew() {
        return purchasePriceNew;
    }

    public void setPurchasePriceNew(double purchasePriceNew) {
        this.purchasePriceNew = purchasePriceNew;
    }

    public double getMRPOld() {
        return MRPOld;
    }

    public void setMRPOld(double MRPOld) {
        this.MRPOld = MRPOld;
    }

    public double getMRPNew() {
        return MRPNew;
    }

    public void setMRPNew(double MRPNew) {
        this.MRPNew = MRPNew;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public double getOfferedPriceOrPercent() {
        return offeredPriceOrPercent;
    }

    public void setOfferedPriceOrPercent(double offeredPriceOrPercent) {
        this.offeredPriceOrPercent = offeredPriceOrPercent;
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
