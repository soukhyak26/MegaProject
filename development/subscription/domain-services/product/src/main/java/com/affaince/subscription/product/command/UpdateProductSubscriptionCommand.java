package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by mandar on 19-06-2016.
 */
public class UpdateProductSubscriptionCommand {
    @TargetAggregateIdentifier
    private String productId;
    private Map<String,Integer> priceBucketWiseSubscriptionCount;

    public UpdateProductSubscriptionCommand(String productId, Map<String,Integer> priceBucketWiseSubscriptionCount) {
        this.productId = productId;
        this.priceBucketWiseSubscriptionCount = priceBucketWiseSubscriptionCount;
    }

    public String getProductId() {
        return productId;
    }

    public Map<String,Integer> getPriceBucketWiseSubscriptionCount() {
        return priceBucketWiseSubscriptionCount;
    }

}
