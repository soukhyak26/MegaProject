package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDateTime;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class OfferedPriceChangedEvent {
    private String productId;
    private String priceBucketId;
    private PriceTaggedWithProduct taggedPriceVersion;
    private double offeredPricePerUnit;
    private EntityStatus entityStatus;
    private LocalDateTime currentPriceDate;


    public OfferedPriceChangedEvent() {
    }

    public OfferedPriceChangedEvent(String productId, String priceBucketId, PriceTaggedWithProduct taggedPriceVersion, double offeredPricePerUnit, EntityStatus entityStatus, LocalDateTime currentPriceDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.taggedPriceVersion = taggedPriceVersion;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.entityStatus = entityStatus;
        this.currentPriceDate = currentPriceDate;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getOfferedPricePerUnit() {
        return offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
    }

    public LocalDateTime getCurrentPriceDate() {
        return this.currentPriceDate;
    }

    public void setCurrentPriceDate(LocalDateTime currentPrizeDate) {
        this.currentPriceDate = currentPrizeDate;
    }
}
