package com.affaince.subscription.business.command.domain;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 30-01-2017.
 */
public class ProfitDebitAccount {
    private String productId;
    private double amountToBeDebited;
    private LocalDate dateOfExcessProfitCredit;

    public ProfitDebitAccount(String productId, double amountToBeDebited, LocalDate dateOfExcessProfitCredit) {
        this.productId = productId;
        this.amountToBeDebited = amountToBeDebited;
        this.dateOfExcessProfitCredit = dateOfExcessProfitCredit;
    }

    public String getProductId() {
        return productId;
    }

    public double getAmountToBeDebited() {
        return amountToBeDebited;
    }

    public LocalDate getDateOfExcessProfitCredit() {
        return dateOfExcessProfitCredit;
    }
}
