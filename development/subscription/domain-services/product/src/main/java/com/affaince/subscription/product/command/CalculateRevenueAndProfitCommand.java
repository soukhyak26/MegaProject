package com.affaince.subscription.product.command;

/**
 * Created by mandar on 28-01-2017.
 */
public class CalculateRevenueAndProfitCommand {
    private String productId;

    public CalculateRevenueAndProfitCommand(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
