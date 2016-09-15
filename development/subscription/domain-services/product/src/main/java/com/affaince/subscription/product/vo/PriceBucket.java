package com.affaince.subscription.product.vo;

import org.joda.time.LocalDateTime;

/**
 * Created by mandark on 28-11-2015.
 */
public class PriceBucket {
    //No need to maintain purchase price versions in each basket
    //private Map<LocalDate, Double> purchasePricePerUnitVersions;
    private PriceTaggedWithProduct taggedPriceVersion;
    private double offeredPricePerUnit;
    private double percentDiscountPerUnit;
    private long totalQuantitySusbcribed;
    //no need to maintain MPR versions in each basket
    //private Map<LocalDate, Double> MRPVersions;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedSubscriptionsAssociatedWithAPrice;
    private long numberOfExistingSubscriptionsAssociatedWithAPrice;
    private double slope;

    public PriceBucket() {
    }

    public PriceBucket(double purchasePricePerUnit, double MRP, LocalDateTime fromDate, LocalDateTime toDate, long numberOfNewCustomersAssociatedWithAPrice, long numberOfChurnedSubscriptionsAssociatedWithAPrice) {

        this.taggedPriceVersion = new PriceTaggedWithProduct(purchasePricePerUnit,MRP,fromDate);
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
        this.numberOfChurnedSubscriptionsAssociatedWithAPrice = numberOfChurnedSubscriptionsAssociatedWithAPrice;
    }

    public PriceBucket(PriceBucket priceBucket) {
        this.taggedPriceVersion = priceBucket.getTaggedPriceVersion();
        this.offeredPricePerUnit = priceBucket.getOfferedPricePerUnit();
        this.fromDate = LocalDateTime.now();
        //TO BE CORRECTED IT SHOULD BE END OF CURRENT YEAR
        this.toDate = LocalDateTime.now();
        this.numberOfNewCustomersAssociatedWithAPrice = 0;
        this.numberOfChurnedSubscriptionsAssociatedWithAPrice = 0;

    }

    public double getOfferedPricePerUnit() {
        return offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
    }


    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public long getNumberOfNewCustomersAssociatedWithAPrice() {
        return numberOfNewCustomersAssociatedWithAPrice;
    }

    public void setNumberOfNewCustomersAssociatedWithAPrice(long numberOfNewCustomersAssociatedWithAPrice) {
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
    }

    public long getNumberOfChurnedSubscriptionsAssociatedWithAPrice() {
        return numberOfChurnedSubscriptionsAssociatedWithAPrice;
    }

    public void setNumberOfChurnedSubscriptionsAssociatedWithAPrice(long numberOfChurnedSubscriptionsAssociatedWithAPrice) {
        this.numberOfChurnedSubscriptionsAssociatedWithAPrice = numberOfChurnedSubscriptionsAssociatedWithAPrice;
    }

    public long getNumberOfExistingSubscriptionsAssociatedWithAPrice() {
        return numberOfExistingSubscriptionsAssociatedWithAPrice;
    }

    public void setNumberOfExistingSubscriptionsAssociatedWithAPrice(long numberOfExistingSubscriptionsAssociatedWithAPrice) {
        this.numberOfExistingSubscriptionsAssociatedWithAPrice = numberOfExistingSubscriptionsAssociatedWithAPrice;
    }


    public long getTotalQuantitySusbcribed() {
        return this.totalQuantitySusbcribed;
    }

    public void setTotalQuantitySusbcribed(long totalQuantitySusbcribed) {
        this.totalQuantitySusbcribed = totalQuantitySusbcribed;
    }

    public double getSlope() {
        return this.slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public void setTaggedPriceVersion(PriceTaggedWithProduct taggedPriceVersion) {
        this.taggedPriceVersion = taggedPriceVersion;
    }

    public double getPercentDiscountPerUnit() {
        return percentDiscountPerUnit;
    }

    public void setPercentDiscountPerUnit(double percentDiscountPerUnit) {
        this.percentDiscountPerUnit = percentDiscountPerUnit;
    }
}
