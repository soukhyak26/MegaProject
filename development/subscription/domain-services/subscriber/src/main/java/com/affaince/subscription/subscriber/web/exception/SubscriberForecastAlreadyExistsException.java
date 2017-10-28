package com.affaince.subscription.subscriber.web.exception;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/28/2017.
 */
public class SubscriberForecastAlreadyExistsException extends Exception {
    private String message;

    private SubscriberForecastAlreadyExistsException(String message) {
        this.message = message;
    }

    public static SubscriberForecastAlreadyExistsException build(LocalDate fromDate, LocalDate toDate) {
        return new SubscriberForecastAlreadyExistsException(String.format("Subscriber Forecast for given Date Range is already available: %s,%s", fromDate.toString(), toDate.toString()));
    }

    @Override
    public String getMessage() {
        return message;
    }

}
