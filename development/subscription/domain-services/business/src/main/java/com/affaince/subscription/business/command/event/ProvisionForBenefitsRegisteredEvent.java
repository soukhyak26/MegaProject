package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForBenefitsRegisteredEvent {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForBenefits;

    public ProvisionForBenefitsRegisteredEvent(Integer id,LocalDate startDate, LocalDate endDate, double provisionForBenefits) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForBenefits=provisionForBenefits;
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

    public double getProvisionForBenefits() {
        return provisionForBenefits;
    }
}
