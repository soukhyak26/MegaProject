package com.affaince.subscription.business.web.request;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 17-01-2017.
 */
public class PurchaseProvisionRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForPurchaseCost;

    public PurchaseProvisionRequest(LocalDate startDate, LocalDate endDate, double provisionForPurchaseCost) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForPurchaseCost = provisionForPurchaseCost;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForPurchaseCost() {
        return provisionForPurchaseCost;
    }

}
