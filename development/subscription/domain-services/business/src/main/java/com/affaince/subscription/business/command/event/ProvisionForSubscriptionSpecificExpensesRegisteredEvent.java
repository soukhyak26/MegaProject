package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForSubscriptionSpecificExpensesRegisteredEvent {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForSubscriptionSpecificExpenses;

    public ProvisionForSubscriptionSpecificExpensesRegisteredEvent(LocalDate startDate, LocalDate endDate, double provisionForSubscriptionSpecificExpenses) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForSubscriptionSpecificExpenses=provisionForSubscriptionSpecificExpenses;
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
