package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 08-10-2016.
 */
public class RecommendedPriceAcceptedEvent {
    private final String productId;

    public RecommendedPriceAcceptedEvent(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
