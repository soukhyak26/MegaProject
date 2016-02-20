package com.affaince.subscription.business.query.view;

import org.joda.time.YearMonth;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Document(collection = "CommonOperatingExpenseView")
public class CommonOperatingExpenseView {
    @Id
    private final String commonExpenseAggregateId;
    private String expenseHeader;
    private double amount;
    private YearMonth monthOfYear;

/*
    public CommonOperatingExpenseView(String id, String expenseHeader, double amount, Period period) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.period = period;
    }
*/

    public CommonOperatingExpenseView(String aggregateId, String expenseHeader, double amount, YearMonth monthOfYear) {
        this.commonExpenseAggregateId = aggregateId;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.monthOfYear = monthOfYear;
    }

    public String getCommonExpenseAggregateId() {
        return this.commonExpenseAggregateId;
    }

    public String getExpenseHeader() {
        return expenseHeader;
    }

    public void setExpenseHeader(String expenseHeader) {
        this.expenseHeader = expenseHeader;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }

    public void setMonthOfYear(YearMonth monthOfYear) {
        this.monthOfYear = monthOfYear;
    }
}