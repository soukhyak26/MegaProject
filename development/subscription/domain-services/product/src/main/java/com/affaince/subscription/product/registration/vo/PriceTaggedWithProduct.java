package com.affaince.subscription.product.registration.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 27-05-2016.
 */
public class PriceTaggedWithProduct  implements Comparable<PriceTaggedWithProduct> {
    private double purchasePricePerUnit;
    private double MRP;
    private LocalDate startDate;
    private LocalDate endDate;

    public PriceTaggedWithProduct(double purchasePricePerUnit, double MRP, LocalDate startDate) {
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public int compareTo(PriceTaggedWithProduct price2){
        if(this.startDate.isBefore(price2.getStartDate())){
            return 1;
        }else{
            return -1;
        }
    }
}
