package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 28-11-2015.
 */
public abstract class PriceBucket extends AbstractAnnotatedEntity {
    private String priceBucketId;
    private PriceTaggedWithProduct taggedPriceVersion;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfExistingSubscriptions;
    private double expectedProfit;
    private LocalDate fromDate;
    private LocalDate toDate;
    private EntityStatus entityStatus;
    private double totalProfit;
    private double slope;

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public void setPriceBucketId(String priceBucketId) {
        this.priceBucketId = priceBucketId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
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

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double recalculateOfferedPriceBasedOnActualDemand() {
        if (slope != 0) {
            return (taggedPriceVersion.getMRP() + slope * getNumberOfNewSubscriptions());
        } else {
            return getOfferedPricePerUnit();
        }

    }

}
