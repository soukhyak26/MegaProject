package com.affaince.subscription.pricing.exception;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 6/27/2017.
 */
public class NoMatchingForecastForActualSubscriptionException extends RuntimeException {
    private static final String message = "For product %s, There is no deaily forecast value present, " +
            "to be compared with an actual subscription dated: %s";


    private NoMatchingForecastForActualSubscriptionException(String message){
        super(message);
    }
    public static NoMatchingForecastForActualSubscriptionException build(String productId,LocalDate date) {
        return new NoMatchingForecastForActualSubscriptionException(String.format(message, productId, date.toString()));
    }

    @Override
    public String getMessage() {
        return message;
    }

}
