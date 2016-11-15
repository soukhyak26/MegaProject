package com.affaince.subscription.forecast.query.view;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 17-08-2016.
 */
public class ForecastingCalenderView {
    private String productId;
    private LocalDate nextForecastDate;

    public ForecastingCalenderView(String productId, LocalDate nextForecastDate) {
        this.productId = productId;
        this.nextForecastDate = nextForecastDate;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getNextForecastDate() {
        return nextForecastDate;
    }
}
