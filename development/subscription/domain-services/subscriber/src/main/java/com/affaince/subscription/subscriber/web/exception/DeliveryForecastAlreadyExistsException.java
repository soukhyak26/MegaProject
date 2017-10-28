package com.affaince.subscription.subscriber.web.exception;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/28/2017.
 */
public class DeliveryForecastAlreadyExistsException extends Exception {
    private String message;

    private DeliveryForecastAlreadyExistsException(String message) {
        this.message = message;
    }

    public static DeliveryForecastAlreadyExistsException build(LocalDate fromDate, LocalDate toDate) {
        return new DeliveryForecastAlreadyExistsException(String.format("Delivery Forecast for given Date Range is already available: %s,%s", fromDate.toString(), toDate.toString()));
    }

    @Override
    public String getMessage() {
        return message;
    }

}
