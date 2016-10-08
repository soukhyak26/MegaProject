package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 08-10-2016.
 */
public class RecommendedPriceOverriddenEvent {
    private final String productId;
    private final PriceTaggedWithProduct taggedPriceVersion;
    private final double overriddenPriceOrPercent;
    private final EntityStatus entityStatus;
    private final LocalDateTime fromDate;


    public RecommendedPriceOverriddenEvent(String productId, PriceTaggedWithProduct taggedPriceVersion, double overriddenPriceOrPercent, EntityStatus entityStatus, LocalDateTime fromDate) {
        this.productId = productId;
        this.taggedPriceVersion = taggedPriceVersion;
        this.overriddenPriceOrPercent = overriddenPriceOrPercent;
        this.entityStatus = entityStatus;
        this.fromDate = fromDate;
    }

    public String getProductId() {
        return productId;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public double getOverriddenPriceOrPercent() {
        return overriddenPriceOrPercent;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }
}
