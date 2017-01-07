package com.affaince.subscription.query.exception;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
public class ProductForecastAlreadyExistsException extends Exception {

    private String message;

    private ProductForecastAlreadyExistsException(String message) {
        this.message = message;
    }

    public static ProductForecastAlreadyExistsException build(String productId, LocalDate fromDate, LocalDate toDate) {
        return new ProductForecastAlreadyExistsException(String.format("Forecast for given Date Range is already available: %s,%s,%s", productId, fromDate.toString(), toDate.toString()));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
