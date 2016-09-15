package com.affaince.subscription.product.web.request;

import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 13-09-2016.
 */
public class NextCalendarRequest {
    final LocalDateTime nextForecastDate;

    public NextCalendarRequest(LocalDateTime nextForecastDate) {
        this.nextForecastDate = nextForecastDate;
    }

    public LocalDateTime getNextForecastDate() {
        return nextForecastDate;
    }
}
