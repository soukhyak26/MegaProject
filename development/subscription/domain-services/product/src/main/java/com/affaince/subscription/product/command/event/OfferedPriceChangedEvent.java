package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 10-10-2016.
 */
public class OfferedPriceChangedEvent {
    private String productId;
    //private PriceBucket newPriceBucket;
    private String priceBucketId;
    private ProductPricingCategory productPricingCategory;
    private PriceTaggedWithProduct taggedPriceVersion;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfExistingSubscriptions;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private EntityStatus entityStatus;
    private double offeredPriceOrPercentDiscountPerUnit;
    private ProductDemandTrend productDemandTrend;

    public OfferedPriceChangedEvent() {
    }
/*
    public OfferedPriceChangedEvent(String productId, PriceBucket newPriceBucket,ProductDemandTrend productDemandTrend) {
        this.productId = productId;
        this.newPriceBucket = newPriceBucket;
        this.productDemandTrend=productDemandTrend;
    }
*/

    public OfferedPriceChangedEvent(String productId, String priceBucketId, ProductPricingCategory productPricingCategory, PriceTaggedWithProduct taggedPriceVersion, long numberOfNewSubscriptions, long numberOfChurnedSubscriptions, long numberOfExistingSubscriptions, LocalDateTime fromDate, LocalDateTime toDate, EntityStatus entityStatus, double offeredPriceOrPercentDiscountPerUnit, ProductDemandTrend productDemandTrend) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.productPricingCategory = productPricingCategory;
        this.taggedPriceVersion = taggedPriceVersion;
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
        this.numberOfExistingSubscriptions = numberOfExistingSubscriptions;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.entityStatus = entityStatus;
        this.offeredPriceOrPercentDiscountPerUnit = offeredPriceOrPercentDiscountPerUnit;
        this.productDemandTrend = productDemandTrend;
    }

    public String getProductId() {
        return productId;
    }

/*
    public PriceBucket getNewPriceBucket() {
        return newPriceBucket;
    }
*/

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public long getNumberOfExistingSubscriptions() {
        return numberOfExistingSubscriptions;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public double getOfferedPriceOrPercentDiscountPerUnit() {
        return offeredPriceOrPercentDiscountPerUnit;
    }

    public ProductDemandTrend getProductDemandTrend() {
        return productDemandTrend;
    }
}
