package com.affaince.subscription.business.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 03-02-2017.
 */
public class ProfitCreditedEvent {
    private Integer businessAccountId;
    private String productId;
    private double amountToBeCredited;
    private LocalDate dateOfTransaction;
    public ProfitCreditedEvent(Integer businessAccountId, String productId,double amountToBeCredited,LocalDate dateOfTransaction) {
        this.businessAccountId=businessAccountId;
        this.productId=productId;
        this.amountToBeCredited=amountToBeCredited;
        this.dateOfTransaction=dateOfTransaction;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public double getAmountToBeCredited() {
        return amountToBeCredited;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public String getProductId() {
        return productId;
    }
}
