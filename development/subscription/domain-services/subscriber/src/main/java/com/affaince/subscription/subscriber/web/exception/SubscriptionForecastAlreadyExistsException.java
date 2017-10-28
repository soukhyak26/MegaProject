package com.affaince.subscription.subscriber.web.exception;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/28/2017.
 */
public class SubscriptionForecastAlreadyExistsException extends Exception {
    private String message;

    private SubscriptionForecastAlreadyExistsException(String message) {
        this.message = message;
    }

    public static SubscriptionForecastAlreadyExistsException build(LocalDate fromDate, LocalDate toDate) {
        return new SubscriptionForecastAlreadyExistsException(String.format("Subscription Forecast for given Date Range is already available: %s,%s", fromDate.toString(), toDate.toString()));
    }

    @Override
    public String getMessage() {
        return message;
    }

}
