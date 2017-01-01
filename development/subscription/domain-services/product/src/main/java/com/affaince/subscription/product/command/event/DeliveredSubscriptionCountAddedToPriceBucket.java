package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 31-12-2016.
 */
public class DeliveredSubscriptionCountAddedToPriceBucket {
    private final String productId;
    private final String priceBucketId;
    private final long deliveredSubscriptionCount;


    public DeliveredSubscriptionCountAddedToPriceBucket(String productId, String priceBucketId,long deliveredSubscriptionCount) {
        this.productId=productId;
        this.priceBucketId=priceBucketId;
        this.deliveredSubscriptionCount = deliveredSubscriptionCount;

    }

    public String getProductId() {
        return productId;
    }

    public long getDeliveredSubscriptionCount() {
        return deliveredSubscriptionCount;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

}
