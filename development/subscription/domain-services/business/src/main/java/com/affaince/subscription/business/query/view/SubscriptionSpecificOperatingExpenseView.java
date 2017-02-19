package com.affaince.subscription.business.query.view;

import org.apache.spark.sql.catalyst.expressions.In;
import org.joda.time.YearMonth;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Document(collection = "SubscriptionSpecificOperatingExpenseView")
public class SubscriptionSpecificOperatingExpenseView {
    @Id
    private Integer id;
    private String expenseHeader;
    private double monthlyExpenseAmount;
    private YearMonth monthOfYear;

    public SubscriptionSpecificOperatingExpenseView(Integer id, String expenseHeader, double monthlyExpenseAmount, YearMonth monthOfYear) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.monthlyExpenseAmount = monthlyExpenseAmount;
        this.monthOfYear = monthOfYear;
    }

    public SubscriptionSpecificOperatingExpenseView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpenseHeader() {
        return expenseHeader;
    }

    public void setExpenseHeader(String expenseHeader) {
        this.expenseHeader = expenseHeader;
    }

    public double getMonthlyExpenseAmount() {
        return this.monthlyExpenseAmount;
    }

    public void setMonthlyExpenseAmount(double monthlyExpenseAmount) {
        this.monthlyExpenseAmount = monthlyExpenseAmount;
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }

    public void setMonthOfYear(YearMonth monthOfYear) {
        this.monthOfYear = monthOfYear;
    }
}
