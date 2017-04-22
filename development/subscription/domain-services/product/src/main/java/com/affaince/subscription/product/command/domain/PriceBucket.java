package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.event.DeliveredSubscriptionCountAddedToPriceBucketEvent;
import com.affaince.subscription.product.command.event.PriceBucketExpiredEvent;
import com.affaince.subscription.product.services.Comparator.DeliveredSusbcriptionsAgainstTaggedPriceComparator;
import com.affaince.subscription.product.vo.DeliveredSubscriptionsAgainstTaggedPrice;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.*;

/**
 * Created by mandar on 4/5/2017.
 */
public abstract class PriceBucket extends AbstractAnnotatedEntity {
    protected String productId;
    protected String priceBucketId;
    protected ProductPricingCategory productPricingCategory;
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;
    protected EntityStatus entityStatus;

    protected long numberOfExistingSubscriptions;
    protected double offeredPriceOrPercentDiscountPerUnit;
    protected Set<DeliveredSubscriptionsAgainstTaggedPrice> deliveredSubscriptionsAgainstTaggedPrices;
    protected Map<LocalDate,Double> registeredProfit;
    protected Map<LocalDate,Double> registeredPurchaseCostOfDeliveredUnits;
    protected Map<LocalDate,Double> registeredRevenue;
    protected double slope;

    protected double expectedPurchaseCostOfDeliveredUnits;
    protected double expectedRevenue;
    protected double expectedProfit;



    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public double getSlope() {
        return slope;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getExpectedProfit() {
        return expectedProfit;
    }

    public void setExpectedProfit(double expectedProfit) {
        this.expectedProfit = expectedProfit;
    }

    public double getFixedOfferedPriceOrPercentDiscountPerUnit() {
        return offeredPriceOrPercentDiscountPerUnit;
    }
    public abstract double getCategoryIndependentOfferedPricePerUnit();
    public long getNumberOfExistingSubscriptions() {
        return numberOfExistingSubscriptions;
    }

    public double getRegisteredRevenueForADate(LocalDate date){
        return registeredRevenue.get(date);
    }
    public double getTotalRegisteredRevenue(){
        double totalRegisteredRevenue=0;
        Iterator<Double> registeredRevenueIterator= registeredRevenue.values().iterator();
        while (registeredRevenueIterator.hasNext()){
            totalRegisteredRevenue +=registeredRevenueIterator.next();
        }
        return totalRegisteredRevenue;
    }

    public double getRegisteredProfitForADate(LocalDate date){
        return registeredProfit.get(date);
    }
    public double getTotalRegisteredProfit(){
        double totalRegisteredProfit=0;
        Iterator<Double> registeredProfitIterator= registeredProfit.values().iterator();
        while (registeredProfitIterator.hasNext()){
            totalRegisteredProfit +=registeredProfitIterator.next();
        }
        return totalRegisteredProfit;
    }


    public double getRegisteredPurchaseCostForADate(LocalDate date){
        return registeredPurchaseCostOfDeliveredUnits.get(date);
    }
    public double getTotalRegisteredPurchaseCost(){
        double totalRegisteredPurchaseCost=0;
        Iterator<Double> registeredPurchaseCostOfDeliveredUnitsIterator= registeredPurchaseCostOfDeliveredUnits.values().iterator();
        while (registeredPurchaseCostOfDeliveredUnitsIterator.hasNext()){
            totalRegisteredPurchaseCost +=registeredPurchaseCostOfDeliveredUnitsIterator.next();
        }
        return totalRegisteredPurchaseCost;
    }

    public DeliveredSubscriptionsAgainstTaggedPrice getLatestDeliveredSubscriptionsAgainstTaggedPriceVersion(){
        //Assumption is, this set is sorted in descending order of start date of tagged price version
        return deliveredSubscriptionsAgainstTaggedPrices.iterator().next();
    }
    public void addNewTaggedPriceVersion(){}
    public double recalculateOfferedPriceBasedOnActualDemand() {
        if (slope != 0) {
            return (getLatestTaggedPriceVersion().getMRP() + slope * getNumberOfNewSubscriptions());
        } else {
            return getCategoryIndependentOfferedPricePerUnit();
        }
    }

    //local method for updating state - used from withing event sourcing handler
    private void addToDeliveredSubscriptions(long deliveredSubscriptionCount){
        if(null != deliveredSubscriptionsAgainstTaggedPrices){
            deliveredSubscriptionsAgainstTaggedPrices= new TreeSet<>(new DeliveredSusbcriptionsAgainstTaggedPriceComparator());
        }
        //we are assuming that this collection is sorted in descending order of start date of tagged price version
        DeliveredSubscriptionsAgainstTaggedPrice latestDeliveredSubscriptionsAgainstTaggedPrice= deliveredSubscriptionsAgainstTaggedPrices.iterator().next();
        latestDeliveredSubscriptionsAgainstTaggedPrice.addToDeliveredSubscriptions(deliveredSubscriptionCount);
    }
    public long getTotalDeliveredSubscriptions(){
        long totalDeliveredSubscriptionCount=0;
        for (DeliveredSubscriptionsAgainstTaggedPrice deliveredSubscriptionCountPerTaggedPrice:deliveredSubscriptionsAgainstTaggedPrices) {
            totalDeliveredSubscriptionCount += deliveredSubscriptionCountPerTaggedPrice.getNumberOfDeliveredSubscriptions();
        }
        return totalDeliveredSubscriptionCount;
    }

    public void addDeliveredSubscriptionsAssociatedWithAPriceBucket(String productId,long deliveredSubscriptionCount ){
        long totalDeliveredSubscriptionCount=this.getTotalDeliveredSubscriptions() + deliveredSubscriptionCount;
        //this.calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(productId,fixedExpensePerUnit,variableExpensePerUnit);
        DeliveredSubscriptionsAgainstTaggedPrice latestDeliveredSubscriptionsAgainstTaggedPrice= deliveredSubscriptionsAgainstTaggedPrices.iterator().next();
        apply( new DeliveredSubscriptionCountAddedToPriceBucketEvent(productId,this.priceBucketId,latestDeliveredSubscriptionsAgainstTaggedPrice,this.getFixedOfferedPriceOrPercentDiscountPerUnit(),this.getProductPricingCategory(),deliveredSubscriptionCount, totalDeliveredSubscriptionCount) );
        if(totalDeliveredSubscriptionCount==this.numberOfExistingSubscriptions){
            apply(new PriceBucketExpiredEvent(productId,priceBucketId, SysDateTime.now()));
        }
    }

    public void expirePriceBucket() {
        this.setEntityStatus(EntityStatus.EXPIRED);
    }
    @EventSourcingHandler
    public void on(DeliveredSubscriptionCountAddedToPriceBucketEvent event){
        this.addToDeliveredSubscriptions(event.getDeliveredSubscriptionCount());
    }



    public abstract PriceTaggedWithProduct getLatestTaggedPriceVersion();
    public abstract long getNumberOfNewSubscriptions();
    public abstract long getNumberOfChurnedSubscriptions();
    public abstract void addSubscriptionToPriceBucket(long subscriptionCount, LocalDate subscriptionChangedDate);
    public abstract void deductSubscriptionFromPriceBucket(int subscriptionCount);
    public abstract void calculateExpectedPurchaseExpenseRevenueAndProfitForPriceBucket(String productId, double fixedExpensePeUnit, double variableExpensePerUnit);
    public abstract void calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(String productId, ProductPricingCategory productPricingCategory, double fixedExpensePeUnit, double variableExpensePerUnit, long deliveredSubscriptionCount,LocalDate dispatchDate);
}
