package com.affaince.subscription.product.registration.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 07-08-2015.
 */
public class AddCurrentOfferedPriceCommand {
    @TargetAggregateIdentifier
    private String productId;
    private double currentOfferedPrice;

    public AddCurrentOfferedPriceCommand(String productId, double currentOfferedPrice) {
        this.productId = productId;
        this.currentOfferedPrice = currentOfferedPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getCurrentOfferedPrice() {
        return currentOfferedPrice;
    }

    public void setCurrentOfferedPrice(double currentOfferedPrice) {
        this.currentOfferedPrice = currentOfferedPrice;
    }
}
