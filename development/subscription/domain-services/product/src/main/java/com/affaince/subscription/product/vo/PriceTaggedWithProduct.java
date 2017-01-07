package com.affaince.subscription.product.vo;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 27-05-2016.
 */
public class PriceTaggedWithProduct  implements Comparable<PriceTaggedWithProduct> {
    private String taggedPriceVersionId;
    private double purchasePricePerUnit;
    private double breakEvenPrice;
    private double MRP;
    private LocalDate taggedStartDate;
    private LocalDate taggedEndDate;

    public PriceTaggedWithProduct(String taggedPriceVersionId, double purchasePricePerUnit, double MRP, LocalDate taggedStartDate) {
        this.taggedPriceVersionId = taggedPriceVersionId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.taggedStartDate = taggedStartDate;
    }

    public PriceTaggedWithProduct(String taggedPriceVersionId, double purchasePricePerUnit, double MRP, LocalDate taggedStartDate, LocalDate taggedEndDate) {
        this.taggedPriceVersionId = taggedPriceVersionId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.taggedStartDate = taggedStartDate;
        this.taggedEndDate = taggedEndDate;
    }

    public PriceTaggedWithProduct() {
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

    public LocalDate getTaggedStartDate() {
        return taggedStartDate;
    }

    public void setTaggedStartDate(LocalDate taggedStartDate) {
        this.taggedStartDate = taggedStartDate;
    }

    public LocalDate getTaggedEndDate() {
        return taggedEndDate;
    }

    public void setTaggedEndDate(LocalDate taggedEndDate) {
        this.taggedEndDate = taggedEndDate;
    }

    public String getTaggedPriceVersionId() {
        return taggedPriceVersionId;
    }

    public void setTaggedPriceVersionId(String taggedPriceVersionId) {
        this.taggedPriceVersionId = taggedPriceVersionId;
    }

    public double getBreakEvenPrice() {
        return breakEvenPrice;
    }

    public void setBreakEvenPrice(double breakEvenPrice) {
        this.breakEvenPrice = breakEvenPrice;
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
