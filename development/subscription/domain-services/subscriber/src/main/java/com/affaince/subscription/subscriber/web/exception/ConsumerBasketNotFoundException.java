package com.affaince.subscription.subscriber.web.exception;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
public class ConsumerBasketNotFoundException extends Exception {

    private String message;

    private ConsumerBasketNotFoundException(String message) {
        this.message = message;
    }

    public static ConsumerBasketNotFoundException build(String basketId) {
        return new ConsumerBasketNotFoundException(String.format("Consumer Delivery does not found with id: %s", basketId));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
