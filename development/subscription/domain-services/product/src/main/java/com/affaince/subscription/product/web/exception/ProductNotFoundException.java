package com.affaince.subscription.product.web.exception;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
public class ProductNotFoundException extends Exception {

    private String message;

    private ProductNotFoundException(String message) {
        this.message = message;
    }

    public static ProductNotFoundException build(String subscriptionableItemId) {
        return new ProductNotFoundException(String.format("Subscriptionable Item does not found with id: %s", subscriptionableItemId));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
