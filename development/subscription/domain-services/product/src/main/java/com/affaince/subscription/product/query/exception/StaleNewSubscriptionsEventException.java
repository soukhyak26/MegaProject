package com.affaince.subscription.product.query.exception;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 12-11-2016.
 */
public class StaleNewSubscriptionsEventException extends Exception {
    private static final String message = "For product %s, there are stale events in the event bus; manual intervention needed " ;


    public StaleNewSubscriptionsEventException(String s) {
        super(message);
    }

    public static StaleNewSubscriptionsEventException build(String productId) {
        return new StaleNewSubscriptionsEventException(String.format(message, productId));
    }

}
