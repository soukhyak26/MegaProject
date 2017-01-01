package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 31-12-2016.
 */
public class SubscriptionDeductedFromPriceBucketEvent {
    private final String productId;
    private final String priceBucketId;
    private final long revisedChurnedSubscriptionCount;
    private final long revisedTotalSubscriptionCount;
    public SubscriptionDeductedFromPriceBucketEvent(String productId, String priceBucketId, long revisedChurnedSubscriptionCount, long revisedTotalSubscriptionCount) {
        this.productId=productId;
        this.priceBucketId=priceBucketId;
        this.revisedChurnedSubscriptionCount=revisedChurnedSubscriptionCount;
        this.revisedTotalSubscriptionCount=revisedTotalSubscriptionCount;
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public long getRevisedChurnedSubscriptionCount() {
        return revisedChurnedSubscriptionCount;
    }

    public long getRevisedTotalSubscriptionCount() {
        return revisedTotalSubscriptionCount;
    }
}
