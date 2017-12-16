package com.affaince.subscription.product.event;

import com.affaince.subscription.product.vo.SubscriptionChangeType;

/**
 * Created by mandar on 3/25/2017.
 */
public class PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent {
    private String productId;
    private String priceBucketId;
    private double purchaseCostOfDeliveredUnits;
    private double revenue;
    private double profitAmountPerPriceBucket;
    private SubscriptionChangeType subscriptionChangeType;

    public PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent(String productId, String priceBucketId, double purchaseCostOfDeliveredUnits, double revenue, double profitAmountPerPriceBucket,SubscriptionChangeType subscriptionChangeType) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.purchaseCostOfDeliveredUnits=purchaseCostOfDeliveredUnits;
        this.revenue=revenue;
        this.profitAmountPerPriceBucket = profitAmountPerPriceBucket;
        this.subscriptionChangeType = subscriptionChangeType;
    }

    public PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public double getPurchaseCostOfDeliveredUnits() {
        return purchaseCostOfDeliveredUnits;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getProfitAmountPerPriceBucket() {
        return profitAmountPerPriceBucket;
    }

    public SubscriptionChangeType getSubscriptionChangeType() {
        return subscriptionChangeType;
    }
}
