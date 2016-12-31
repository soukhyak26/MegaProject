package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 31-12-2016.
 */
public class UpdateDeliveryCountPerPriceBucketCommand {

    @TargetAggregateIdentifier
    private String productId;
    private String priceBucketId;

    public UpdateDeliveryCountPerPriceBucketCommand(String productId, String priceBucketId) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
    }

    public UpdateDeliveryCountPerPriceBucketCommand() {
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }
}
