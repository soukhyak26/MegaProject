package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by mandark on 28-01-2016.
 */

@Document
public class PriceBucketView  implements Comparable<PriceBucketView>{
    @Id
    private final ProductwisePriceBucketId productwisePriceBucketId;
    private PriceTaggedWithProduct taggedPriceVersion;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private double offeredPriceOrPercentDiscountPerUnit;
    //private double percentDiscountPerUnit;
    private long numberOfNewSubscriptionsAssociatedWithAPrice;
    private long numberOfChurnedSubscriptionsAssociatedWithAPrice;
    private long numberOfExistingSubscriptionsAssociatedWithAPrice;
    private EntityStatus entityStatus;
    private double registeredPurchaseCost;
    private double registeredRevenue;
    private double registeredProfit;

    private double slope;

    public PriceBucketView(ProductwisePriceBucketId productwisePriceBucketId) {
        this.productwisePriceBucketId = productwisePriceBucketId;
    }

    public static PriceBucketView getLatestPriceBucket(List<PriceBucketView> activePriceBuckets) {
        PriceBucketView latestPriceBucketView = null;
        LocalDateTime max = activePriceBuckets.get(0).getFromDate();
        for (PriceBucketView priceBucketView : activePriceBuckets) {
            if (priceBucketView.getFromDate().compareTo(max) > 0) {
                latestPriceBucketView = priceBucketView;
            }
        }
        return latestPriceBucketView;
    }

    public String getProductId() {
        return productwisePriceBucketId.getProductId();
    }

    public ProductwisePriceBucketId getProductwisePriceBucketId() {
        return productwisePriceBucketId;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public double getOfferedPriceOrPercentDiscountPerUnit() {
        return this.offeredPriceOrPercentDiscountPerUnit;
    }

    public void setOfferedPriceOrPercentDiscountPerUnit(double offeredPriceOrPercentDiscountPerUnit) {
        this.offeredPriceOrPercentDiscountPerUnit = offeredPriceOrPercentDiscountPerUnit;
    }

    public long getNumberOfNewSubscriptionsAssociatedWithAPrice() {
        return this.numberOfNewSubscriptionsAssociatedWithAPrice;
    }

    public void setNumberOfNewSubscriptionsAssociatedWithAPrice(long numberOfNewSubscriptionsAssociatedWithAPrice) {
        this.numberOfNewSubscriptionsAssociatedWithAPrice = numberOfNewSubscriptionsAssociatedWithAPrice;
    }

    public long getNumberOfChurnedSubscriptionsAssociatedWithAPrice() {
        return this.numberOfChurnedSubscriptionsAssociatedWithAPrice;
    }

    public void setNumberOfChurnedSubscriptionsAssociatedWithAPrice(long numberOfChurnedSubscriptionsAssociatedWithAPrice) {
        this.numberOfChurnedSubscriptionsAssociatedWithAPrice = numberOfChurnedSubscriptionsAssociatedWithAPrice;
    }

    public long getNumberOfExistingSubscriptionsAssociatedWithAPrice() {
        return this.numberOfExistingSubscriptionsAssociatedWithAPrice;
    }

    public void setNumberOfExistingSubscriptionsAssociatedWithAPrice(long numberOfExistingSubscriptionsAssociatedWithAPrice) {
        this.numberOfExistingSubscriptionsAssociatedWithAPrice = numberOfExistingSubscriptionsAssociatedWithAPrice;
    }

    public EntityStatus getEntityStatus() {
        return this.entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public void setTaggedPriceVersion(PriceTaggedWithProduct taggedPriceVersion) {
        this.taggedPriceVersion = taggedPriceVersion;
    }

    public double getRegisteredProfit() {
        return registeredProfit;
    }

    public void setRegisteredProfit(double registeredProfit) {
        this.registeredProfit = registeredProfit;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public double getRegisteredPurchaseCost() {
        return registeredPurchaseCost;
    }

    public void setRegisteredPurchaseCost(double registeredPurchaseCost) {
        this.registeredPurchaseCost = registeredPurchaseCost;
    }

    public double getRegisteredRevenue() {
        return registeredRevenue;
    }

    public void setRegisteredRevenue(double registeredRevenue) {
        this.registeredRevenue = registeredRevenue;
    }

    @Override
    public int compareTo(PriceBucketView o) {
        return this.getFromDate().compareTo(o.getFromDate());
    }

    public double recalculateOfferedPriceBasedOnActualDemand(){
        if(slope!= 0){
            return ( taggedPriceVersion.getMRP()+ slope* numberOfExistingSubscriptionsAssociatedWithAPrice);
        }else{
            return offeredPriceOrPercentDiscountPerUnit;
        }
    }

    public void addToNewSubscriptions(int subscriptionCount){
        this.numberOfNewSubscriptionsAssociatedWithAPrice=this.numberOfNewSubscriptionsAssociatedWithAPrice+subscriptionCount;
        this.numberOfExistingSubscriptionsAssociatedWithAPrice +=subscriptionCount;
    }

    public void addToChurnedSubscriptions( int subscriptionCount){
        this.numberOfChurnedSubscriptionsAssociatedWithAPrice=this.numberOfChurnedSubscriptionsAssociatedWithAPrice + subscriptionCount;
        this.numberOfExistingSubscriptionsAssociatedWithAPrice -=subscriptionCount;
    }
}
