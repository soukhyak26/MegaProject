package com.affaince.subscription.product.event;

import com.affaince.subscription.common.type.ForecastContentStatus;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 2/28/2017.
 */
public class ManualSingularForecastAddedEvent {
    private String productId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double purchasePricePerUnit;
    private double mrp;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private ForecastContentStatus forecastContentStatus;

    public ManualSingularForecastAddedEvent(String productId, LocalDate startDate, LocalDate endDate, double purchasePricePerUnit, double mrp, long numberOfNewSubscriptions, long numberOfChurnedSubscriptions, ForecastContentStatus forecastContentStatus) {
        this.productId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.mrp = mrp;
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
        this.forecastContentStatus = forecastContentStatus;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }
}
