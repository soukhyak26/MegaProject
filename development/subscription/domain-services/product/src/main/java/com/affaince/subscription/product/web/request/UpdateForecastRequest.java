package com.affaince.subscription.product.web.request;

import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 13-09-2016.
 */
public class UpdateForecastRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double purchasePricePerUnit;
    private double MRP;
    private long numberofNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfTotalSubscriptions;

    public UpdateForecastRequest(LocalDateTime startDate, LocalDateTime endDate, double purchasePricePerUnit, double MRP, long numberofNewSubscriptions, long numberOfChurnedSubscriptions, long numberOfTotalSubscriptions) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.numberofNewSubscriptions = numberofNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
        this.numberOfTotalSubscriptions = numberOfTotalSubscriptions;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public long getNumberofNewSubscriptions() {
        return numberofNewSubscriptions;
    }

    public void setNumberofNewSubscriptions(long numberofNewSubscriptions) {
        this.numberofNewSubscriptions = numberofNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public void setNumberOfChurnedSubscriptions(long numberOfChurnedSubscriptions) {
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
    }

    public long getNumberOfTotalSubscriptions() {
        return numberOfTotalSubscriptions;
    }

    public void setNumberOfTotalSubscriptions(long numberOfTotalSubscriptions) {
        this.numberOfTotalSubscriptions = numberOfTotalSubscriptions;
    }
}
