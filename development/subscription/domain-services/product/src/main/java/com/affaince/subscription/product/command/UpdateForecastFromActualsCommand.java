package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by mandark on 29-04-2016.
 */
public class UpdateForecastFromActualsCommand {
    @TargetAggregateIdentifier
    private String productId;

    public UpdateForecastFromActualsCommand(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return this.productId;
    }
}
