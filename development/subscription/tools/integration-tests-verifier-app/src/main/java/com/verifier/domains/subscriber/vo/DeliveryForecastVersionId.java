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
public class DeliveryForecastVersionId implements Serializable {
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate forecastDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate deliveryDate;
    private double weightRangeMin;
    private double weightRangeMax;

    public DeliveryForecastVersionId(){}
    public DeliveryForecastVersionId(LocalDate deliveryDate, LocalDate forecastDate, double weightRangeMin, double weightRangeMax) {
        this.forecastDate = forecastDate;
        this.deliveryDate = deliveryDate;
        this.weightRangeMin = weightRangeMin;
        this.weightRangeMax = weightRangeMax;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
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

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setWeightRangeMin(double weightRangeMin) {
        this.weightRangeMin = weightRangeMin;
    }

    public void setWeightRangeMax(double weightRangeMax) {
        this.weightRangeMax = weightRangeMax;
    }
}
