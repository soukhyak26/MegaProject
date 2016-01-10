package com.affaince.subscription.subscriber.web.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class BasketItemRequest {
    @NotNull
    private String itemId;
    @Min(1)
    private int quantityPerBasket;
    @Min(1)
    private int frequency;
    private int frequencyUnit;
    private double discountedOfferedPrice;
    @Min(1)
    private int noOfCycle;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantityPerBasket() {
        return quantityPerBasket;
    }

    public void setQuantityPerBasket(int quantityPerBasket) {
        this.quantityPerBasket = quantityPerBasket;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(int frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
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
