package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForLossesRegisteredEvent {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForLosses;

    public ProvisionForLossesRegisteredEvent(Integer id,LocalDate startDate, LocalDate endDate, double provisionForLosses) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForLosses=provisionForLosses;
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

    public double getProvisionForLosses() {
        return provisionForLosses;
    }
}
