package com.affaince.subscription.product.registration.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 01-01-2016.
 */
public class ForecastedPriceParameter {
    private LocalDate fromDate;
    private LocalDate toDate;
    private double purchasePricePerUnit;
    private double MRP;
    private long numberofNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private double demandDensity;
    private double averageDemandPerSubscriber;

    public ForecastedPriceParameter(LocalDate fromDate,LocalDate toDate,double purchasePricePerUnit, double MRP, long numberofNewSubscriptions, long numberOfChurnedSubscritptions, double demandDensity, double averageDemandPerSubscriber) {
        this.fromDate=fromDate;
        this.toDate=toDate;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.numberofNewSubscriptions = numberofNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscritptions;
        this.demandDensity = demandDensity;
        this.averageDemandPerSubscriber = averageDemandPerSubscriber;
    }

    public double getPurchasePricePerUnit() {
        return this.purchasePricePerUnit;
    }

    public double getMRP() {
        return this.MRP;
    }

    public long getNumberofNewSubscriptions() {
        return this.numberofNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return this.numberOfChurnedSubscriptions;
    }

    public double getDemandDensity() {
        return this.demandDensity;
    }

    public double getAverageDemandPerSubscriber() {
        return this.averageDemandPerSubscriber;
    }

    public LocalDate getFromDate() {
        return this.fromDate;
    }

    public LocalDate getToDate() {
        return this.toDate;
    }
}
