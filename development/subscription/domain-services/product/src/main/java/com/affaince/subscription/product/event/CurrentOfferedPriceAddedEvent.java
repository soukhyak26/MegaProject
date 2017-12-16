package com.affaince.subscription.product.event;

/**
 * Created by rbsavaliya on 07-08-2015.
 */
public class CurrentOfferedPriceAddedEvent {

    private String productId;
    private double currentOfferedPrice;

    public CurrentOfferedPriceAddedEvent(String productId, double currentOfferedPrice) {
        this.productId = productId;
        this.currentOfferedPrice = currentOfferedPrice;
    }

    public CurrentOfferedPriceAddedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getCurrentOfferedPrice() {
        return currentOfferedPrice;
    }

    public void setCurrentOfferedPrice(double currentOfferedPrice) {
        this.currentOfferedPrice = currentOfferedPrice;
    }
}
