package com.affaince.subscription.product.command.domain;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 27-05-2016.
 */
public class PriceBucketForPriceCommitment extends PriceBucket {
    private double offeredPricePerUnit;

    public PriceBucketForPriceCommitment() {
    }

    public PriceBucketForPriceCommitment(double offeredPricePerUnit, LocalDate fromDate) {
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.fromDate = fromDate;
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