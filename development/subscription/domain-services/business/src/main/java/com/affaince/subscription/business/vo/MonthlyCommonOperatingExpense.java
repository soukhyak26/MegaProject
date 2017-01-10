package com.affaince.subscription.business.vo;

import com.affaince.subscription.business.command.event.CommonExpenseCreatedEvent;
import com.affaince.subscription.business.command.event.FixedExpenseUpdatedToProductEvent;
import com.affaince.subscription.business.command.event.OperatingExpenseUpdatedEvent;
import com.affaince.subscription.business.command.event.PastCommonExpenseTypesRemovedEvent;
import com.affaince.subscription.business.process.operatingexpenses.DefaultOperatingExpensesDeterminator;
import com.affaince.subscription.business.process.operatingexpenses.OperatingExpensesDeterminator;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.YearMonth;

import java.util.Map;

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
