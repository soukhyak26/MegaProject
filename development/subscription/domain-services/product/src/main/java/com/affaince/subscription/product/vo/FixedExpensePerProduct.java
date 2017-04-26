package com.affaince.subscription.product.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 27-05-2016.
 */
public class FixedExpensePerProduct implements Comparable<FixedExpensePerProduct>{
    private double fixedOperatingExpPerUnit;
    private LocalDate startDate;
    private LocalDate endDate = new LocalDate(9999, 12, 31);

    public FixedExpensePerProduct(double fixedOperatingExpPerUnit, LocalDate startDate) {
        this.fixedOperatingExpPerUnit = fixedOperatingExpPerUnit;
        this.startDate = startDate;
    }

    public double getFixedOperatingExpPerUnit() {
        return this.fixedOperatingExpPerUnit;
    }

    public void setFixedOperatingExpPerUnit(double fixedOperatingExpPerUnit) {
        this.fixedOperatingExpPerUnit = fixedOperatingExpPerUnit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public int compareTo(FixedExpensePerProduct expense2){
        if(this.startDate.isAfter(expense2.getStartDate())){
            return 1;
        }else{
            return -1;
        }
    }

}
