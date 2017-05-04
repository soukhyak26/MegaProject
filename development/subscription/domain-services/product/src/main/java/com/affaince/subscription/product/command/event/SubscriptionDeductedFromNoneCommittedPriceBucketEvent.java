package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 4/10/2017.
 */
public class SubscriptionDeductedFromNoneCommittedPriceBucketEvent {
    private final String productId;
    private final String priceBucketId;
    private final long deductedSubscriptionCount;
    private final long revisedChurnedSubscriptionCount;
    private final long revisedTotalSubscriptionCount;
    private final double offeredPrice;
    private final double purchasePrice;
    private final double MRP;

    private final LocalDate subscriptionChangedDate;


    public SubscriptionDeductedFromNoneCommittedPriceBucketEvent(String productId, String priceBucketId, long deductedSubscriptionCount, long revisedChurnedSubscriptionCount, long revisedTotalSubscriptionCount,double offeredPrice,double purchasePrice,double MRP,LocalDate subscriptionChangedDate) {
        this.productId=productId;
        this.priceBucketId=priceBucketId;
        this.deductedSubscriptionCount=deductedSubscriptionCount;
        this.revisedChurnedSubscriptionCount=revisedChurnedSubscriptionCount;
        this.revisedTotalSubscriptionCount=revisedTotalSubscriptionCount;
        this.offeredPrice=offeredPrice;
        this.purchasePrice=purchasePrice;
        this.MRP=MRP;
        this.subscriptionChangedDate=subscriptionChangedDate;
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

    public LocalDate getSubscriptionChangedDate() {
        return subscriptionChangedDate;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getMRP() {
        return MRP;
    }
}
