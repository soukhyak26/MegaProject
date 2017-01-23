package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForCommonExpensesRegisteredEvent {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForCommonExpenses;
    public ProvisionForCommonExpensesRegisteredEvent(Integer id,LocalDate startDate, LocalDate endDate, double provisionForCommonExpenses) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForCommonExpenses=provisionForCommonExpenses;
    }

    public Integer getId() {
        return id;
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
