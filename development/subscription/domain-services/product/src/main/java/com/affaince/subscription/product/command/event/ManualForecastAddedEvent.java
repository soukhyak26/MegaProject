package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 06-10-2016.
 */
public class ManualForecastAddedEvent {
    private String productId;
    private double purchasePricePerUnit;
    private double mrp;
    private long numberofNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ManualForecastAddedEvent(String productId, double purchasePricePerUnit, double mrp, long numberofNewSubscriptions, long numberOfChurnedSubscriptions, LocalDateTime startDate, LocalDateTime endDate) {
        this.productId = productId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.mrp = mrp;
        this.numberofNewSubscriptions = numberofNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getMrp() {
        return mrp;
    }

    public long getNumberofNewSubscriptions() {
        return numberofNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getProductId() {
        return productId;
    }
}
