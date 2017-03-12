package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDateTime;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class OfferedPriceChangedEvent {
    private String productId;
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
    private LocalDateTime currentPriceDate;

    public OfferedPriceChangedEvent() {
    }

/*
    public OfferedPriceChangedEvent(String productId, String priceBucketId, double offeredPricePerUnit, LocalDateTime currentPriceDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.currentPriceDate = currentPriceDate;
    }
*/

    public OfferedPriceChangedEvent(String productId, String priceBucketId, ProductPricingCategory productPricingCategory, PriceTaggedWithProduct taggedPriceVersion, long numberOfNewSubscriptions, long numberOfChurnedSubscriptions, long numberOfExistingSubscriptions, LocalDateTime fromDate, LocalDateTime toDate, EntityStatus entityStatus, double offeredPriceOrPercentDiscountPerUnit, LocalDateTime currentPriceDate) {
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
        this.currentPriceDate = currentPriceDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public void setPriceBucketId(String priceBucketId) {
        this.priceBucketId = priceBucketId;
    }

/*
    public double getOfferedPricePerUnit() {
        return offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
    }
*/

    public LocalDateTime getCurrentPriceDate() {
        return currentPriceDate;
    }

    public void setCurrentPriceDate(LocalDateTime currentPriceDate) {
        this.currentPriceDate = currentPriceDate;
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
}
