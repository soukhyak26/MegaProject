package com.affaince.subscription.consumerbasket.query.view;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class BasketItem {
    private String itemId;
    private String productId;
    private int quantityPerBasket;
    private String frequency;
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

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
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
