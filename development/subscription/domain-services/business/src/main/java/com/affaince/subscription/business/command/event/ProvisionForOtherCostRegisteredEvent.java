package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForOtherCostRegisteredEvent {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForOtherCost;

    public ProvisionForOtherCostRegisteredEvent(LocalDate startDate, LocalDate endDate, double provisionForOtherCost) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForOtherCost=provisionForOtherCost;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForOtherCost() {
        return provisionForOtherCost;
    }
}
