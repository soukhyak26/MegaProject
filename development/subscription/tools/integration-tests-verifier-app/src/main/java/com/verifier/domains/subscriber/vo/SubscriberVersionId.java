package com.verifier.domains.subscriber.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

public class SubscriberVersionId implements Serializable {
    private LocalDate startDate;
    private LocalDate forecastDate;

    public SubscriberVersionId(){}
    public SubscriberVersionId(LocalDate startDate,LocalDate forecastDate) {
        this.startDate = startDate;
        this.forecastDate = forecastDate;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }
}