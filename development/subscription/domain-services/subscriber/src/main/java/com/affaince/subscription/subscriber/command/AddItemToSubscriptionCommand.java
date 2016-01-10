package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 22-08-2015.
 */
public class AddItemToSubscriptionCommand {
    @TargetAggregateIdentifier
    private String subscriptionId;
    private String itemId;
    private int quantityPerBasket;
    private int frequency;
    private int frequencyUnit;
    private double discountedOfferedPrice;
    private int noOfCycle;

    public AddItemToSubscriptionCommand(String subscriptionId, String itemId, int quantityPerBasket, int frequency, int frequencyUnit, double discountedOfferedPrice, int noOfCycle) {
        this.subscriptionId = subscriptionId;
        this.itemId = itemId;
        this.quantityPerBasket = quantityPerBasket;
        this.frequency = frequency;
        this.frequencyUnit = frequencyUnit;
        this.discountedOfferedPrice = discountedOfferedPrice;
        this.noOfCycle = noOfCycle;
    }

    public AddItemToSubscriptionCommand() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantityPerBasket() {
        return quantityPerBasket;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getFrequencyUnit() {
        return frequencyUnit;
    }

    public double getDiscountedOfferedPrice() {
        return discountedOfferedPrice;
    }

    public int getNoOfCycle() {
        return noOfCycle;
    }
}
