package com.affaince.subscription.product.command.event;


import org.joda.time.LocalDate;

/**
 * Created by mandar on 9/10/2017.
 */
public class ProductForecastCreatedEvent {
    private Object Id;
    private String forecastString;
    private LocalDate forecastDate;

    public ProductForecastCreatedEvent(Object id, String forecastString, LocalDate forecastDate) {
        Id = id;
        this.forecastString = forecastString;
        this.forecastDate = forecastDate;
    }

    public Object getId() {
        return Id;
    }

    public String getForecastString() {
        return forecastString;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }
}
