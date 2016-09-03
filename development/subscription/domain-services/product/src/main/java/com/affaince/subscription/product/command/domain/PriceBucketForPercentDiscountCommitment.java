package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 28-05-2016.
 */
public class PriceBucketForPercentDiscountCommitment extends PriceBucket {
    private double percentDiscountPerUnit;

    public PriceBucketForPercentDiscountCommitment() {
    }

    public PriceBucketForPercentDiscountCommitment(String priceBucketId, PriceTaggedWithProduct taggedPriceVersion, double percentDiscount, EntityStatus entityStatus) {
        this.setPriceBucketId(priceBucketId);
        this.setTaggedPriceVersion(taggedPriceVersion);
        this.percentDiscountPerUnit = percentDiscount;
        this.setEntityStatus(entityStatus);
    }

    public PriceBucketForPercentDiscountCommitment(double percentDiscountPerUnit, LocalDate fromDate) {
        this.percentDiscountPerUnit = percentDiscountPerUnit;
        this.setFromDate(fromDate);
    }


    @Override
    public double getOfferedPricePerUnit() {
        throw new RuntimeException("Price Bucket for percent discount offering cannot return offer price");
    }

    @Override
    public void setOfferedPricePerUnit(double offeredPrice) {
        throw new RuntimeException("Price Bucket for percent discount offering cannot set offer price");
    }

    @Override
    public double getPercentDiscountPerUnit() {
        return percentDiscountPerUnit;
    }

    @Override
    public void setPercentDiscountPerUnit(double offeredDiscount) {
        percentDiscountPerUnit=offeredDiscount;
    }
}
