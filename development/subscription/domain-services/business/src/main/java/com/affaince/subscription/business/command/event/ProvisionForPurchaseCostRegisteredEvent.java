package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForPurchaseCostRegisteredEvent {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForPurchaseOfGoods;
    public ProvisionForPurchaseCostRegisteredEvent(Integer Id,LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForPurchaseOfGoods=provisionForPurchaseOfGoods;
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

    public double getProvisionForPurchaseOfGoods() {
        return provisionForPurchaseOfGoods;
    }
}
