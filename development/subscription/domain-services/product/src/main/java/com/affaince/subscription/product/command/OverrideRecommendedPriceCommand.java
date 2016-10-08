package com.affaince.subscription.product.command;

/**
 * Created by mandar on 08-10-2016.
 */
public class OverrideRecommendedPriceCommand {
    private final String productId;
    private final double overriddenPrice;

    public OverrideRecommendedPriceCommand(String productId, double overriddenPrice) {
        this.productId = productId;
        this.overriddenPrice = overriddenPrice;
    }

    public String getProductId() {
        return productId;
    }

    public double getOverriddenPrice() {
        return overriddenPrice;
    }
}
