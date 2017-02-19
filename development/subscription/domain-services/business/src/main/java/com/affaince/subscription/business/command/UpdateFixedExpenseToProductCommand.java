package com.affaince.subscription.business.command;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10-01-2017.
 */
public class UpdateFixedExpenseToProductCommand {
    private String productId;
    private LocalDate distributionDate;
    private double distributionAmountPerUnit;
    public UpdateFixedExpenseToProductCommand(String productId,LocalDate distributionDate,double distributionAmountPerUnit) {
        this.productId=productId;
        this.distributionDate=distributionDate;
        this.distributionAmountPerUnit=distributionAmountPerUnit;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getDistributionDate() {
        return distributionDate;
    }

    public double getDistributionAmountPerUnit() {
        return distributionAmountPerUnit;
    }
}
