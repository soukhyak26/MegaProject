package com.affaince.subscription.product.event;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 4/6/2017.
 */
public class ValueCommittedPriceBucketCreatedEvent {
    private final String productId;
    private final String priceBucketId;
    private final ProductPricingCategory productPricingCategory;
    private final PriceTaggedWithProduct taggedPriceVersion;
    private final double offeredPriceOrPercentDiscountPerUnit;
    private final EntityStatus entityStatus;
    private final LocalDateTime fromDate;

    public ValueCommittedPriceBucketCreatedEvent(String productId, String priceBucketId, ProductPricingCategory productPricingCategory, PriceTaggedWithProduct taggedPriceVersion, double offeredPriceOrPercentDiscountPerUnit, EntityStatus entityStatus, LocalDateTime fromDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.productPricingCategory = productPricingCategory;
        this.taggedPriceVersion = taggedPriceVersion;
        this.offeredPriceOrPercentDiscountPerUnit = offeredPriceOrPercentDiscountPerUnit;
        this.entityStatus = entityStatus;
        this.fromDate = fromDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public double getOfferedPriceOrPercentDiscountPerUnit() {
        return offeredPriceOrPercentDiscountPerUnit;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }
}
