package com.affaince.subscription.business.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/4/2017.
 */
public class InstallmentPerCalendarPeriod {
    private LocalDate startDate;
    private LocalDate endDate;
    private double amount;

    public InstallmentPerCalendarPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getAmount() {
        return amount;
    }

    public void addAmount(double amount){
        this.amount +=amount;
    }
    public void deductAmount(double amount){
        this.amount -=amount;
    }
}
