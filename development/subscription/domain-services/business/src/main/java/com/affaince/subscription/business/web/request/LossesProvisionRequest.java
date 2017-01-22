package com.affaince.subscription.business.web.request;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 17-01-2017.
 */
public class LossesProvisionRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForLosses;

    public LossesProvisionRequest(LocalDate startDate, LocalDate endDate, double provisionForLosses) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForLosses = provisionForLosses;
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
