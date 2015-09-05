package com.affaince.subscription.consumerbasket.command.domain;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class BasketItem {
    private String itemId;
    private String productId;
    private int quantityPerBasket;
    private Frequency frequency;
    private double itemMRP;
    private double itemDiscountedPrice;

    public BasketItem(String itemId, String productId, int quantityPerBasket, Frequency frequency, double itemMRP, double itemDiscountedPrice) {
        this.itemId = itemId;
        this.productId = productId;
        this.quantityPerBasket = quantityPerBasket;
        this.frequency = frequency;
        this.itemMRP = itemMRP;
        this.itemDiscountedPrice = itemDiscountedPrice;
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

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketItem)) return false;

        BasketItem that = (BasketItem) o;

        if (!itemId.equals(that.itemId)) return false;
        return productId.equals(that.productId);

    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + productId.hashCode();
        return result;
    }
}
