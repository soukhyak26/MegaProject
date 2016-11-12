package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 11-11-2016.
 */
public class ManualForecastAddedEvent {
    private String productId;
    private double purchasePricePerUnit;
    private double mrp;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfTotalSubscriptions;
    private LocalDateTime forecastStartDate;
    private LocalDateTime forecastEndDate;

    public ManualForecastAddedEvent(String productId, double purchasePricePerUnit, double mrp, long numberOfNewSubscriptions, long numberOfChurnedSubscriptions, long numberOfTotalSubscriptions, LocalDateTime forecastStartDate, LocalDateTime forecastEndDate) {
        this.productId = productId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.mrp = mrp;
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
        this.numberOfTotalSubscriptions = numberOfTotalSubscriptions;
        this.forecastStartDate = forecastStartDate;
        this.forecastEndDate = forecastEndDate;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getMrp() {
        return mrp;
    }

    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public long getNumberOfTotalSubscriptions() {
        return numberOfTotalSubscriptions;
    }

    public LocalDateTime getForecastStartDate() {
        return forecastStartDate;
    }

    public LocalDateTime getForecastEndDate() {
        return forecastEndDate;
    }

    public String getProductId() {
        return productId;
    }

}
