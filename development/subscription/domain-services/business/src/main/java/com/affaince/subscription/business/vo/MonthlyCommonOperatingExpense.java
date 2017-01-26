package com.affaince.subscription.business.vo;

import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.joda.time.YearMonth;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class MonthlyCommonOperatingExpense{
    private String id;
    YearMonth monthOfYear;
    private String expenseHeader;
    private double monthlyExpenseAmount;
    private SensitivityCharacteristic sensitivityCharacteristic;

    public MonthlyCommonOperatingExpense() {

    }

    public MonthlyCommonOperatingExpense(String id, YearMonth monthOfYear, String expenseHeader, double amount, SensitivityCharacteristic sensitivityCharacteristic) {
        //Here the assumption is that common operating expense are going to remain the same through out year independent of business dynamics..
        //at which event we should convey per unit unit expense to the product??????
        this.id = id;
        this.monthOfYear=monthOfYear;
        this.expenseHeader = expenseHeader;
        this.monthlyExpenseAmount = amount;
        this.sensitivityCharacteristic = sensitivityCharacteristic;

    }

    public String getId() {
        return this.id;
    }

    public String getExpenseHeader() {
        return this.expenseHeader;
    }

    public double getMonthlyExpenseAmount() {
        return monthlyExpenseAmount;
    }

    public SensitivityCharacteristic getSensitivityCharacteristic() {
        return this.sensitivityCharacteristic;
    }

}
