package com.affaince.subscription.expensedistribution.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 27-05-2016.
 */
public class PriceTaggedWithProduct implements Comparable<PriceTaggedWithProduct> {
    private double purchasePricePerUnit;
    private double MRP;
    private LocalDate taggedStartDate;
    private LocalDate taggedEndDate;

    public PriceTaggedWithProduct(double purchasePricePerUnit, double MRP, LocalDate taggedStartDate) {
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.taggedStartDate = taggedStartDate;
    }

    public PriceTaggedWithProduct(double purchasePricePerUnit, double MRP, LocalDate taggedStartDate, LocalDate taggedEndDate) {
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.taggedStartDate = taggedStartDate;
        this.taggedEndDate = taggedEndDate;
    }

    public void setTaggedEndDate(LocalDate taggedEndDate) {
        this.taggedEndDate = taggedEndDate;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public LocalDate getTaggedStartDate() {
        return taggedStartDate;
    }

    public LocalDate getTaggedEndDate() {
        return taggedEndDate;
    }

    @Override
    public int compareTo(PriceTaggedWithProduct price2){
        if(this.taggedStartDate.isBefore(price2.getTaggedStartDate())){
            return 1;
        }else{
            return -1;
        }
    }
}
