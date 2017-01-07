package com.affaince.subscription.product.web.request;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-09-2016.
 */
public class NextCalendarRequest {
    final LocalDate nextForecastDate;

    public NextCalendarRequest(LocalDate nextForecastDate) {
        this.nextForecastDate = nextForecastDate;
    }

    public LocalDate getNextForecastDate() {
        return nextForecastDate;
    }
}
