package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.command.exception.PriceBucketLifecycleMismatchException;
import com.affaince.subscription.product.vo.DeliveredSubscriptionsAgainstTaggedPrice;
import com.affaince.subscription.product.vo.SubscriptionChangeType;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mandark on 28-11-2015.
 */
public class ValueCommittedPriceBucket extends PriceBucket {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValueCommittedPriceBucket.class);
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    protected PriceTaggedWithProduct taggedPriceVersion;

    public ValueCommittedPriceBucket() {
    }

    //Since this constructor is already being invoked through an event sourcing handler,it need not emit any event of its own
    public ValueCommittedPriceBucket(String productId, String priceBucketId, ProductPricingCategory productPricingCategory, PriceTaggedWithProduct taggedPriceVersion, double offeredPriceOrPercentDiscountPerUnit, EntityStatus entityStatus, LocalDateTime fromDate) {
        //apply(new ValueCommittedPriceBucketCreatedEvent(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercentDiscountPerUnit, entityStatus, fromDate));
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.productPricingCategory = productPricingCategory;
        this.taggedPriceVersion = taggedPriceVersion;
        this.offeredPriceOrPercentDiscountPerUnit = offeredPriceOrPercentDiscountPerUnit;
        this.entityStatus = entityStatus;
        this.fromDate = fromDate;
        this.toDate= new LocalDateTime(9999,12,31,0,0,0);

    }

    public PriceTaggedWithProduct getLatestTaggedPriceVersion(){
        return this.taggedPriceVersion;
    }

    //This may not be needed.
    @EventSourcingHandler
    public void on(ValueCommittedPriceBucketCreatedEvent event) {
        this.productId = event.getProductId();
        this.priceBucketId = event.getPriceBucketId();
        this.productPricingCategory = event.getProductPricingCategory();
        this.taggedPriceVersion = event.getTaggedPriceVersion();
        this.offeredPriceOrPercentDiscountPerUnit = event.getOfferedPriceOrPercentDiscountPerUnit();
        this.entityStatus = event.getEntityStatus();
        this.fromDate = event.getFromDate();
    }


    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public double getCategoryIndependentOfferedPricePerUnit(){
        return this.offeredPriceOrPercentDiscountPerUnit;
    }

    public void addSubscriptionToPriceBucket(long subscriptionCount, LocalDate subscriptionChangedDate) {
        long revisedNewSubscriptionCount = this.getNumberOfNewSubscriptions() + subscriptionCount;
        long revisedTotalSubscriptionCount = this.getNumberOfExistingSubscriptions() + subscriptionCount;
        double offeredPrice=this.getOfferedPriceOrPercentDiscountPerUnit();
        PriceTaggedWithProduct latestTaggedPriceVersion=this.getLatestTaggedPriceVersion();
        double purchasePrice=latestTaggedPriceVersion.getPurchasePricePerUnit();
        double MRP=latestTaggedPriceVersion.getMRP();

        apply(new NewSubscriptionAddedToValueCommittedPriceBucketEvent(productId, priceBucketId, subscriptionCount,
                revisedNewSubscriptionCount, revisedTotalSubscriptionCount, offeredPrice,purchasePrice,MRP,subscriptionChangedDate));
    }

    @EventSourcingHandler
    public void on(NewSubscriptionAddedToValueCommittedPriceBucketEvent event) {
        this.numberOfNewSubscriptions = event.getNewSubscriptionCount();
        //SHALL WE UPDATE TOTALSUBSCRIPTION COUNT ALSO?
        this.numberOfExistingSubscriptions = event.getTotalSubscriptionCount();
    }


    public void deductSubscriptionFromPriceBucket(int subscriptionCount,LocalDate subscriptionChangeDate) {
        long revisedChurnedSubscriptionCount = this.numberOfChurnedSubscriptions + subscriptionCount;
        long revisedTotalSubscriptionCount = this.numberOfExistingSubscriptions - subscriptionCount;
        if (revisedTotalSubscriptionCount == 0) {
            apply(new PriceBucketExpiredEvent(productId, priceBucketId, SysDateTime.now()));
        }
        double offeredPrice=this.getOfferedPriceOrPercentDiscountPerUnit();
        PriceTaggedWithProduct latestTaggedPriceVersion=this.getLatestTaggedPriceVersion();
        double purchasePrice=latestTaggedPriceVersion.getPurchasePricePerUnit();
        double MRP=latestTaggedPriceVersion.getMRP();

        //SHALL WE UPDATE TOTAL SUBSCRIPTION COUNT HERE ALSO?
        apply(new SubscriptionDeductedFromValueCommittedPriceBucketEvent(productId, priceBucketId, subscriptionCount, revisedChurnedSubscriptionCount, revisedTotalSubscriptionCount,offeredPrice,purchasePrice,MRP,subscriptionChangeDate));
    }

    @EventSourcingHandler
    public void on(SubscriptionDeductedFromValueCommittedPriceBucketEvent event) {
        this.numberOfChurnedSubscriptions = event.getRevisedChurnedSubscriptionCount();
        this.numberOfExistingSubscriptions = event.getRevisedTotalSubscriptionCount();
    }

    //Expected revenue/profit/cost at the time of each new/churned subscription affiliated to this price bucket
    public void calculateExpectedPurchaseExpenseRevenueAndProfitForPriceBucket(String productId, int changedSubscriptionCount,double fixedExpensePeUnit, double variableExpensePerUnit) {
        final double revenue = Math.abs(changedSubscriptionCount) * this.getFixedOfferedPriceOrPercentDiscountPerUnit();
        final double purchaseCost = Math.abs(changedSubscriptionCount) * (this.getTaggedPriceVersion().getPurchasePricePerUnit());
        final double totalFixedExpense = Math.abs(changedSubscriptionCount) * fixedExpensePeUnit;
        final double totalVariableExpense = Math.abs(changedSubscriptionCount) * variableExpensePerUnit;
        final double profit = revenue - (purchaseCost + totalFixedExpense + totalVariableExpense);
        SubscriptionChangeType changeType=SubscriptionChangeType.GAIN;
        if(changedSubscriptionCount<0){
            changeType=SubscriptionChangeType.CHURN;
        }
        apply(new PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent(productId, priceBucketId, purchaseCost, revenue, profit,changeType));
    }

    //registered profit and revenue should be calculated on each delivery confirmation OR by a batch job
    public void calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(String productId, ProductPricingCategory productPricingCategory, double fixedExpensePerUnit, double variableExpensePerUnit, long deliveredSubscriptionCount,LocalDate dispatchDate){
        double purchaseCostOfDeliveredSubscriptions=0;
        //first calculate revenue, profit and purchase cost of delivered subscription in a day
        double revenueOfDeliveredSubscriptions= deliveredSubscriptionCount*this.getFixedOfferedPriceOrPercentDiscountPerUnit();
        try {
            if ((dispatchDate.isEqual(this.getTaggedPriceVersion().getTaggedStartDate()) || dispatchDate.isAfter(this.getTaggedPriceVersion().getTaggedStartDate())) && ( dispatchDate.isBefore(this.getTaggedPriceVersion().getTaggedEndDate())|| dispatchDate.isEqual(this.getTaggedPriceVersion().getTaggedEndDate()))) {
                purchaseCostOfDeliveredSubscriptions = deliveredSubscriptionCount * this.getTaggedPriceVersion().getPurchasePricePerUnit();
            } else {
                throw PriceBucketLifecycleMismatchException.build(productId, priceBucketId, dispatchDate);
            }
        }catch(PriceBucketLifecycleMismatchException ex){
            LOGGER.error(ex.getMessage());
        }
        double fixedExpensesOfDeliveredSubscriptions=deliveredSubscriptionCount*fixedExpensePerUnit;
        double variableExpensesOfDeliveredSubscriptions=deliveredSubscriptionCount*variableExpensePerUnit;
        double profitOfDeliveredSubscriptions=revenueOfDeliveredSubscriptions -( purchaseCostOfDeliveredSubscriptions + fixedExpensesOfDeliveredSubscriptions + variableExpensesOfDeliveredSubscriptions);
        apply(new PurchaseCostRevenueAndProfitOfDeliveredSubscriptionsCalculatedEvent(productId, priceBucketId, productPricingCategory, fixedExpensePerUnit, variableExpensePerUnit, deliveredSubscriptionCount,purchaseCostOfDeliveredSubscriptions,revenueOfDeliveredSubscriptions,profitOfDeliveredSubscriptions,dispatchDate));
/*
        long totalDeliveredSubscriptions = this.getTotalDeliveredSubscriptions() + deliveredSubscriptionCount;
        totalRevenue = totalDeliveredSubscriptions * this.getFixedOfferedPriceOrPercentDiscountPerUnit();
        //need to change the following as total delivered subscriptions may be associated with different tagged price versions in different types of price buckets
        //purchaseCost = totalDeliveredSubscriptions * (this.getTaggedPriceVersion().getPurchasePricePerUnit());
        for(DeliveredSubscriptionsAgainstTaggedPrice deliveredSubscriptionsAgainstTaggedPrice: deliveredSubscriptionsAgainstTaggedPrices){
            totalPurchaseCost += deliveredSubscriptionsAgainstTaggedPrice.getNumberOfDeliveredSubscriptions() * deliveredSubscriptionsAgainstTaggedPrice.getTaggedPriceVersion().getPurchasePricePerUnit();
        }
        //now add the purchase cost of latest delivered subscriptions as the same has not yet been added against respective latest tagged price version.
        totalPurchaseCost += deliveredSubscriptionCount * getLatestDeliveredSubscriptionsAgainstTaggedPriceVersion().getTaggedPriceVersion().getPurchasePricePerUnit();
        totalFixedExpense = totalDeliveredSubscriptions * fixedExpensePerUnit;
        totalVariableExpense = totalDeliveredSubscriptions * variableExpensePerUnit;
        totalProfit = totalRevenue - (totalPurchaseCost + totalFixedExpense + totalVariableExpense);
        apply(new PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent(productId, priceBucketId, productPricingCategory, fixedExpensePerUnit, variableExpensePerUnit, deliveredSubscriptionCount, totalDeliveredSubscriptions, totalPurchaseCost, totalRevenue, totalProfit));
        if (totalDeliveredSubscriptions == this.numberOfExistingSubscriptions) {
            apply(new PriceBucketExpiredEvent(productId, priceBucketId, SysDateTime.now()));
        }
*/
    }
    private PriceTaggedWithProduct getTaggedPriceVersion(){
        return this.taggedPriceVersion;
    }

    @EventSourcingHandler
    public void on(PurchaseCostRevenueAndProfitOfDeliveredSubscriptionsCalculatedEvent event){
        Double registeredPurchaseCostForADate= this.registeredPurchaseCostOfDeliveredUnits.get(event.getDispatchDate());
        if(null== registeredPurchaseCostForADate || registeredPurchaseCostForADate==0.0){
            this.registeredPurchaseCostOfDeliveredUnits.put(event.getDispatchDate(),event.getPurchaseCostOfDeliveredSubscriptions());
        }else{
            this.registeredPurchaseCostOfDeliveredUnits.put(event.getDispatchDate(),registeredPurchaseCostForADate + event.getPurchaseCostOfDeliveredSubscriptions());
        }

        Double revenueForADate= this.getRegisteredRevenueForADate(event.getDispatchDate());
        if( null== revenueForADate || revenueForADate==0.0){
            this.registeredRevenue.put(event.getDispatchDate(),event.getRevenueOfDeliveredSubscriptions());
        }else{
            this.registeredRevenue.put(event.getDispatchDate(),revenueForADate + event.getRevenueOfDeliveredSubscriptions());
        }

        Double profitForADate=this.getRegisteredProfitForADate(event.getDispatchDate());
        if( null == profitForADate || profitForADate==0.0){
            this.registeredProfit.put(event.getDispatchDate(),event.getProfitOfDeliveredSubscriptions());
        }else{
            this.registeredProfit.put(event.getDispatchDate(),profitForADate + event.getProfitOfDeliveredSubscriptions());
        }
    }
    @EventSourcingHandler
    public void on(PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent event) {
        if (event.getSubscriptionChangeType() == SubscriptionChangeType.GAIN){
            this.expectedPurchaseCostOfDeliveredUnits += event.getPurchaseCostOfDeliveredUnits();
            this.expectedRevenue += event.getRevenue();
            this.expectedProfit += event.getProfitAmountPerPriceBucket();
        }else{
            this.expectedPurchaseCostOfDeliveredUnits -= event.getPurchaseCostOfDeliveredUnits();
            this.expectedRevenue -= event.getRevenue();
            this.expectedProfit -= event.getProfitAmountPerPriceBucket();
        }
    }

/*
    @EventSourcingHandler
    public void on(PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent event) {
        DeliveredSubscriptionsAgainstTaggedPrice latestDeliveredSubscriptionAgainstTaggedPrice = getLatestDeliveredSubscriptionsAgainstTaggedPriceVersion();
        latestDeliveredSubscriptionAgainstTaggedPrice.addToDeliveredSubscriptions(event.getDeliveredSubscriptionCount());
        //this.numberOfDeliveredSubscriptions = event.getTotalDeliveredSubscriptionCount();
        this.registeredPurchaseCostOfDeliveredUnits = event.getPurchaseCostOfDeliveredUnits();
        this.registeredRevenue = event.getRevenue();
        this.registeredProfit = event.getProfitAmountPerPriceBucket();
    }
*/
}
