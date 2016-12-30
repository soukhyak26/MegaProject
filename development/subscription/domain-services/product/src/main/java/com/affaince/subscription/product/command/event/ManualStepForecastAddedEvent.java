package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 07-10-2016.
 */
public class ManualStepForecastAddedEvent {
    private String productId;

    public ManualStepForecastAddedEvent(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
