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

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class CommonOperatingExpense extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String id;
    private String expenseHeader;
    private Map<YearMonth, Double> rollingExpenseForecast;
    private SensitivityCharacteristic sensitivityCharacteristic;

    public CommonOperatingExpense() {

    }

    public CommonOperatingExpense(String id, String expenseHeader, double amount, SensitivityCharacteristic sensitivityCharacteristic) {
        apply(new CommonExpenseCreatedEvent(id, expenseHeader, amount, sensitivityCharacteristic, YearMonth.now()));
    }

    public String getId() {
        return this.id;
    }

    public String getExpenseHeader() {
        return this.expenseHeader;
    }

    public Map<YearMonth, Double> getRollingExpenseForecast() {
        return this.rollingExpenseForecast;
    }

    public SensitivityCharacteristic getSensitivityCharacteristic() {
        return this.sensitivityCharacteristic;
    }

    @EventSourcingHandler
    public void on(CommonExpenseCreatedEvent event) {
        this.id = event.getCommonOperatingExpenseId();
        this.expenseHeader = event.getExpenseHeader();
        YearMonth monthOfYear = event.getMonthOfYear();
        this.sensitivityCharacteristic = event.getSensitivityCharacteristic();
        for (int i = 0; i <= 11; i++) {
            monthOfYear = monthOfYear.plusMonths(i);
            rollingExpenseForecast.put(monthOfYear, event.getAmount());
        }
        OperatingExpensesDeterminator operatingExpensesDeterminator =
                new DefaultOperatingExpensesDeterminator();
        final Map<String, Double> perUnitOperatingExpenses = operatingExpensesDeterminator.calculateOperatingExpensesPerProduct(this);
        perUnitOperatingExpenses.forEach((productId, perUnitExpense) -> apply(
                new FixedExpenseUpdatedToProductEvent(productId, SysDate.now(), perUnitExpense)
        ));
    }


    @EventSourcingHandler
    public void on(OperatingExpenseUpdatedEvent event) {
        YearMonth monthOfYear = new YearMonth(event.getForYear(), event.getForMonth());
        if (event.getExpenseType() == ExpenseType.COMMON_EXPENSE) {
            for (int i = 0; i <= 11; i++) {
                monthOfYear = monthOfYear.plusMonths(i);
                rollingExpenseForecast.put(monthOfYear, event.getExpenseAmount());
            }
        }
        OperatingExpensesDeterminator operatingExpensesDeterminator =
                new DefaultOperatingExpensesDeterminator();
        final Map<String, Double> perUnitOperatingExpenses = operatingExpensesDeterminator.calculateOperatingExpensesPerProduct(this);
        perUnitOperatingExpenses.forEach((productId, perUnitExpense) -> apply(
                new FixedExpenseUpdatedToProductEvent(productId, SysDate.now(), perUnitExpense)
        ));
    }

    @EventSourcingHandler
    public void on(PastCommonExpenseTypesRemovedEvent event) {
        //Remove past expense details from aggregate;
        YearMonth monthOfYear = event.getMonthOfYear();
        rollingExpenseForecast.entrySet().removeIf(
                entry -> entry.getKey().isBefore(monthOfYear)
        );
    }

    public void removePastCommonExpenses(String aggregateId, String expenseHeader, YearMonth monthOfYear) {
        apply(new PastCommonExpenseTypesRemovedEvent(aggregateId, expenseHeader, monthOfYear));
    }
}
