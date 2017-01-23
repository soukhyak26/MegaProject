package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForTaxesRegisteredEvent {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForTaxes;

    public ProvisionForTaxesRegisteredEvent(Integer id,LocalDate startDate, LocalDate endDate, double provisionForTaxes) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForTaxes=provisionForTaxes;
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

    public double getProvisionForTaxes() {
        return provisionForTaxes;
    }
}
