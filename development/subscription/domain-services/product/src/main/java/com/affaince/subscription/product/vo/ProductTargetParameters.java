package com.affaince.subscription.product.vo;

import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 21-10-2016.
 */
public class ProductTargetParameters {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long numberofNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfTotalSubscriptions;
    private double fixedExpensesPerPeriod;
    private double variableExpensesPerPeriod;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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
