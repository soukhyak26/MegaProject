package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 06-10-2016.
 */
public class ManualForecastAddedEvent {
    private String productId;

    public ManualForecastAddedEvent(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
