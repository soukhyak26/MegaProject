package com.verifier.domains.subscriber.vo;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 9/30/2017.
 */
public class SubscriptionVersionId implements Serializable{
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate forecastDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    private double valueRangeMin;
    private double valueRangeMax;

    public SubscriptionVersionId(){}
    public SubscriptionVersionId(LocalDate startDate,LocalDate forecastDate, double valueRangeMin, double valueRangeMax) {
        this.forecastDate = forecastDate;
        this.startDate=startDate;
        this.valueRangeMin = valueRangeMin;
        this.valueRangeMax = valueRangeMax;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public double getValueRangeMin() {
        return valueRangeMin;
    }

    public double getValueRangeMax() {
        return valueRangeMax;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setValueRangeMin(double valueRangeMin) {
        this.valueRangeMin = valueRangeMin;
    }

    public void setValueRangeMax(double valueRangeMax) {
        this.valueRangeMax = valueRangeMax;
    }
}
