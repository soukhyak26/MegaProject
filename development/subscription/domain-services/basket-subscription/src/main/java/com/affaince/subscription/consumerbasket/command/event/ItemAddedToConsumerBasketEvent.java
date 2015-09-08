package com.affaince.subscription.consumerbasket.command.event;

/**
 * Created by rbsavaliya on 23-08-2015.
 */
public class ItemAddedToConsumerBasketEvent {
    private String basketId;
    private String itemId;
    private int quantityPerBasket;
    private int frequency;
    private int frequencyUnit;
    private double discountedOfferedPrice;

    public ItemAddedToConsumerBasketEvent(String basketId, String itemId, int quantityPerBasket, int frequency, int frequencyUnit, double discountedOfferedPrice) {
        this.basketId = basketId;
        this.itemId = itemId;
        this.quantityPerBasket = quantityPerBasket;
        this.frequency = frequency;
        this.frequencyUnit = frequencyUnit;
        this.discountedOfferedPrice = discountedOfferedPrice;
    }

    public ItemAddedToConsumerBasketEvent() {
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

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
}
