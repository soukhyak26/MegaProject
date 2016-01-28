package com.affaince.subscription.product.registration.command.domain;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public enum ProductPricingCategory {

    NO_COMMITMENT(0), PRICE_COMMITMENT(1), DISCOUNT_COMMITMENT(2);

    private int index;

    ProductPricingCategory(int index) {
        this.index = index;
    }
}
