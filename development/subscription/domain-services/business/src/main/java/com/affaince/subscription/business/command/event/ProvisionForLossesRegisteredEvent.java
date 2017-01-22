package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForLossesRegisteredEvent {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForLosses;

    public ProvisionForLossesRegisteredEvent(LocalDate startDate, LocalDate endDate, double provisionForLosses) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForLosses=provisionForLosses;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForLosses() {
        return provisionForLosses;
    }
}
