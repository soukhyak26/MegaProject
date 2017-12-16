package com.affaince.subscription.business.event;


import org.joda.time.LocalDate;

/**
 * Created by mandar on 19-02-2017.
 */
public class CostOfDeliveredGoodsDebitedEvent {
    private Integer businessAccountId;
    private String productId;
    private double amountToBeDebited;
    private double revisedProvisionAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public CostOfDeliveredGoodsDebitedEvent(Integer businessAccountId,String productId, double amountToBeDebited,double revisedProvisionAmount,LocalDate startDate,LocalDate endDate) {
        this.businessAccountId=businessAccountId;
        this.productId=productId;
        this.amountToBeDebited=amountToBeDebited;
        this.revisedProvisionAmount=revisedProvisionAmount;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public String getProductId() {
        return productId;
    }

    public double getRevisedProvisionAmount() {
        return revisedProvisionAmount;
    }

    public double getAmountToBeDebited() {
        return amountToBeDebited;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
