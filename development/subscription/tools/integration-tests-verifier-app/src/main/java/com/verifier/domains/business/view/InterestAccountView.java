package com.verifier.domains.business.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;

public class InterestAccountView {
    @Id
    private String businessAccountId;
    private double currentAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public InterestAccountView(){}
    public InterestAccountView(String businessAccountId, double currentAmount, LocalDate startDate, LocalDate endDate) {
        this.businessAccountId = businessAccountId;
        this.currentAmount = currentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public void setBusinessAccountId(String businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
