package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.product.registration.command.event.CommonExpenseTypeAddedEvent;
import com.affaince.subscription.product.registration.command.event.CommonExpenseTypeUpdatedEvent;
import com.affaince.subscription.product.registration.command.event.PastCommonExpenseTypesRemovedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.YearMonth;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class CommonOperatingExpense extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String id;
    private Set<CommonExpenseType> monthlyCommonExpenses;

    public CommonOperatingExpense(String id) {
        this.id = id;
        monthlyCommonExpenses = new HashSet<>();

    }

    @EventSourcingHandler
    public void on(CommonExpenseTypeAddedEvent event) {
        this.id = event.getCommonOperatingExpenseId();
        YearMonth monthOfYear = YearMonth.now();
        CommonExpenseType commonExpenseType = null;
        for (int i = 0; i <= 11; i++) {
            monthOfYear = monthOfYear.plusMonths(i);
            CommonExpenseType tempExpenseType = new CommonExpenseType(event.getCommonOperatingExpenseId(), event.getExpenseHeader(), monthOfYear);
            if (monthlyCommonExpenses.contains(tempExpenseType)) {
                commonExpenseType = monthlyCommonExpenses.stream().filter(s -> s.equals(tempExpenseType)).findFirst().get();
                commonExpenseType.setAmount(event.getAmount());

            } else {
                commonExpenseType = new CommonExpenseType(event.getCommonOperatingExpenseId(), event.getExpenseHeader(), event.getAmount(), event.getPeriod(), monthOfYear);
            }
            monthlyCommonExpenses.add(commonExpenseType);
        }
    }

    public void addCommonExpenseType(String id, String expenseHeader, double amount, Period period) {
        apply(new CommonExpenseTypeAddedEvent(id, expenseHeader, amount, period, YearMonth.now()));
        apply(new PastCommonExpenseTypesRemovedEvent(id, expenseHeader, YearMonth.now()));
    }

    @EventSourcingHandler
    public void on(CommonExpenseTypeUpdatedEvent event) {
        YearMonth monthOfYear = new YearMonth(event.getForYear(), event.getForMonth());
        CommonExpenseType commonExpenseType = null;
        if (event.getExpenseType() == ExpenseType.COMMON_EXPENSE) {
            for (int i = 0; i <= 11; i++) {
                monthOfYear = monthOfYear.plusMonths(i);
                CommonExpenseType tempExpenseType = new CommonExpenseType(event.getCommonOperatingExpenseId(), event.getExpenseHeader(), monthOfYear);
                if (monthlyCommonExpenses.contains(tempExpenseType)) {
                    commonExpenseType = monthlyCommonExpenses.stream().filter(s -> s.equals(tempExpenseType)).findFirst().get();
                    commonExpenseType.setAmount(event.getExpenseAmount());
                } else {
                    commonExpenseType = new CommonExpenseType(event.getCommonOperatingExpenseId(), event.getExpenseHeader(), event.getExpenseAmount(), event.getPeriod(), monthOfYear);
                }
                monthlyCommonExpenses.add(commonExpenseType);
            }
        }
    }

    @EventSourcingHandler
    public void on(PastCommonExpenseTypesRemovedEvent event) {
        //Remove past expense details from aggregate;
        YearMonth monthOfYear = event.getMonthOfYear();
        monthlyCommonExpenses.removeIf(
                s -> s.getMonthOfYear().isBefore(monthOfYear)
        );
    }

    public void removePastCommonExpenses(String aggregateId, String expenseHeader, YearMonth monthOfYear) {
        apply(new PastCommonExpenseTypesRemovedEvent(aggregateId, expenseHeader, monthOfYear));
    }
}
