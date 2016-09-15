package com.affaince.subscription.product.web.exception;

import org.joda.time.LocalDateTime;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
public class ProductForecastAlreadyExistsException extends Exception {

    private String message;

    private ProductForecastAlreadyExistsException(String message) {
        this.message = message;
    }

    public static ProductForecastAlreadyExistsException build(String productId, LocalDateTime fromDate, LocalDateTime toDate) {
        return new ProductForecastAlreadyExistsException(String.format("Forecast for given Date Range is already available: %s,%s,%s", productId, fromDate.toString(), toDate.toString()));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
