package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 17-01-2017.
 */
public class ProvisionForNodalRegisteredEvent {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForNodal;

    public ProvisionForNodalRegisteredEvent(LocalDate startDate, LocalDate endDate, double provisionForNodal) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForNodal=provisionForNodal;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForNodal() {
        return provisionForNodal;
    }
}
