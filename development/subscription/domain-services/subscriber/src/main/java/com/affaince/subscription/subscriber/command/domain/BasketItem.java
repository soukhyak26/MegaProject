package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.Frequency;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class BasketItem {
    private String productId;
    private Frequency frequency;
    private double discountedOfferedPrice;
    private double offeredPriceWithBasketLevelDiscount;
    private int noOfCycle;

    public BasketItem(String productId, Frequency frequency, double discountedOfferedPrice, int noOfCycle) {
        this.productId = productId;
        this.frequency = frequency;
        this.discountedOfferedPrice = discountedOfferedPrice;
        this.noOfCycle = noOfCycle;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public double getOfferedPriceWithBasketLevelDiscount() {
        return offeredPriceWithBasketLevelDiscount;
    }

    public void setOfferedPriceWithBasketLevelDiscount(double offeredPriceWithBasketLevelDiscount) {
        this.offeredPriceWithBasketLevelDiscount = offeredPriceWithBasketLevelDiscount;
    }

    public int getNoOfCycle() {
        return noOfCycle;
    }

    public void setNoOfCycle(int noOfCycle) {
        this.noOfCycle = noOfCycle;
    }
}
