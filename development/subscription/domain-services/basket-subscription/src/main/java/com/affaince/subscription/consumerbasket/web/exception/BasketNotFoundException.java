package com.affaince.subscription.consumerbasket.web.exception;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
public class BasketNotFoundException extends Exception {

    private String message;

    private BasketNotFoundException(String message) {
        this.message = message;
    }

    public static BasketNotFoundException build(String basketId) {
        return new BasketNotFoundException(String.format("Basket does not found with id: %s", basketId));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
