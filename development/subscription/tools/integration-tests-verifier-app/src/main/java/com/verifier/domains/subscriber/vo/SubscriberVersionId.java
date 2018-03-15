package com.verifier.domains.subscriber.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 9/30/2017.
 */
public class SubscriberVersionId implements Serializable {
    private final LocalDate startDate;
    private final LocalDate forecastDate;


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
}
