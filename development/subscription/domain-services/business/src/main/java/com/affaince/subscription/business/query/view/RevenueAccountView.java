package com.affaince.subscription.business.query.view;

import org.apache.spark.sql.catalyst.expressions.In;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "RevenueAccountView")
public class RevenueAccountView {
    @Id
    private Integer year;
    private double startAmount;
    private double currentAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public RevenueAccountView(Integer year, double startAmount, double currentAmount, LocalDateTime startDate, LocalDateTime endDate) {
        this.year = year;
        this.startAmount = startAmount;
        this.currentAmount = currentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
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
        this.currentAmount -= amount;
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, currentAmount));
    }

    public void credit(double amount) {
        this.currentAmount += amount;
        //transactionList.add(new Transaction(amount, TransactionType.CREDIT, currentAmount));
    }

}
