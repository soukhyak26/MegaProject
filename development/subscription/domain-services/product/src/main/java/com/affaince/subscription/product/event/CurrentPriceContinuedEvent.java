package com.affaince.subscription.product.event;

/**
 * Created by mandar on 09-10-2016.
 */
public class CurrentPriceContinuedEvent {
    private final String productId;

    public CurrentPriceContinuedEvent(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
