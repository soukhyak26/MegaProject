package com.affaince.subscription.subscriber.event;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.ProductPricingCategory;

/**
 * Created by rbsavaliya on 23-08-2015.
 */
public class ItemAddedToSubscriptionEvent {
    private String subscriptionId;
    private String itemId;
    private int countPerPeriod;
    private Period period;
    private double discountedOfferedPrice;
    private double offeredPriceWithBasketLevelDiscount;
    private int noOfCycles;
    private double weightInGrms;
    private ProductPricingCategory productPricingCategory;

    public ItemAddedToSubscriptionEvent(String subscriptionId, String itemId, int countPerPeriod, Period period, double discountedOfferedPrice, double offeredPriceWithBasketLevelDiscount, int noOfCycles, double weightInGrms, ProductPricingCategory productPricingCategory) {
        this.subscriptionId = subscriptionId;
        this.itemId = itemId;
        this.countPerPeriod = countPerPeriod;
        this.period = period;
        this.discountedOfferedPrice = discountedOfferedPrice;
        this.offeredPriceWithBasketLevelDiscount = offeredPriceWithBasketLevelDiscount;
        this.noOfCycles = noOfCycles;
        this.weightInGrms = weightInGrms;
        this.productPricingCategory = productPricingCategory;
    }

    public ItemAddedToSubscriptionEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getItemId() {
        return itemId;
    }

    public int getCountPerPeriod() {
        return countPerPeriod;
    }

    public Period getPeriod() {
        return period;
    }

    public double getDiscountedOfferedPrice() {
        return discountedOfferedPrice;
    }

    public double getOfferedPriceWithBasketLevelDiscount() {
        return offeredPriceWithBasketLevelDiscount;
    }

    public int getNoOfCycles() {
        return noOfCycles;
    }

    public double getWeightInGrms() {
        return weightInGrms;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }
}
