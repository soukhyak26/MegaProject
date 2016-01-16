package com.affaince.subscription.product.registration.query.view;

import com.affaince.subscription.common.type.Period;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Document(collection = "CommonOperatingExpenseView")
public class CommonOperatingExpenseView {
    @Id
    private String id;
    private String expenseHeader;
    private double amount;
    private Period period;

    public CommonOperatingExpenseView(String id, String expenseHeader, double amount, Period period) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.period = period;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
