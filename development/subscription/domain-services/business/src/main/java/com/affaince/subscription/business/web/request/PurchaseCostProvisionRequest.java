package com.affaince.subscription.business.web.request;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class PurchaseCostProvisionRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForPurchaseOfGoods;

    public PurchaseCostProvisionRequest(LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.provisionForPurchaseOfGoods = provisionForPurchaseOfGoods;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForPurchaseOfGoods() {
        return provisionForPurchaseOfGoods;
    }
}
