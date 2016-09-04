package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 28-05-2016.
 */
public class PriceBucketForNoneCommitment extends PriceBucket {
    private double offeredPricePerUnit;

    public PriceBucketForNoneCommitment() {
    }

    public PriceBucketForNoneCommitment(String productId, String priceBucketId, PriceTaggedWithProduct taggedPriceVersion, double offeredPrice, EntityStatus entityStatus, LocalDateTime fromDate) {
        this.setProductId(productId);
        this.setPriceBucketId(priceBucketId);
        this.setTaggedPriceVersion(taggedPriceVersion);
        this.offeredPricePerUnit = offeredPrice;
        this.setEntityStatus(entityStatus);
        this.setFromDate(fromDate);
    }

    public PriceBucketForNoneCommitment(double offeredPricePerUnit, LocalDateTime fromDate) {
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.setFromDate(fromDate);
    }

    @Override
    public double getOfferedPricePerUnit() {
        return offeredPricePerUnit;
    }

    @Override
    public void setOfferedPricePerUnit(double offeredPrice) {
        offeredPricePerUnit=offeredPrice;
    }

    @Override
    public double getPercentDiscountPerUnit() {
        throw new RuntimeException("Price Bucket for no commitment offering cannot return offer percent discount");
    }

    @Override
    public void setPercentDiscountPerUnit(double offeredDiscount) {
        throw new RuntimeException("Price Bucket for no commitment offering cannot set offer percent discount");
    }
}
