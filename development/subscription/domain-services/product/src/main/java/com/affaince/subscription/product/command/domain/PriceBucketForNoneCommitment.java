package com.affaince.subscription.product.command.domain;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 28-05-2016.
 */
public class PriceBucketForNoneCommitment extends PriceBucket {
    private double offeredPricePerUnit;

    public PriceBucketForNoneCommitment() {
    }

    public PriceBucketForNoneCommitment(double offeredPricePerUnit,LocalDate fromDate) {
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.fromDate=fromDate;
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
