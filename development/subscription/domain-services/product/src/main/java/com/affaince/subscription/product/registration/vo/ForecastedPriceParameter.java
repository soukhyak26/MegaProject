package com.affaince.subscription.product.registration.vo;

import org.joda.time.YearMonth;

/**
 * Created by mandark on 01-01-2016.
 */
public class ForecastedPriceParameter {
    private double purchasePricePerUnit;
    private double MRP;
    private long numberofNewSubscriptions;
    private long numberOfChurnedSubscritptions;
    private double demandDensity;
    private double averageDemandPerSubscriber;
    private YearMonth monthOfYear;

    public ForecastedPriceParameter(double purchasePricePerUnit, double MRP, long numberofNewSubscriptions, long numberOfChurnedSubscritptions, double demandDensity, double averageDemandPerSubscriber, YearMonth monthOfYear) {
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.numberofNewSubscriptions = numberofNewSubscriptions;
        this.numberOfChurnedSubscritptions = numberOfChurnedSubscritptions;
        this.demandDensity = demandDensity;
        this.averageDemandPerSubscriber = averageDemandPerSubscriber;
        this.monthOfYear = monthOfYear;
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

    public long getNumberOfChurnedSubscritptions() {
        return this.numberOfChurnedSubscritptions;
    }

    public double getDemandDensity() {
        return this.demandDensity;
    }

    public double getAverageDemandPerSubscriber() {
        return this.averageDemandPerSubscriber;
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }
}
