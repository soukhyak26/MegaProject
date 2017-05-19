package com.affaince.subscription.product.web.request;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-09-2016.
 */
public class NextCalendarRequest {
    private LocalDate nextForecastDate;

    public NextCalendarRequest() {
    }

    public LocalDate getNextForecastDate() {
        return nextForecastDate;
    }

    public void setNextForecastDate(LocalDate nextForecastDate) {
        this.nextForecastDate = nextForecastDate;
    }
}
