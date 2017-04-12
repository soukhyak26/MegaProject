package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 4/10/2017.
 */
public class NewSubscriptionAddedToPercentCommittedPriceBucketEvent {
    private String productId;
    private String priceBucketId;
    private long addedSubscriptionCount;
    private long newSubscriptionCount;
    private long totalSubscriptionCount;
    private LocalDate subscriptionChangedDate;

    public NewSubscriptionAddedToPercentCommittedPriceBucketEvent(String productId, String priceBucketId, long addedSubscriptionCount, long newSubscriptionCount, long totalSubscriptionCount, LocalDate subscriptionChangedDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.addedSubscriptionCount = addedSubscriptionCount;
        this.newSubscriptionCount = newSubscriptionCount;
        this.totalSubscriptionCount = totalSubscriptionCount;
        this.subscriptionChangedDate = subscriptionChangedDate;
    }

    public NewSubscriptionAddedToPercentCommittedPriceBucketEvent() {
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

    public LocalDate getSubscriptionChangedDate() {
        return subscriptionChangedDate;
    }

}
