package com.affaince.subscription.business.web.request;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 17-01-2017.
 */
public class OthersProvisionRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForOtherCost;

    public OthersProvisionRequest(LocalDate startDate, LocalDate endDate, double provisionForOtherCost) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForOtherCost = provisionForOtherCost;
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
