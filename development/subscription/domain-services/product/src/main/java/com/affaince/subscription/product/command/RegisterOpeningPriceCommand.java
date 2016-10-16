package com.affaince.subscription.product.command;

/**
 * Created by mandar on 16-10-2016.
 */
public class RegisterOpeningPriceCommand {
    private final String productId;
    private final double openingPrice;

    public RegisterOpeningPriceCommand(String productId, double openingPrice) {
        this.productId = productId;
        this.openingPrice = openingPrice;
    }

    public String getProductId() {
        return productId;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }
}
