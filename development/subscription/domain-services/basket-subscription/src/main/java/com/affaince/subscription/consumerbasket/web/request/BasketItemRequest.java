package com.affaince.subscription.consumerbasket.web.request;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class BasketItemRequest {
    private String itemId;
    private String productId;
    private int quantityPerBasket;
    private int frequency;
    private String frequencyUnit;
    private double itemMRP;
    private double itemDiscountedPrice;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }

    public double getItemMRP() {
        return itemMRP;
    }

    public void setItemMRP(double itemMRP) {
        this.itemMRP = itemMRP;
    }

    public double getItemDiscountedPrice() {
        return itemDiscountedPrice;
    }

    public void setItemDiscountedPrice(double itemDiscountedPrice) {
        this.itemDiscountedPrice = itemDiscountedPrice;
    }
}
