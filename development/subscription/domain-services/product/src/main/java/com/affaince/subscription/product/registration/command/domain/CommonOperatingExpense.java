package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.product.registration.command.event.CommonOperatingExpenseAddedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class CommonOperatingExpense extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String id;
    private String expenseHeader;
    private double amount;
    private Period period;

    public CommonOperatingExpense(String id, String expenseHeader, double amount, Period period) {
        apply(new CommonOperatingExpenseAddedEvent(id, expenseHeader, amount, period));
    }

    public CommonOperatingExpense() {
    }

    @EventSourcingHandler
    public void on(CommonOperatingExpenseAddedEvent event) {
        this.id = event.getId();
        this.expenseHeader = event.getExpenseHeader();
        this.amount = event.getAmount();
        this.period = event.getPeriod();
    }
}
