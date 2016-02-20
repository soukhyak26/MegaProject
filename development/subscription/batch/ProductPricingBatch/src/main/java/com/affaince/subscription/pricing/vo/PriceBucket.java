package com.affaince.subscription.pricing.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 28-11-2015.
 */
public class PriceBucket {
    //No need to maintain purchase price versions in each basket
    //private Map<LocalDate, Double> purchasePricePerUnitVersions;
    private double purchasePricePerUnit;
    private double offeredPricePerUnit;
    private long totalQuantitySusbcribed;
    //no need to maintain MPR versions in each basket
    //private Map<LocalDate, Double> MRPVersions;
    private double MRP;
    private LocalDate fromDate;
    private LocalDate toDate;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;
    private long numberOfExistingCustomersAssociatedWithAPrice;

    public PriceBucket() {
    }

    public PriceBucket(double purchasePricePerUnit, double MRP, LocalDate fromDate, LocalDate toDate, long numberOfNewCustomersAssociatedWithAPrice, long numberOfChurnedCustomersAssociatedWithAPrice) {

        this.purchasePricePerUnit = purchasePricePerUnit;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.MRP = MRP;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public PriceBucket(PriceBucket priceBucket) {
        this.purchasePricePerUnit = priceBucket.getPurchasePricePerUnit();
        this.offeredPricePerUnit = priceBucket.getOfferedPricePerUnit();
        this.MRP = priceBucket.getMRP();
        this.fromDate = LocalDate.now();
        //TO BE CORRECTED IT SHOULD BE END OF CURRENT YEAR
        this.toDate = LocalDate.now();
        this.numberOfNewCustomersAssociatedWithAPrice = 0;
        this.numberOfChurnedCustomersAssociatedWithAPrice = 0;

    }

    public double getOfferedPricePerUnit() {
        return offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
    }

    public Double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public long getNumberOfNewCustomersAssociatedWithAPrice() {
        return numberOfNewCustomersAssociatedWithAPrice;
    }

    public void setNumberOfNewCustomersAssociatedWithAPrice(long numberOfNewCustomersAssociatedWithAPrice) {
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
    }

    public long getNumberOfChurnedCustomersAssociatedWithAPrice() {
        return numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public void setNumberOfChurnedCustomersAssociatedWithAPrice(long numberOfChurnedCustomersAssociatedWithAPrice) {
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public long getNumberOfExistingCustomersAssociatedWithAPrice() {
        return numberOfExistingCustomersAssociatedWithAPrice;
    }

    public void setNumberOfExistingCustomersAssociatedWithAPrice(long numberOfExistingCustomersAssociatedWithAPrice) {
        this.numberOfExistingCustomersAssociatedWithAPrice = numberOfExistingCustomersAssociatedWithAPrice;
    }

    public double getPurchasePricePerUnit() {
        return this.purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public long getTotalQuantitySusbcribed() {
        return this.totalQuantitySusbcribed;
    }

    public void setTotalQuantitySusbcribed(long totalQuantitySusbcribed) {
        this.totalQuantitySusbcribed = totalQuantitySusbcribed;
    }
}
