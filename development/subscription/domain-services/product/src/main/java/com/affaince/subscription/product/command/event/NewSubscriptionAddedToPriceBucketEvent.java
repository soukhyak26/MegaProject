package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 31-12-2016.
 */
public class NewSubscriptionAddedToPriceBucketEvent {
    private String productId;
    private String priceBucketId;
    private long addedSubscriptionCount;
    private long newSubscriptionCount;
    private final long totalSubscriptionCount;
    public NewSubscriptionAddedToPriceBucketEvent(String productId, String priceBucketId, long addedSubscriptionCount,long newSubscriptionCount, long totalSubscriptionCount) {
        this.productId=productId;
        this.priceBucketId=priceBucketId;
        this.addedSubscriptionCount=addedSubscriptionCount;
        this.newSubscriptionCount = newSubscriptionCount;
        this.totalSubscriptionCount=totalSubscriptionCount;
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public long getNewSubscriptionCount() {
        return newSubscriptionCount;
    }
    public long getTotalSubscriptionCount() {
        return totalSubscriptionCount;
    }

    public long getAddedSubscriptionCount() {
        return addedSubscriptionCount;
    }
}
