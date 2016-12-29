package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by mandar on 19-06-2016.
 */
public class ProductSubscriptionUpdatedEvent {
    private final String productId;
    private final Map<String,Integer> priceBucketWiseSubscriptionCount;

    public ProductSubscriptionUpdatedEvent(String productId, Map<String,Integer> priceBucketWiseSubscriptionCount) {
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
