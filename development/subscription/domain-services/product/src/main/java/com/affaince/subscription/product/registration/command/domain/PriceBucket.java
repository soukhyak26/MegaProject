package com.affaince.subscription.product.registration.command.domain;

import org.joda.time.LocalDate;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by mandark on 28-11-2015.
 */
public class PriceBucket {
    private Map<LocalDate, Double> purchasePricePerUnitVersions;
    private double offeredPricePerUnit;

    private Map<LocalDate, Double> MRPVersions;
    private LocalDate fromDate;
    private LocalDate toDate;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;
    private long numberOfExistingCustomersAssociatedWithAPrice;

    public PriceBucket() {
        purchasePricePerUnitVersions = new TreeMap<>();
        MRPVersions = new TreeMap<>();
    }

    public PriceBucket(double purchasePricePerUnit, double offeredPricePerUnit, double MRP, LocalDate fromDate, LocalDate toDate, long numberOfNewCustomersAssociatedWithAPrice, long numberOfChurnedCustomersAssociatedWithAPrice) {
        this.purchasePricePerUnitVersions = new TreeMap<>();
        this.MRPVersions = new TreeMap<>();

        this.purchasePricePerUnitVersions.put(fromDate, purchasePricePerUnit);
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.MRPVersions.put(fromDate, MRP);
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public PriceBucket(PriceBucket priceBucket) {
        this.purchasePricePerUnitVersions = priceBucket.getPurchasePricePerUnitVersions();
        this.offeredPricePerUnit = priceBucket.getOfferedPricePerUnit();
        this.MRPVersions = priceBucket.getMRPVersions();
        this.fromDate = LocalDate.now();
        //TO BE CORRECTED IT SHOULD BE END OF CURRENT YEAR
        this.toDate = LocalDate.now();
        this.numberOfNewCustomersAssociatedWithAPrice = 0;
        this.numberOfChurnedCustomersAssociatedWithAPrice = 0;

    }

    public Map<LocalDate, Double> getPurchasePricePerUnitVersions() {
        return purchasePricePerUnitVersions;
    }

    public void setPurchasePricePerUnitVersions(Map<LocalDate, Double> purchasePricePerUnitVersions) {
        this.purchasePricePerUnitVersions = purchasePricePerUnitVersions;
    }

    public void addPurchasePricePerUnitVersion(LocalDate fromDate, double newPurchasePrice) {
        if (null != purchasePricePerUnitVersions) {
            purchasePricePerUnitVersions.put(fromDate, newPurchasePrice);
        } else {
            //RAISE EXCEPTION
        }
    }

    public double getOfferedPricePerUnit() {
        return offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
    }

    public Map<LocalDate, Double> getMRPVersions() {
        return MRPVersions;
    }

    public void setMRPVersions(Map<LocalDate, Double> MRPVersions) {
        this.MRPVersions = MRPVersions;
    }

    public void addMRPVersion(LocalDate fromDate, double newMRP) {
        if (null != MRPVersions) {
            MRPVersions.put(fromDate, newMRP);
        } else {
            //RAISE EXCEPTION
        }
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

    public double getLatestPurchasePricePerUnitVersion() {
        Set<LocalDate> keySet = purchasePricePerUnitVersions.keySet();
        LocalDate maxDate = null;
        //CHECK OF COMPARISION SYNTAX IS CORRECT
        for (LocalDate date : keySet) {
            if (date.compareTo(maxDate) > 1) {
                maxDate = date;
            }
        }
        return purchasePricePerUnitVersions.get(maxDate);
    }

    public double getLatestMRPVersion() {
        Set<LocalDate> keySet = MRPVersions.keySet();
        LocalDate maxDate = null;
        //CHECK OF COMPARISION SYNTAX IS CORRECT
        for (LocalDate date : keySet) {
            if (date.compareTo(maxDate) > 1) {
                maxDate = date;
            }
        }
        return MRPVersions.get(maxDate);
    }

}
