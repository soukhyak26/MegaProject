package com.affaince.subscription.forecast.vo;

import org.joda.time.LocalDate;
import scala.Serializable;

/**
 * Created by mandar on 9/30/2017.
 */
public class SubscriptionVersionId implements Serializable{
    private final LocalDate forecastDate;
    private final LocalDate startDate;
    private final double valueRangeMin;
    private final double valueRangeMax;

    public SubscriptionVersionId(LocalDate forecastDate, LocalDate startDate, double valueRangeMin, double valueRangeMax) {
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
}
