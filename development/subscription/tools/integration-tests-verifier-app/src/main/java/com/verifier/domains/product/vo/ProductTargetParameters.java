package com.verifier.domains.product.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 21-10-2016.
 */
public class ProductTargetParameters {
    private LocalDate startDate;
    private LocalDate endDate;
    private long numberofNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfTotalSubscriptions;
    private double fixedExpensesPerPeriod;
    private double variableExpensesPerPeriod;

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

    public long getNumberofNewSubscriptions() {
        return numberofNewSubscriptions;
    }

    public void setNumberofNewSubscriptions(long numberofNewSubscriptions) {
        this.numberofNewSubscriptions = numberofNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public void setNumberOfChurnedSubscriptions(long numberOfChurnedSubscriptions) {
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
    }

    public long getNumberOfTotalSubscriptions() {
        return numberOfTotalSubscriptions;
    }

    public void setNumberOfTotalSubscriptions(long numberOfTotalSubscriptions) {
        this.numberOfTotalSubscriptions = numberOfTotalSubscriptions;
    }

    public double getFixedExpensesPerPeriod() {
        return fixedExpensesPerPeriod;
    }

    public void setFixedExpensesPerPeriod(double fixedExpensesPerPeriod) {
        this.fixedExpensesPerPeriod = fixedExpensesPerPeriod;
    }

    public double getVariableExpensesPerPeriod() {
        return variableExpensesPerPeriod;
    }

    public void setVariableExpensesPerPeriod(double variableExpensesPerPeriod) {
        this.variableExpensesPerPeriod = variableExpensesPerPeriod;
    }

}
