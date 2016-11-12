package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 12-11-2016.
 */
public class ProductActivateCommand {

    @TargetAggregateIdentifier
    private String productId;

    public ProductActivateCommand(String productId) {
        this.productId = productId;
    }

    public ProductActivateCommand() {
    }

    public String getProductId() {
        return productId;
    }
}
