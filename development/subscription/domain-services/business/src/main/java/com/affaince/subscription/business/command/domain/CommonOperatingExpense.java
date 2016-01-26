package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.CommonExpenseCreatedEvent;
import com.affaince.subscription.business.command.event.CommonExpenseTypeUpdatedEvent;
import com.affaince.subscription.business.command.event.PastCommonExpenseTypesRemovedEvent;
import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;
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
    //private double amount;
    private Period period;
    //private YearMonth monthOfYear;

    //private Set<CommonExpenseType> monthlyCommonExpenses;


    public CommonOperatingExpense(String id, String expenseHeader, double amount) {
        apply(new CommonExpenseCreatedEvent(id, expenseHeader, amount, YearMonth.now()));
    }

    @EventSourcingHandler
    public void on(CommonExpenseCreatedEvent event) {
        this.id = event.getCommonOperatingExpenseId();
        this.expenseHeader = expenseHeader;
        this.period = period;
        YearMonth monthOfYear = event.getMonthOfYear();
        for (int i = 0; i <= 11; i++) {
            monthOfYear = monthOfYear.plusMonths(i);
            rollingExpenseForecast.put(monthOfYear, event.getAmount());
        }
    }


    @EventSourcingHandler
    public void on(CommonExpenseTypeUpdatedEvent event) {
        YearMonth monthOfYear = new YearMonth(event.getForYear(), event.getForMonth());
        if (event.getExpenseType() == ExpenseType.COMMON_EXPENSE) {
            for (int i = 0; i <= 11; i++) {
                monthOfYear = monthOfYear.plusMonths(i);
                rollingExpenseForecast.put(monthOfYear, event.getExpenseAmount());
            }
        }
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
