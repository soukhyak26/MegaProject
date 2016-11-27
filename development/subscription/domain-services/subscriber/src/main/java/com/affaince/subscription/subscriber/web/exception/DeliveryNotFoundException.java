package com.affaince.subscription.subscriber.web.exception;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
public class DeliveryNotFoundException extends Exception {

    private String message;

    private DeliveryNotFoundException(String message) {
        this.message = message;
    }

    public static DeliveryNotFoundException build(String basketId) {
        return new DeliveryNotFoundException(String.format("Delivery does not found with id: %s", basketId));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
