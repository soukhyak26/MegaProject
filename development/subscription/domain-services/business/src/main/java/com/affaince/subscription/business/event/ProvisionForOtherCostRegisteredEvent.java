package com.affaince.subscription.business.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForOtherCostRegisteredEvent {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForOtherCost;

    public ProvisionForOtherCostRegisteredEvent(Integer id,LocalDate startDate, LocalDate endDate, double provisionForOtherCost) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForOtherCost=provisionForOtherCost;
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

    public double getProvisionForOtherCost() {
        return provisionForOtherCost;
    }
}
