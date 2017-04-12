package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.product.vo.DeliveredSubscriptionsAgainstTaggedPrice;

/**
 * Created by mandar on 31-12-2016.
 */
public class DeliveredSubscriptionCountAddedToPriceBucket {
    private final String productId;
    private final String priceBucketId;
    private final DeliveredSubscriptionsAgainstTaggedPrice latestDeliveredSubscriptionsAgainstTaggedPrice;
    private final double offerPriceOrPercent;
    private final ProductPricingCategory productPricingCategory;
    private final long deliveredSubscriptionCount;
    private final long totalDeliveredSubscriptionCount;

    public DeliveredSubscriptionCountAddedToPriceBucket(String productId, String priceBucketId, DeliveredSubscriptionsAgainstTaggedPrice latestDeliveredSubscriptionsAgainstTaggedPrice, double offerPriceOrPercent, ProductPricingCategory productPricingCategory, long deliveredSubscriptionCount, long totalDeliveredSubscriptionCount) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.latestDeliveredSubscriptionsAgainstTaggedPrice = latestDeliveredSubscriptionsAgainstTaggedPrice;
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

    public DeliveredSubscriptionsAgainstTaggedPrice getLatestDeliveredSubscriptionsAgainstTaggedPrice() {
        return latestDeliveredSubscriptionsAgainstTaggedPrice;
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
