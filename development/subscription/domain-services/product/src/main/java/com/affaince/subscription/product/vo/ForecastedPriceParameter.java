package com.affaince.subscription.product.vo;

import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.YearMonth;

/**
 * Created by mandark on 01-01-2016.
 */
public class ForecastedPriceParameter {
    private Interval interval;
    private double purchasePricePerUnit;
    private double MRP;
    private long numberofNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private double demandDensity;
    private double averageDemandPerSubscriber;

    public ForecastedPriceParameter(LocalDate startDate,LocalDate endDate,double purchasePricePerUnit, double MRP, long numberofNewSubscriptions, long numberOfChurnedSubscritptions, double demandDensity, double averageDemandPerSubscriber) {
        this.interval=new Interval(startDate.toDateTimeAtStartOfDay(),endDate.toDateTimeAtStartOfDay());
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


    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public void setNumberofNewSubscriptions(long numberofNewSubscriptions) {
        this.numberofNewSubscriptions = numberofNewSubscriptions;
    }

    public void setNumberOfChurnedSubscriptions(long numberOfChurnedSubscriptions) {
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
    }

    public void setDemandDensity(double demandDensity) {
        this.demandDensity = demandDensity;
    }

    public void setAverageDemandPerSubscriber(double averageDemandPerSubscriber) {
        this.averageDemandPerSubscriber = averageDemandPerSubscriber;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }
}
