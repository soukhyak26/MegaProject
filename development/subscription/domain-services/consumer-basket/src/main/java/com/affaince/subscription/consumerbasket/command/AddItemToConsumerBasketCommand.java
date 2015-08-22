package com.affaince.subscription.consumerbasket.command;

/**
 * Created by rbsavaliya on 22-08-2015.
 */
public class AddItemToConsumerBasketCommand {
    private String basketId;
    private String itemId;
    private String productId;
    private int quantityPerBasket;
    private int frequency;
    private double itemMRP;
    private double itemDiscountedPrice;

    public AddItemToConsumerBasketCommand(String basketId, String itemId, String productId, int quantityPerBasket, int frequency, double itemMRP, double itemDiscountedPrice) {
        this.basketId = basketId;
        this.itemId = itemId;
        this.productId = productId;
        this.quantityPerBasket = quantityPerBasket;
        this.frequency = frequency;
        this.itemMRP = itemMRP;
        this.itemDiscountedPrice = itemDiscountedPrice;
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
