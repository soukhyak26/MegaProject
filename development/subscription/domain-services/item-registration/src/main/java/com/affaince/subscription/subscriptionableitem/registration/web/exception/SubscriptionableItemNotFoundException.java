package com.affaince.subscription.subscriptionableitem.registration.web.exception;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
public class SubscriptionableItemNotFoundException extends Exception {

    private String message;

    private SubscriptionableItemNotFoundException(String message) {
        this.message = message;
    }

    public static SubscriptionableItemNotFoundException build(String subscriptionableItemId) {
        return new SubscriptionableItemNotFoundException(String.format("Subscriptionable Item does not found with id: %s", subscriptionableItemId));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
