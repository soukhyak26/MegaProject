package com.verifier.domains.subscriber.vo;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.affaince.subscription.common.type.ForecastContentStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/28/2017.
 */
public class SubscriptionForecastParameter {
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private double valueRangeMin;
    private double valueRangeMax;
    //  private long numberOfTotalSubscriptions;
    private ForecastContentStatus forecastContentStatus;

    public SubscriptionForecastParameter(LocalDate startDate, LocalDate endDate, long numberOfNewSubscriptions, long numberOfChurnedSubscriptions, ForecastContentStatus forecastContentStatus, double valueRangeMin, double valueRangeMax) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
        this.forecastContentStatus = forecastContentStatus;
        this.valueRangeMin=valueRangeMin;
        this.valueRangeMax= valueRangeMax;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    public void setNumberOfNewSubscriptions(long numberOfNewSubscriptions) {
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public void setNumberOfChurnedSubscriptions(long numberOfChurnedSubscriptions) {
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }

    public double getValueRangeMin() {
        return valueRangeMin;
    }

    public void setValueRangeMin(double valueRangeMin) {
        this.valueRangeMin = valueRangeMin;
    }

    public double getValueRangeMax() {
        return valueRangeMax;
    }

    public void setValueRangeMax(double valueRangeMax) {
        this.valueRangeMax = valueRangeMax;
    }
}
