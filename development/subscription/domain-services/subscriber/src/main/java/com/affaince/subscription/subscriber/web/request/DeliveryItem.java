package com.affaince.subscription.subscriber.web.request;

import com.affaince.subscription.common.type.ProductPricingCategory;

/**
 * Created by rbsavaliya on 23-10-2016.
 */
public class DeliveryItem {
    private String deliveryItemId;
    private int quantity;
    private long quantityInGrms;
    private double deliveryItemOfferedPrice;
    private ProductPricingCategory productPricingCategory;

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
}
