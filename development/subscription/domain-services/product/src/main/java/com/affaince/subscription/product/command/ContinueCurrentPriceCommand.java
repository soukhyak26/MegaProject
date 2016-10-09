package com.affaince.subscription.product.command;

/**
 * Created by mandar on 09-10-2016.
 */
public class ContinueCurrentPriceCommand {

    private final String productId;

    public ContinueCurrentPriceCommand(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
