package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 28-11-2015.
 */
public abstract class PriceBucket extends AbstractAnnotatedEntity {
    protected PriceTaggedWithProduct taggedPriceVersion;
    protected long numberOfNewSubscriptions;
    protected long numberOfChurnedSubscriptions;
    protected long numberOfExistingSubscriptions;
    protected double expectedProfit;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    public void setNumberOfNewSubscriptions(long numberOfNewSubscriptions) {
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public void setNumberOfChurnedSubscriptions(long numberOfChurnedSubscriptions) {
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
    }

    public long getNumberOfExistingSubscriptions() {
        return numberOfExistingSubscriptions;
    }

    public void setNumberOfExistingSubscriptions(long numberOfExistingSubscriptions) {
        this.numberOfExistingSubscriptions = numberOfExistingSubscriptions;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
    private double calculateProfitPerBasket(){
        //return (this.numberOfExistingSubscriptions *this.offeredPricePerUnit)-(this.numberOfExistingSubscriptions *(this.purchasePricePerUnit+this.fixedOperatingExpPerUnit+this.variableOperatingExpPerUnit));
        return 0.0;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public void setTaggedPriceVersion(PriceTaggedWithProduct taggedPriceVersion) {
        this.taggedPriceVersion = taggedPriceVersion;
    }

    public double getExpectedProfit() {
        return expectedProfit;
    }

    public void setExpectedProfit(double expectedProfit) {
        this.expectedProfit = expectedProfit;
    }

    public abstract double getOfferedPricePerUnit();
    public abstract void setOfferedPricePerUnit(double offeredPrice);
    public abstract double getPercentDiscountPerUnit();
    public abstract void setPercentDiscountPerUnit(double offeredDiscount);

}