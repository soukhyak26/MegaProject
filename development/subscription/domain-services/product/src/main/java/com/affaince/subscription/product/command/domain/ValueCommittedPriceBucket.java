package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.DeliveredSubscriptionsAgainstTaggedPrice;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by mandark on 28-11-2015.
 */
public class ValueCommittedPriceBucket extends PriceBucket {
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    protected PriceTaggedWithProduct taggedPriceVersion;

    public ValueCommittedPriceBucket() {
    }


    public ValueCommittedPriceBucket(String productId, String priceBucketId, ProductPricingCategory productPricingCategory, PriceTaggedWithProduct taggedPriceVersion, double offeredPriceOrPercentDiscountPerUnit, EntityStatus entityStatus, LocalDateTime fromDate) {
        apply(new ValueCommittedPriceBucketCreatedEvent(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercentDiscountPerUnit, entityStatus, fromDate));
    }

    public PriceTaggedWithProduct getLatestTaggedPriceVersion(){
        return this.taggedPriceVersion;
    }
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


    public void addSubscriptionToPriceBucket(long subscriptionCount, LocalDate subscriptionChangedDate) {
        long revisedNewSubscriptionCount = this.getNumberOfNewSubscriptions() + subscriptionCount;
        long revisedTotalSubscriptionCount = this.getNumberOfExistingSubscriptions() + subscriptionCount;
        apply(new NewSubscriptionAddedToValueCommittedPriceBucketEvent(productId, priceBucketId, subscriptionCount,
                revisedNewSubscriptionCount, revisedTotalSubscriptionCount, subscriptionChangedDate));
    }

    @EventSourcingHandler
    public void on(NewSubscriptionAddedToValueCommittedPriceBucketEvent event) {
        this.numberOfNewSubscriptions = event.getNewSubscriptionCount();
        //SHALL WE UPDATE TOTALSUBSCRIPTION COUNT ALSO?
        this.numberOfExistingSubscriptions = event.getTotalSubscriptionCount();
    }


    public void deductSubscriptionFromPriceBucket(int subscriptionCount) {
        long revisedChurnedSubscriptionCount = this.numberOfChurnedSubscriptions + subscriptionCount;
        long revisedTotalSubscriptionCount = this.numberOfExistingSubscriptions - subscriptionCount;
        if (revisedTotalSubscriptionCount == 0) {
            apply(new PriceBucketExpiredEvent(productId, priceBucketId, SysDateTime.now()));
        }
        //SHALL WE UPDATE TOTAL SUBSCRIPTION COUNT HERE ALSO?
        apply(new SubscriptionDeductedFromValueCommittedPriceBucketEvent(productId, priceBucketId, subscriptionCount, revisedChurnedSubscriptionCount, revisedTotalSubscriptionCount));
    }

    public void on(SubscriptionDeductedFromValueCommittedPriceBucketEvent event) {
        this.numberOfChurnedSubscriptions = event.getRevisedChurnedSubscriptionCount();
        this.numberOfExistingSubscriptions = event.getRevisedTotalSubscriptionCount();
    }

    //Expected revenue/profit/cost at the time of each new/churned subscription affiliated to this price bucket
    public void calculateExpectedPurchaseExpenseRevenueAndProfitForPriceBucket(String productId, double fixedExpensePeUnit, double variableExpensePerUnit) {
        LocalDateTime fromDate = this.getFromDate();
        LocalDateTime toDate = this.getToDate();
        double purchaseCost = 0;
        double revenue = 0;
        double totalFixedExpense = 0;
        double totalVariableExpense = 0;
        double profit = 0;

        revenue = this.getNumberOfExistingSubscriptions() * this.getFixedOfferedPriceOrPercentDiscountPerUnit();
        purchaseCost = this.getNumberOfExistingSubscriptions() * (this.getTaggedPriceVersion().getPurchasePricePerUnit());
        totalFixedExpense = this.getNumberOfExistingSubscriptions() * fixedExpensePeUnit;
        totalVariableExpense = this.getNumberOfExistingSubscriptions() * variableExpensePerUnit;
        profit = revenue - (purchaseCost + totalFixedExpense + totalVariableExpense);
        apply(new PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent(productId, priceBucketId, purchaseCost, revenue, profit));
    }

