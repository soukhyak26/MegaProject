package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForTaxesRegisteredEvent {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForTaxes;

    public ProvisionForTaxesRegisteredEvent(LocalDate startDate, LocalDate endDate, double provisionForTaxes) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForTaxes=provisionForTaxes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForTaxes() {
        return provisionForTaxes;
    }
}
