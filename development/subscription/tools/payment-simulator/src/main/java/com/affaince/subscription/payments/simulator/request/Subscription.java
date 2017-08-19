package com.affaince.subscription.payments.simulator.request;

import java.util.Arrays;

/**
 * Created by rsavaliya on 8/3/17.
 */
public class Subscription {

    private BasketItem[] basketItems;

    public BasketItem[] getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(BasketItem[] basketItems) {
        this.basketItems = basketItems;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "basketItems=" + Arrays.toString(basketItems) +
                '}';
    }
}
