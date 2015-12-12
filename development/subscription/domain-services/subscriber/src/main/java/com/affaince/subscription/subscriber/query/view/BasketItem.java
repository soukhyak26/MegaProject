package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.Frequency;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class BasketItem {
    private String itemId;
    private Frequency frequency;
    private double discountedOfferedPrice;

    public BasketItem(String itemId, Frequency frequency, double discountedOfferedPrice) {
        this.itemId = itemId;
        this.frequency = frequency;
        this.discountedOfferedPrice = discountedOfferedPrice;
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
}
