package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/23/2017.
 */
@Document(collection = "DeliveryForecastView")
public class DeliveryForecastView {
    @Id
    private LocalDate forecastDate;
    private double weightRangeMin;
    private double weightRangeMax;
    private long deliveryCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private ForecastContentStatus forecastContentStatus;

    public DeliveryForecastView(LocalDate forecastDate, LocalDate startDate, LocalDate endDate) {
        this.forecastDate = forecastDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.forecastContentStatus=ForecastContentStatus.ACTIVE;
    }

    public DeliveryForecastView(LocalDate forecastDate, double weightRangeMin, double weightRangeMax, long deliveryCount, LocalDate startDate, LocalDate endDate, ForecastContentStatus forecastContentStatus) {
        this.forecastDate = forecastDate;
        this.weightRangeMin = weightRangeMin;
        this.weightRangeMax = weightRangeMax;
        this.deliveryCount = deliveryCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.forecastContentStatus = forecastContentStatus;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

    public double getWeightRangeMin() {
        return weightRangeMin;
    }

    public void setWeightRangeMin(double weightRangeMin) {
        this.weightRangeMin = weightRangeMin;
    }

    public double getWeightRangeMax() {
        return weightRangeMax;
    }

    public void setWeightRangeMax(double weightRangeMax) {
        this.weightRangeMax = weightRangeMax;
    }

    public long getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(long deliveryCount) {
        this.deliveryCount = deliveryCount;
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

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }
}
