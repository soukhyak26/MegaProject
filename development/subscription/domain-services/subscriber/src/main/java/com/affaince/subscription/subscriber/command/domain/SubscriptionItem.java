package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.ProductPricingCategory;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class SubscriptionItem {
    private String productId;
    private double weightInGrms;
    private int countPerPeriod;
    private Period period;
    private double discountedOfferedPrice;
    private double offeredPriceWithBasketLevelDiscount;
    private int noOfCycles;
    private ProductPricingCategory productPricingCategory;

    public SubscriptionItem(String productId, double weightInGrms, int countPerPeriod, Period period, double discountedOfferedPrice, double offeredPriceWithBasketLevelDiscount, int noOfCycles, ProductPricingCategory productPricingCategory) {
        this.productId = productId;
        this.weightInGrms = weightInGrms;
        this.countPerPeriod = countPerPeriod;
        this.period = period;
        this.discountedOfferedPrice = discountedOfferedPrice;
        this.offeredPriceWithBasketLevelDiscount = offeredPriceWithBasketLevelDiscount;
        this.noOfCycles = noOfCycles;
        this.productPricingCategory = productPricingCategory;
    }

    public String getProductId() {
        return productId;
    }

    public double getWeightInGrms() {
        return weightInGrms;
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

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }
}
