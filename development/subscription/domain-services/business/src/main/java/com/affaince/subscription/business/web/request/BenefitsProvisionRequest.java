package com.affaince.subscription.business.web.request;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 17-01-2017.
 */
public class BenefitsProvisionRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForBenefits;

    public BenefitsProvisionRequest(LocalDate startDate, LocalDate endDate, double provisionForBenefits) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForBenefits = provisionForBenefits;
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
