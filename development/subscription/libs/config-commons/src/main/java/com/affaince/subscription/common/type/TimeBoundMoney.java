package com.affaince.subscription.common.type;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 13-02-2016.
 */
public class TimeBoundMoney {
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private double amount;

    public TimeBoundMoney(double amount, LocalDate fromDate, LocalDate toDate) {
        this.amount = amount;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void addToTimeBoundMoney(double amount, int periodInDays) {
        this.amount += amount;
        toDate.plusDays(periodInDays);
    }

    public void debitFromTimeBoundMoney(double amount) {
        this.amount -= amount;
    }
}
