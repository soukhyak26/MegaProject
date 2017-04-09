package com.affaince.subscription.subscriber.web.request;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.subscriber.command.domain.LatestPriceBucket;

/**
 * Created by rbsavaliya on 23-10-2016.
 */
public class DeliveryItem {
    private String deliveryItemId;
    private int quantity;
    private long quantityInGrms;
    private double deliveryItemOfferedPrice;
    private ProductPricingCategory productPricingCategory;
    private String priceBucketId;
    private double offeredPricePerUnit;

    public String getDeliveryItemId() {
        return deliveryItemId;
    }

    public void setDeliveryItemId(String deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getQuantityInGrms() {
        return quantityInGrms;
    }

    public void setQuantityInGrms(long quantityInGrms) {
        this.quantityInGrms = quantityInGrms;
    }

    public double getDeliveryItemOfferedPrice() {
        return deliveryItemOfferedPrice;
    }

    public void setDeliveryItemOfferedPrice(double deliveryItemOfferedPrice) {
        this.deliveryItemOfferedPrice = deliveryItemOfferedPrice;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
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
}
