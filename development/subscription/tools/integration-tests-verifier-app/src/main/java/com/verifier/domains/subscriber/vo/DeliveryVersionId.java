package com.verifier.domains.subscriber.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 9/30/2017.
 */
public class DeliveryVersionId implements Serializable{
    private final LocalDate forecastDate;
    private final LocalDate startDate;
    private final double weightRangeMin;
    private final double weightRangeMax;

    public DeliveryVersionId(LocalDate forecastDate, LocalDate startDate, double weightRangeMin, double weightRangeMax) {
        this.forecastDate = forecastDate;
        this.startDate=startDate;
        this.weightRangeMin = weightRangeMin;
        this.weightRangeMax = weightRangeMax;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public double getWeightRangeMin() {
        return weightRangeMin;
    }

    public double getWeightRangeMax() {
        return weightRangeMax;
    }
}
