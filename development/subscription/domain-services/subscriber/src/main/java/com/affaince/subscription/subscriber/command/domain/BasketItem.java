package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.Frequency;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class BasketItem {
    private String itemId;
    private Frequency frequency;
    private double discountedOfferedPrice;
    private int noOfCycle;

    public BasketItem(String itemId, Frequency frequency, double discountedOfferedPrice, int noOfCycle) {
        this.itemId = itemId;
        this.frequency = frequency;
        this.discountedOfferedPrice = discountedOfferedPrice;
        this.noOfCycle = noOfCycle;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public double getDiscountedOfferedPrice() {
        return discountedOfferedPrice;
    }

    public void setDiscountedOfferedPrice(double discountedOfferedPrice) {
        this.discountedOfferedPrice = discountedOfferedPrice;
    }

    public int getNoOfCycle() {
        return noOfCycle;
    }

    public void setNoOfCycle(int noOfCycle) {
        this.noOfCycle = noOfCycle;
    }
}
