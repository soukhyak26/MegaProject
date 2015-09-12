package com.affaince.subscription.subscriber.web.exception;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
public class SubscriberNotFoundException extends Exception {

    private String message;

    private SubscriberNotFoundException(String message) {
        this.message = message;
    }

    public static SubscriberNotFoundException build(String subscriberId) {
        return new SubscriberNotFoundException(String.format("Subscriber does not found with id: %s", subscriberId));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
