package com.affaince.subscription.subscriber.vo;

import org.joda.time.LocalDate;
import scala.Serializable;

/**
 * Created by mandar on 9/30/2017.
 */
public class DeliveryForecastVersionId implements Serializable{
    private final LocalDate forecastDate;
    private final LocalDate deliveryDate;
    private final double weightRangeMin;
    private final double weightRangeMax;

    public DeliveryForecastVersionId(LocalDate forecastDate, LocalDate deliveryDate, double weightRangeMin, double weightRangeMax) {
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
}
