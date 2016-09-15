package com.affaince.subscription.product.web.exception;

import org.joda.time.LocalDateTime;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
public class ProductForecastModificationException extends Exception {

    private String message;

    private ProductForecastModificationException(String message) {
        this.message = message;
    }

    public static ProductForecastModificationException build(String productId, LocalDateTime fromDate, LocalDateTime toDate) {
        return new ProductForecastModificationException(String.format("No Forecast available for modification for given Date Range : %s,%s,%s", productId, fromDate.toString(), toDate.toString()));
    }

    @Override
    public String getMessage() {
        return message;
    }
}
