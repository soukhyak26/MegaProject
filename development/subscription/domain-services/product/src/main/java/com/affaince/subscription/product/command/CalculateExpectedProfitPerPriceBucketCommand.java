package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.lang.annotation.Target;

/**
 * Created by mandar on 29-12-2016.
 */
public class CalculateExpectedProfitPerPriceBucketCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final String priceBucketId;

    public CalculateExpectedProfitPerPriceBucketCommand(String productId, String priceBucketId) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }
}
