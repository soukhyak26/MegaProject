package com.affaince.subscription.product.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 27-05-2016.
 */
public class VariableExpensePerProduct implements Comparable<VariableExpensePerProduct> {
    private double variableOperatingExpPerUnit;
    private LocalDate startDate;
    private LocalDate endDate;

    public VariableExpensePerProduct(double variableOperatingExpPerUnit, LocalDate startDate) {
        this.variableOperatingExpPerUnit = variableOperatingExpPerUnit;
        this.startDate = startDate;
    }

    public double getVariableOperatingExpPerUnit() {
        return variableOperatingExpPerUnit;
    }

    public void setVariableOperatingExpPerUnit(double variableOperatingExpPerUnit) {
        this.variableOperatingExpPerUnit = variableOperatingExpPerUnit;
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
    public int compareTo(VariableExpensePerProduct expense2){
        if(this.startDate.isAfter(expense2.getStartDate())){
            return 1;
        }else{
            return -1;
        }
    }
}
