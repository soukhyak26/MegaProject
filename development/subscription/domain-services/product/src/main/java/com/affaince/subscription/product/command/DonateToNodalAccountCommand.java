package com.affaince.subscription.product.command;

/**
 * Created by mandar on 30-01-2017.
 */
public class DonateToNodalAccountCommand {
    private String productId;
    private double weight;

    public DonateToNodalAccountCommand(String productId, double weight) {
        this.productId = productId;
        this.weight = weight;
    }

    public String getProductId() {
        return productId;
    }

    public double getWeight() {
        return weight;
    }
}
