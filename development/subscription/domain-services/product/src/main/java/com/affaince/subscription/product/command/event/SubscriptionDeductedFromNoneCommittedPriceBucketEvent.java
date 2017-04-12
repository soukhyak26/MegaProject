package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 4/10/2017.
 */
public class SubscriptionDeductedFromNoneCommittedPriceBucketEvent {
    private final String productId;
    private final String priceBucketId;
    private final long deductedSubscriptionCount;
    private final long revisedChurnedSubscriptionCount;
    private final long revisedTotalSubscriptionCount;

    public SubscriptionDeductedFromNoneCommittedPriceBucketEvent(String productId, String priceBucketId, long deductedSubscriptionCount, long revisedChurnedSubscriptionCount, long revisedTotalSubscriptionCount) {
        this.productId=productId;
        this.priceBucketId=priceBucketId;
        this.deductedSubscriptionCount=deductedSubscriptionCount;
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

    public long getDeductedSubscriptionCount() {
        return deductedSubscriptionCount;
    }
}
