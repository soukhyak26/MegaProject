package com.verifier.domains.subscriber.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 9/30/2017.
 */
public class SubscriberVersionId implements Serializable {
    private LocalDate startDate;
    private LocalDate forecastDate;

    public SubscriberVersionId(){}
    public SubscriberVersionId(LocalDate forecastDate,LocalDate startDate) {
        this.forecastDate = forecastDate;
        this.startDate = startDate;
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
