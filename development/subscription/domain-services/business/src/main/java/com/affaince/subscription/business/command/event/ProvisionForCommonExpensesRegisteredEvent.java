package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForCommonExpensesRegisteredEvent {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForCommonExpenses;
    public ProvisionForCommonExpensesRegisteredEvent(LocalDate startDate, LocalDate endDate, double provisionForCommonExpenses) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForCommonExpenses=provisionForCommonExpenses;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForCommonExpenses() {
        return provisionForCommonExpenses;
    }
}
