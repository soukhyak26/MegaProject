package com.affaince.subscription.product.event;

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
    private double offeredPrice;
    private double purchasePrice;
    private double MRP;
    private LocalDate subscriptionChangedDate;

    public NewSubscriptionAddedToPercentCommittedPriceBucketEvent(String productId, String priceBucketId, long addedSubscriptionCount, long newSubscriptionCount, long totalSubscriptionCount, double offeredPrice,double purchasePrice,double MRP,LocalDate subscriptionChangedDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.addedSubscriptionCount = addedSubscriptionCount;
        this.newSubscriptionCount = newSubscriptionCount;
        this.totalSubscriptionCount = totalSubscriptionCount;
        this.offeredPrice=offeredPrice;
        this.purchasePrice=purchasePrice;
        this.MRP=MRP;
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