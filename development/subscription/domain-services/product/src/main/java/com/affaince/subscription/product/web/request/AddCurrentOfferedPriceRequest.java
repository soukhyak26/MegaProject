package com.affaince.subscription.product.web.request;

public class AddCurrentOfferedPriceRequest {

    private double currentOfferedPriceOrPercent;

    public double getCurrentOfferedPriceOrPercent() {
        return currentOfferedPriceOrPercent;
    }

    public void setCurrentOfferedPriceOrPercent(double currentOfferedPriceOrPercent) {
        this.currentOfferedPriceOrPercent = currentOfferedPriceOrPercent;
    }
}
