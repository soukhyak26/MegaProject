package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.ProductPricingCategory;

/**
 * Created by mandar on 19-02-2017.
 */
public class DeliveredSubscriptionCountAddedToPriceBucket {
    private final String productId;
    private final String priceBucketId;
    private final double purchasePricePerUnit;
    private final double MRP;
    private final double offerPriceOrPercent;
    private final ProductPricingCategory productPricingCategory;
    private final long deliveredSubscriptionCount;
    private final long totalDeliveredSubscriptionCount;

    public DeliveredSubscriptionCountAddedToPriceBucket(String productId, String priceBucketId, double purchasePricePerUnit, double MRP, double offerPriceOrPercent, ProductPricingCategory productPricingCategory, long deliveredSubscriptionCount,long totalDeliveredSubscriptionCount) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.offerPriceOrPercent = offerPriceOrPercent;
        this.productPricingCategory = productPricingCategory;
        this.deliveredSubscriptionCount = deliveredSubscriptionCount;
        this.totalDeliveredSubscriptionCount=totalDeliveredSubscriptionCount;
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

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public double getOfferPriceOrPercent() {
        return offerPriceOrPercent;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public long getTotalDeliveredSubscriptionCount() {
        return totalDeliveredSubscriptionCount;
    }
}
