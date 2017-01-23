package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForSubscriptionSpecificExpensesRegisteredEvent {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForSubscriptionSpecificExpenses;

    public ProvisionForSubscriptionSpecificExpensesRegisteredEvent(Integer id,LocalDate startDate, LocalDate endDate, double provisionForSubscriptionSpecificExpenses) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForSubscriptionSpecificExpenses=provisionForSubscriptionSpecificExpenses;
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

    public double getProvisionForSubscriptionSpecificExpenses() {
        return provisionForSubscriptionSpecificExpenses;
    }
}
