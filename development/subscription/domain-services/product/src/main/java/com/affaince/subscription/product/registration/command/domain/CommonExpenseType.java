package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import org.joda.time.YearMonth;

/**
 * Created by mandark on 24-01-2016.
 */
public class CommonExpenseType {
    private String commonExpenseAggregateId;
    private String expenseHeader;
    private double amount;
    private Period period;
    private YearMonth monthOfYear;

    public CommonExpenseType(String aggregateId, String expenseHeader, YearMonth monthOfYear) {
        this.commonExpenseAggregateId = aggregateId;
        this.expenseHeader = expenseHeader;
        this.monthOfYear = monthOfYear;
    }

    public CommonExpenseType(String aggregateId, String expenseHeader, double amount, Period period, YearMonth monthOfYear) {
        this.commonExpenseAggregateId = aggregateId;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.period = period;
        this.monthOfYear = monthOfYear;
    }

    public String getExpenseHeader() {
        return this.expenseHeader;
    }

    public void setExpenseHeader(String expenseHeader) {
        this.expenseHeader = expenseHeader;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Period getPeriod() {
        return this.period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }

    public void setMonthOfYear(YearMonth monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    public String getCommonExpenseAggregateId() {
        return this.commonExpenseAggregateId;
    }

    public void setCommonExpenseAggregateId(String commonExpenseAggregateId) {
        this.commonExpenseAggregateId = commonExpenseAggregateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CommonExpenseType that = (CommonExpenseType) o;

        if (Double.compare(that.amount, this.amount) != 0) return false;
        if (!this.expenseHeader.equals(that.expenseHeader)) return false;
        if (!this.period.equals(that.period)) return false;
        return monthOfYear.equals(that.monthOfYear);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = this.expenseHeader.hashCode();
        temp = Double.doubleToLongBits(this.amount);
        result = 31 * result + (int) (temp ^ temp >>> 32);
        result = 31 * result + this.period.hashCode();
        result = 31 * result + this.monthOfYear.hashCode();
        return result;
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
