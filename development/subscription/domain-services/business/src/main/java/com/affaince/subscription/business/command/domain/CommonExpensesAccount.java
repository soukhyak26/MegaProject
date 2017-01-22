package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.*;
import com.affaince.subscription.business.process.operatingexpenses.DefaultOperatingExpensesDeterminator;
import com.affaince.subscription.business.process.operatingexpenses.OperatingExpensesDeterminator;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by anayonkar on 9/5/16.
 */
public class CommonExpensesAccount extends AbstractAnnotatedEntity {
    private double provisionAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public CommonExpensesAccount(LocalDate startDate,LocalDate endDate) {
        this.endDate = endDate;
        this.startDate=startDate;
    }
    public void debit(double amount) {
        this.provisionAmount -= amount;
    }

    public double getProvisionAmount() {
        return provisionAmount;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


    public void fireDebitedEvent(Integer businessAccountId, double amountToDebit) {
        apply(new CommonExpenseDebitedEvent(businessAccountId, amountToDebit));
    }


    @EventSourcingHandler
    public void on(CommonExpenseDebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    @EventSourcingHandler
    public void on(ProvisionForCommonExpensesRegisteredEvent event){
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        this.provisionAmount=event.getProvisionForCommonExpenses();
    }
}
