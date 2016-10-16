package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 16-10-2016.
 */
public class OpeningPriceOrPercentRegisteredEvent {

    private String productId;
    private double openingPriceOrPercent;

    public OpeningPriceOrPercentRegisteredEvent(String productId, double openingPriceOrPercent) {
        this.productId = productId;
        this.openingPriceOrPercent = openingPriceOrPercent;
    }

    public String getProductId() {
        return productId;
    }

    public double getOpeningPriceOrPercent() {
        return openingPriceOrPercent;
    }
}
