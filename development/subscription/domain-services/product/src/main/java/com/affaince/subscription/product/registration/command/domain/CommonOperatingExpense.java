package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.product.registration.command.event.CommonOperatingExpenseAddedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class CommonOperatingExpense {
    private String id;
    private String expenseHeader;
    private double amount;
    private Period period;

    public CommonOperatingExpense(String id, String expenseHeader, double amount, Period period) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.period = period;
    }

    public CommonOperatingExpense() {
    }

    @EventSourcingHandler
    public void on(CommonOperatingExpenseAddedEvent event) {
        this.id = event.getId();
        this.expenseHeader = event.getExpenseHeader();
        this.amount = event.getAmount();
        this.period = event.getPeriod();
    }

    public double transalateExpenseAmountToMonthlyExpense() {
        if (this.period.getUnit() == PeriodUnit.WEEK) {
            return (this.amount / period.getValue()) * 4;
        } else if (this.period.getUnit() == PeriodUnit.YEAR) {
            return this.amount / (period.getValue() * 12);
        }
        return this.amount;
    }
}
