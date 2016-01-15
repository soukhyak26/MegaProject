package com.affaince.subscription.product.registration.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 01-01-2016.
 */
public class ForecastedPriceParameter {
    private double purchasePricePerUnit;
    private double averageOfferedPricePerUnit;
    private double MRP;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;
    private LocalDate fromDate;
    private LocalDate toDate;


    public ForecastedPriceParameter(double purchasePricePerUnit, double averageOfferedPricePerUnit, double MRP, long numberOfNewCustomersAssociatedWithAPrice, long numberOfChurnedCustomersAssociatedWithAPrice, LocalDate fromDate, LocalDate toDate) {
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.averageOfferedPricePerUnit = averageOfferedPricePerUnit;
        this.MRP = MRP;
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public double getPurchasePricePerUnit() {
        return this.purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public double getAverageOfferedPricePerUnit() {
        return this.averageOfferedPricePerUnit;
    }

    public void setAverageOfferedPricePerUnit(double averageOfferedPricePerUnit) {
        this.averageOfferedPricePerUnit = averageOfferedPricePerUnit;
    }

    public double getMRP() {
        return this.MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public long getNumberOfNewCustomersAssociatedWithAPrice() {
        return this.numberOfNewCustomersAssociatedWithAPrice;
    }

    public void setNumberOfNewCustomersAssociatedWithAPrice(long numberOfNewCustomersAssociatedWithAPrice) {
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
    }

    public long getNumberOfChurnedCustomersAssociatedWithAPrice() {
        return this.numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public void setNumberOfChurnedCustomersAssociatedWithAPrice(long numberOfChurnedCustomersAssociatedWithAPrice) {
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

}
