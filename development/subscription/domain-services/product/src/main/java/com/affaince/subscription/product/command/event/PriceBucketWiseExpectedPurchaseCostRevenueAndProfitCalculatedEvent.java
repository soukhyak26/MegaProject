package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 3/25/2017.
 */
public class PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent {
    private final String productId;
    private final String priceBucketId;
    private final double purchaseCostOfDeliveredUnits;
    private final double revenue;
    private final double profitAmountPerPriceBucket;

    public PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent(String productId, String priceBucketId, double purchaseCostOfDeliveredUnits, double revenue, double profitAmountPerPriceBucket) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.purchaseCostOfDeliveredUnits=purchaseCostOfDeliveredUnits;
        this.revenue=revenue;
        this.profitAmountPerPriceBucket = profitAmountPerPriceBucket;
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

}
