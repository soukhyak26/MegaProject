package com.verifier.domains.subscriber.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 9/30/2017.
 */
public class DeliveryVersionId implements Serializable{
    private LocalDate forecastDate;
    private LocalDate startDate;
    private double weightRangeMin;
    private double weightRangeMax;

    public DeliveryVersionId() {
    }

    public DeliveryVersionId(LocalDate startDate,LocalDate forecastDate, double weightRangeMin, double weightRangeMax) {
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

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setWeightRangeMin(double weightRangeMin) {
        this.weightRangeMin = weightRangeMin;
    }

    public void setWeightRangeMax(double weightRangeMax) {
        this.weightRangeMax = weightRangeMax;
    }
}
