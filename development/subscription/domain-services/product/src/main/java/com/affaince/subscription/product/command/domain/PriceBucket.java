package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDateTime;

/**
 * Created by mandark on 28-11-2015.
 */
public class PriceBucket extends AbstractAnnotatedEntity {
    private String productId;
    private String priceBucketId;
    private PriceTaggedWithProduct taggedPriceVersion;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfExistingSubscriptions;
    private long numberOfDeliveredSubscriptions;
    private double expectedProfit;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private EntityStatus entityStatus;
    private double totalProfit;
    private double slope;

    private double offeredPriceOrPercentDiscountPerUnit;

    //Only to be used for product with none commitment
    public PriceBucket(String productId, String priceBucketId) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.entityStatus = EntityStatus.CREATED;
    }

    public PriceBucket(String productId, String priceBucketId, PriceTaggedWithProduct taggedPriceVersion, double offeredPriceOrPercentDiscountPerUnit, EntityStatus entityStatus, LocalDateTime fromDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.taggedPriceVersion = taggedPriceVersion;
        this.offeredPriceOrPercentDiscountPerUnit = offeredPriceOrPercentDiscountPerUnit;
        this.entityStatus = entityStatus;
        this.fromDate = fromDate;
    }

    public PriceBucket() {
    }

    public double getOfferedPriceOrPercentDiscountPerUnit() {
        return offeredPriceOrPercentDiscountPerUnit;
    }

    public void setOfferedPriceOrPercentDiscountPerUnit(double offeredPriceOrPercentDiscountPerUnit) {
        this.offeredPriceOrPercentDiscountPerUnit = offeredPriceOrPercentDiscountPerUnit;
    }

    public long getNumberOfDeliveredSubscriptions() {
        return numberOfDeliveredSubscriptions;
    }

    public void setNumberOfDeliveredSubscriptions(long numberOfDeliveredSubscriptions) {
        this.numberOfDeliveredSubscriptions = numberOfDeliveredSubscriptions;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public void setPriceBucketId(String priceBucketId) {
        this.priceBucketId = priceBucketId;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double recalculateOfferedPriceBasedOnActualDemand() {
        if (slope != 0) {
            return (taggedPriceVersion.getMRP() + slope * getNumberOfNewSubscriptions());
        } else {
            return getOfferedPriceOrPercentDiscountPerUnit();
        }

    }

    public void addDeliveredSubscriptionsAssociatedWithAPriceBucket(int subscriptionCount) {
        long numberOfDeliveredSubscriptions = this.numberOfDeliveredSubscriptions + subscriptionCount;
        if(this.numberOfDeliveredSubscriptions==this.numberOfExistingSubscriptions){
            this.entityStatus=EntityStatus.EXPIRED;
        }
    }

    public void addSubscriptionToPriceBucket(int subscriptionCount) {
        this.numberOfNewSubscriptions += subscriptionCount;
        //SHALL WE UPDATE TOTALSUBSCRIPTION COUNT ALSO?
        this.numberOfExistingSubscriptions += subscriptionCount;
    }

    public void deductSubscriptionFromPriceBucket(int subscriptionCount) {
        this.numberOfChurnedSubscriptions -= subscriptionCount;
        //SHALL WE UPDATE TOTAL SUBSCRIPTION COUNT HERE ALSO?
        this.numberOfExistingSubscriptions -= subscriptionCount;
    }


    public void expirePriceBucket() {
        this.setEntityStatus(EntityStatus.EXPIRED);
    }
}
