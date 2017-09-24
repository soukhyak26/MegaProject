package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.common.type.ForecastContentStatus;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 9/23/2017.
 */
public class DeliveryForecastCreatedEvent {
    private String forecastString;
    private LocalDate forecastDate;

    public DeliveryForecastCreatedEvent(String forecastString, LocalDate forecastDate) {
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
