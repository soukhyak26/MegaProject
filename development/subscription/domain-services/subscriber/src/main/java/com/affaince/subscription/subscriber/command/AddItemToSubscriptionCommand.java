package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.common.type.Period;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 22-08-2015.
 */
public class AddItemToSubscriptionCommand {
    @TargetAggregateIdentifier
    private String subscriptionId;
    private String productId;
    private int countPerPeriod;
    private Period period;
    private double discountedOfferedPrice;
    private double offeredPriceWithBasketLevelDiscount;
    private int noOfCycles;
    private double weightInGrms;

    public AddItemToSubscriptionCommand(String subscriptionId, String productId, int countPerPeriod, Period period, double discountedOfferedPrice, double offeredPriceWithBasketLevelDiscount, int noOfCycles, double weightInGrms) {
        this.subscriptionId = subscriptionId;
        this.productId = productId;
        this.countPerPeriod = countPerPeriod;
        this.period = period;
        this.discountedOfferedPrice = discountedOfferedPrice;
        this.offeredPriceWithBasketLevelDiscount = offeredPriceWithBasketLevelDiscount;
        this.noOfCycles = noOfCycles;
        this.weightInGrms = weightInGrms;
    }

    public AddItemToSubscriptionCommand() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getProductId() {
        return productId;
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
}
