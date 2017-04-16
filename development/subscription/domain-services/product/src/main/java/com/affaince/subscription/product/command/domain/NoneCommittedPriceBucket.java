package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.vo.DeliveredSubscriptionsAgainstTaggedPrice;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 4/5/2017.
 */
public class NoneCommittedPriceBucket extends PriceBucket {
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    protected PriceTaggedWithProduct taggedPriceVersion;

    //Since this constructor is already being invoked through an event sourcing handler,it need not emit any event of its own
    public NoneCommittedPriceBucket(String productId, String priceBucketId, ProductPricingCategory productPricingCategory, PriceTaggedWithProduct taggedPriceVersion, double offeredPriceOrPercentDiscountPerUnit, EntityStatus entityStatus, LocalDateTime fromDate) {
        //apply(new NoneCommittedPriceBucketCreatedEvent(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercentDiscountPerUnit, entityStatus, fromDate));
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.productPricingCategory = productPricingCategory;
        this.taggedPriceVersion = taggedPriceVersion;
        this.offeredPriceOrPercentDiscountPerUnit = offeredPriceOrPercentDiscountPerUnit;
        this.entityStatus = entityStatus;
        this.fromDate = fromDate;

    }

    //This may not be needed
    @EventSourcingHandler
    public void on(NoneCommittedPriceBucketCreatedEvent event) {
        this.productId = event.getProductId();
        this.priceBucketId = event.getPriceBucketId();
        this.productPricingCategory = event.getProductPricingCategory();
        this.taggedPriceVersion = event.getTaggedPriceVersion();
        this.offeredPriceOrPercentDiscountPerUnit = event.getOfferedPriceOrPercentDiscountPerUnit();
        this.entityStatus = event.getEntityStatus();
        this.fromDate = event.getFromDate();
    }

    @Override
    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    @Override
    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public PriceTaggedWithProduct getLatestTaggedPriceVersion(){
        //assumed that this collection is sorted in descending order of start date of tagged price version;
        return this.taggedPriceVersion;
    }
    public double getCategoryIndependentOfferedPricePerUnit(){
        return this.offeredPriceOrPercentDiscountPerUnit;
    }

    public void addSubscriptionToPriceBucket(long subscriptionCount, LocalDate subscriptionChangedDate) {
        long revisedNewSubscriptionCount = this.getNumberOfNewSubscriptions() + subscriptionCount;
        long revisedTotalSubscriptionCount = this.getNumberOfExistingSubscriptions() + subscriptionCount;
        apply(new NewSubscriptionAddedToNoneCommittedPriceBucketEvent(productId, priceBucketId, subscriptionCount,
                revisedNewSubscriptionCount, revisedTotalSubscriptionCount, subscriptionChangedDate));
    }

    public void deductSubscriptionFromPriceBucket(int subscriptionCount) {
        long revisedChurnedSubscriptionCount = this.numberOfChurnedSubscriptions + subscriptionCount;
        long revisedTotalSubscriptionCount = this.numberOfExistingSubscriptions - subscriptionCount;
        if (revisedTotalSubscriptionCount == 0) {
            apply(new PriceBucketExpiredEvent(productId, priceBucketId, SysDateTime.now()));
        }
        //SHALL WE UPDATE TOTAL SUBSCRIPTION COUNT HERE ALSO?
        apply(new SubscriptionDeductedFromNoneCommittedPriceBucketEvent(productId, priceBucketId, subscriptionCount, revisedChurnedSubscriptionCount, revisedTotalSubscriptionCount));
    }

    //Expected revenue/profit/cost at the time of each new/churned subscripton affiliated to this price bucket
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

    public void calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(String productId, ProductPricingCategory productPricingCategory, double fixedExpensePeUnit, double variableExpensePerUnit, long deliveredSubscriptionCount) {
        LocalDateTime fromDate = this.getFromDate();
        LocalDateTime toDate = this.getToDate();
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

}
