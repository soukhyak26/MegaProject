package com.affaince.subscription.expensedistribution.vo;

import java.util.List;

/**
 * Created by rsavaliya on 13/2/16.
 */
public class Delivery {
    private List <Product> products;
    private double basketPrice;
    private double basketWeight;
    private double basketDeliveryCharges;

    public Delivery(List<Product> products, double basketPrice, double basketWeight, double basketDeliveryCharges) {
        this.products = products;
        this.basketPrice = basketPrice;
        this.basketWeight = basketWeight;
        this.basketDeliveryCharges = basketDeliveryCharges;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getBasketPrice() {
        return basketPrice;
    }

    public double getBasketWeight() {
        return basketWeight;
    }

    public double getBasketDeliveryCharges() {
        return basketDeliveryCharges;
    }
}
