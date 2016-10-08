package com.affaince.subscription.product.command;

/**
 * Created by mandar on 08-10-2016.
 */
public class AcceptRecommendedPriceCommand {
    private final String productId;

    public AcceptRecommendedPriceCommand(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
