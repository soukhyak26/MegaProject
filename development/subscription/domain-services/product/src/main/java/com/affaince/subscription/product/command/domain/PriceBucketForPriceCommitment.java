package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 27-05-2016.
 */
public class PriceBucketForPriceCommitment extends PriceBucket {
    private double offeredPricePerUnit;

    public PriceBucketForPriceCommitment() {
    }

    public PriceBucketForPriceCommitment(String priceBucketId, PriceTaggedWithProduct taggedPriceVersion, double offeredPricePerUnit, EntityStatus entityStatus) {
        this.setPriceBucketId(priceBucketId);
        this.setTaggedPriceVersion(taggedPriceVersion);
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.setEntityStatus(entityStatus);
    }

    public PriceBucketForPriceCommitment(double offeredPricePerUnit, LocalDate fromDate) {
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
        throw new RuntimeException("Price Bucket for price commitment offering cannot return percent discount");
    }

    @Override
    public void setPercentDiscountPerUnit(double offeredDiscount) {
        throw new RuntimeException("Price Bucket for price commitment offering cannot set percent discount");
    }
}
