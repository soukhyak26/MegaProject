package com.test.verifier.domains.business.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/4/2017.
 */
public class ProvisionSegment {
    private LocalDate startDate;
    private LocalDate endDate;
    private double expectedAmount;
    private double provisionedAmount;

    public ProvisionSegment(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getExpectedAmount() {
        return expectedAmount;
    }

    public double getProvisionedAmount() {
        return provisionedAmount;
    }

    public void credit(double additionalAmount){
        provisionedAmount +=additionalAmount;
    }
    public void debit(double amountTobeDeducted){
        provisionedAmount -=amountTobeDeducted;
    }

    public void setExpectedAmount(double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }
}
