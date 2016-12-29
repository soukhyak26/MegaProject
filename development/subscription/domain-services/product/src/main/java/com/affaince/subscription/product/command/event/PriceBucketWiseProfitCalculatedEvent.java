package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 29-12-2016.
 */
public class PriceBucketWiseProfitCalculatedEvent {
    private final String productId;
    private final String priceBucketId;
    private final double profitAmountPerPriceBucket;

    public PriceBucketWiseProfitCalculatedEvent(String productId, String priceBucketId, double profitAmountPerPriceBucket) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.profitAmountPerPriceBucket = profitAmountPerPriceBucket;
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public double getProfitAmountPerPriceBucket() {
        return profitAmountPerPriceBucket;
    }
}
