package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 31-12-2016.
 */
public class SubscriptionDeductedFromPercentCommittedPriceBucketEvent {
    private final String productId;
    private final String priceBucketId;
    private final long deductedSubscriptionCount;
    private final long revisedChurnedSubscriptionCount;
    private final long revisedTotalSubscriptionCount;
    private final double offeredPrice;
    private final LocalDate subscriptionChangeDate;

    public SubscriptionDeductedFromPercentCommittedPriceBucketEvent(String productId, String priceBucketId, long deductedSubscriptionCount, long revisedChurnedSubscriptionCount, long revisedTotalSubscriptionCount,double offeredPrice,LocalDate subscriptionChangeDate) {
        this.productId=productId;
        this.priceBucketId=priceBucketId;
        this.deductedSubscriptionCount=deductedSubscriptionCount;
        this.revisedChurnedSubscriptionCount=revisedChurnedSubscriptionCount;
        this.revisedTotalSubscriptionCount=revisedTotalSubscriptionCount;
        this.offeredPrice=offeredPrice;
        this.subscriptionChangeDate=subscriptionChangeDate;
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

    public LocalDate getSubscriptionChangeDate() {
        return subscriptionChangeDate;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }
}
