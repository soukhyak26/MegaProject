package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.common.type.Period;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 22-08-2015.
 */
public class AddItemToSubscriptionCommand {
    @TargetAggregateIdentifier
    private String subscriptionId;
    private String itemId;
    private int countPerPeriod;
    private Period period;
    private double discountedOfferedPrice;
    private double offeredPriceWithBasketLevelDiscount;
    private int noOfCycles;

    public AddItemToSubscriptionCommand(String subscriptionId, String itemId, int countPerPeriod, Period period, double discountedOfferedPrice, double offeredPriceWithBasketLevelDiscount, int noOfCycles) {
        this.subscriptionId = subscriptionId;
        this.itemId = itemId;
        this.countPerPeriod = countPerPeriod;
        this.period = period;
        this.discountedOfferedPrice = discountedOfferedPrice;
        this.offeredPriceWithBasketLevelDiscount = offeredPriceWithBasketLevelDiscount;
        this.noOfCycles = noOfCycles;
    }

    public AddItemToSubscriptionCommand() {
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
}