    //registered profit and revenue should be calculated on each delivery confirmation OR by a batch job
    public void calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(String productId, ProductPricingCategory productPricingCategory, double fixedExpensePeUnit, double variableExpensePerUnit, long deliveredSubscriptionCount) {
        double purchaseCost = 0;
        double revenue = 0;
        double totalFixedExpense = 0;
        double totalVariableExpense = 0;
        double profit = 0;
        long totalDeliveredSubscriptions = this.getTotalDeliveredSubscriptions() + deliveredSubscriptionCount;
        revenue = totalDeliveredSubscriptions * this.getFixedOfferedPriceOrPercentDiscountPerUnit();
        //need to change the following as total delivered subscriptions may be associated with different tagged price versions in different types of price buckets
        //purchaseCost = totalDeliveredSubscriptions * (this.getTaggedPriceVersion().getPurchasePricePerUnit());
        for(DeliveredSubscriptionsAgainstTaggedPrice deliveredSubscriptionsAgainstTaggedPrice: deliveredSubscriptionsAgainstTaggedPrices){
            purchaseCost += deliveredSubscriptionsAgainstTaggedPrice.getNumberOfDeliveredSubscriptions() * deliveredSubscriptionsAgainstTaggedPrice.getTaggedPriceVersion().getPurchasePricePerUnit();
        }
        //now add the purchase cost of latest delivered subscriptions as the same has not yet been added against respective latest tagged price version.
        purchaseCost += deliveredSubscriptionCount * getLatestDeliveredSubscriptionsAgainstTaggedPriceVersion().getTaggedPriceVersion().getPurchasePricePerUnit();
        totalFixedExpense = totalDeliveredSubscriptions * fixedExpensePeUnit;
        totalVariableExpense = totalDeliveredSubscriptions * variableExpensePerUnit;
        profit = revenue - (purchaseCost + totalFixedExpense + totalVariableExpense);
        apply(new PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent(productId, priceBucketId, productPricingCategory, fixedExpensePeUnit, variableExpensePerUnit, deliveredSubscriptionCount, totalDeliveredSubscriptions, purchaseCost, revenue, profit));
        if (totalDeliveredSubscriptions == this.numberOfExistingSubscriptions) {
            apply(new PriceBucketExpiredEvent(productId, priceBucketId, SysDateTime.now()));
        }
    }
    private PriceTaggedWithProduct getTaggedPriceVersion(){
        return this.taggedPriceVersion;
    }

    @EventSourcingHandler
    public void on(PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent event) {
        this.expectedPurchaseCostOfDeliveredUnits = event.getPurchaseCostOfDeliveredUnits();
        this.expectedRevenue = event.getRevenue();
        this.expectedProfit = event.getProfitAmountPerPriceBucket();

    }

    @EventSourcingHandler
    public void on(PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent event) {
        DeliveredSubscriptionsAgainstTaggedPrice latestDeliveredSubscriptionAgainstTaggedPrice = getLatestDeliveredSubscriptionsAgainstTaggedPriceVersion();
        latestDeliveredSubscriptionAgainstTaggedPrice.addToDeliveredSubscriptions(event.getDeliveredSubscriptionCount());
        //this.numberOfDeliveredSubscriptions = event.getTotalDeliveredSubscriptionCount();
        this.registeredPurchaseCostOfDeliveredUnits = event.getPurchaseCostOfDeliveredUnits();
        this.registeredRevenue = event.getRevenue();
        this.registeredProfit = event.getProfitAmountPerPriceBucket();
    }
}
