package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDateTime;

public class OfferedPriceChangedEvent {
    private String productId;
    private PriceTaggedWithProduct taggedPriceVersion;
    private double offeredPricePerUnit;
    private EntityStatus entityStatus;
    private LocalDateTime currentPriceDate;


    public OfferedPriceChangedEvent() {
    }

    public OfferedPriceChangedEvent(String productId, PriceTaggedWithProduct taggedPriceVersion, double offeredPricePerUnit, EntityStatus entityStatus, LocalDateTime currentPriceDate) {
        this.productId = productId;
        this.taggedPriceVersion = taggedPriceVersion;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.entityStatus = entityStatus;
        this.currentPriceDate = currentPriceDate;
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
