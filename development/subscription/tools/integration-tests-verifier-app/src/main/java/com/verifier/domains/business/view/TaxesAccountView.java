package com.verifier.domains.business.view;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "TaxesAccountView")
public class TaxesAccountView {
    @Id
    private String businessAccountId;
    private double provisionAmount;
    private double expectedProvisionAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public TaxesAccountView() {
    }

    public TaxesAccountView(String businessAccountId, double provisionAmount, double expectedProvisionAmount, LocalDateTime startDate, LocalDateTime endDate) {
        this.businessAccountId = businessAccountId;
        this.provisionAmount = provisionAmount;
        this.expectedProvisionAmount = expectedProvisionAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public void setBusinessAccountId(String businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

    public double getProvisionAmount() {
        return provisionAmount;
    }

    public void setProvisionAmount(double provisionAmount) {
        this.provisionAmount = provisionAmount;
    }

    public double getExpectedProvisionAmount() {
        return expectedProvisionAmount;
    }

    public void setExpectedProvisionAmount(double expectedProvisionAmount) {
        this.expectedProvisionAmount = expectedProvisionAmount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void debit(double amount) {
        this.provisionAmount -= amount;
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, expectedProvisionAmount));
    }

    public void credit(double amount) {
        this.provisionAmount += amount;
        //transactionList.add(new Transaction(amount, TransactionType.CREDIT, expectedProvisionAmount));
    }

}
