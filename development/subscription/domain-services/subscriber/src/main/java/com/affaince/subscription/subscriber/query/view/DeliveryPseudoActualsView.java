package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/23/2017.
 */
@Document(collection = "DeliveryPseudoActualsView")
public class DeliveryPseudoActualsView {
    @Id
    private LocalDate forecastDate;
    private double weightRangeMin;
    private double weightRangeMax;
    private double deliveryCount;
    private LocalDate startDate;
    private ForecastContentStatus forecastContentStatus;

    public DeliveryPseudoActualsView(LocalDate forecastDate,LocalDate startDate){
        this.forecastDate = forecastDate;
        this.startDate = startDate;
    }
    public DeliveryPseudoActualsView(LocalDate forecastDate, double weightRangeMin, double weightRangeMax, double deliveryCount, LocalDate startDate, ForecastContentStatus forecastContentStatus) {
        this.forecastDate = forecastDate;
        this.weightRangeMin = weightRangeMin;
        this.weightRangeMax = weightRangeMax;
        this.deliveryCount = deliveryCount;
        this.startDate = startDate;
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

    public double getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(double deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }
}
