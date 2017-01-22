package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForBenefitsRegisteredEvent {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForBenefits;

    public ProvisionForBenefitsRegisteredEvent(LocalDate startDate, LocalDate endDate, double provisionForBenefits) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForBenefits=provisionForBenefits;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForBenefits() {
        return provisionForBenefits;
    }
}
