package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.CommonExpenseCreatedEvent;
import com.affaince.subscription.business.command.event.FixedExpenseUpdatedToProductEvent;
import com.affaince.subscription.business.command.event.OperatingExpenseUpdatedEvent;
import com.affaince.subscription.business.command.event.PastCommonExpenseTypesRemovedEvent;
import com.affaince.subscription.business.process.operatingexpenses.DefaultOperatingExpensesDeterminator;
import com.affaince.subscription.business.process.operatingexpenses.OperatingExpensesDeterminator;
import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.YearMonth;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class MonthlyCommonOperatingExpense extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String id;
    YearMonth monthOfYear;
    private String expenseHeader;
    private double monthlyExpenseAmount;
    private SensitivityCharacteristic sensitivityCharacteristic;

    public MonthlyCommonOperatingExpense() {

    }

    public MonthlyCommonOperatingExpense(String id, YearMonth monthOfYear,String expenseHeader, double amount, SensitivityCharacteristic sensitivityCharacteristic) {
        //Here the assumption is that common operating expense are going to remain the same through out year independent of business dynamics..
        //at which event we should convey per unit unit expense to the product??????
        OperatingExpensesDeterminator operatingExpensesDeterminator =
                new DefaultOperatingExpensesDeterminator();
        final Map<String, Double> perUnitOperatingExpenses = operatingExpensesDeterminator.calculateOperatingExpensesPerProduct(this);
        perUnitOperatingExpenses.forEach((productId, perUnitExpense) -> apply(
                new FixedExpenseUpdatedToProductEvent(productId, SysDate.now(), perUnitExpense)
        ));

        apply(new CommonExpenseCreatedEvent(id, monthOfYear, expenseHeader, amount, sensitivityCharacteristic));
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

    @EventSourcingHandler
    public void on(CommonExpenseCreatedEvent event) {
        this.id = event.getCommonOperatingExpenseId();
        this.monthOfYear=event.getMonthOfYear();
        this.expenseHeader = event.getExpenseHeader();
        this.sensitivityCharacteristic = event.getSensitivityCharacteristic();
        this.monthlyExpenseAmount=event.getAmount();
    }


    public void updateCommonOperatingExpense(String expenseHeader,double expenseAmount,SensitivityCharacteristic sensitivityCharacteristic){
            OperatingExpensesDeterminator operatingExpensesDeterminator =
                    new DefaultOperatingExpensesDeterminator();
            final Map<String, Double> perUnitOperatingExpenses = operatingExpensesDeterminator.calculateOperatingExpensesPerProduct(this);
            perUnitOperatingExpenses.forEach((productId, perUnitExpense) -> apply(
                    new FixedExpenseUpdatedToProductEvent(productId, SysDate.now(), perUnitExpense)
            ));
            apply(new OperatingExpenseUpdatedEvent(id, expenseHeader, expenseAmount, sensitivityCharacteristic));
    }
    @EventSourcingHandler
    public void on(OperatingExpenseUpdatedEvent event) {

        this.id = event.getCommonOperatingExpenseId();
        this.expenseHeader = event.getExpenseHeader();
        this.sensitivityCharacteristic = event.getSensitivityCharacteristic();
        this.monthlyExpenseAmount=event.getExpenseAmount();
    }

/*
    @EventSourcingHandler
    public void on(PastCommonExpenseTypesRemovedEvent event) {
        //Remove past expense details from aggregate;
        YearMonth monthOfYear = event.getMonthOfYear();
        rollingExpenseForecast.entrySet().removeIf(
                entry -> entry.getKey().isBefore(monthOfYear)
        );
    }
*/

    public void removePastCommonExpenses(String aggregateId, String expenseHeader, YearMonth monthOfYear) {
        apply(new PastCommonExpenseTypesRemovedEvent(aggregateId, expenseHeader, monthOfYear));
    }
}
