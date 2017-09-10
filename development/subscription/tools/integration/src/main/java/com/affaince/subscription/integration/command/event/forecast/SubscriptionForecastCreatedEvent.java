package com.affaince.subscription.integration.command.event.forecast;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 9/10/2017.
 */
public class SubscriptionForecastCreatedEvent {
    private String forecastString;
    private LocalDate forecastDate;

    public SubscriptionForecastCreatedEvent(String forecastString, LocalDate forecastDate) {
        this.forecastString = forecastString;
        this.forecastDate = forecastDate;
    }

    public String getForecastString() {
        return forecastString;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }
}
