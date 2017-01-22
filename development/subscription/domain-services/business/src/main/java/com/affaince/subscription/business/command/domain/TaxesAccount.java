package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.CreditedEvent;
import com.affaince.subscription.business.command.event.DebitedEvent;
import com.affaince.subscription.business.command.event.ProvisionForTaxesRegisteredEvent;
import com.affaince.subscription.business.command.event.TaxesDebitedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class TaxesAccount extends AbstractAnnotatedEntity {
    private double provisionAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public TaxesAccount(LocalDate startDate,LocalDate endDate) {
        this.startDate=startDate;
        this.endDate = endDate;
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

    @EventSourcingHandler
    private void on(TaxesDebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    public void fireDebitedEvent(Integer businessAccountId, double amountToDebit) {
        apply(new TaxesDebitedEvent(businessAccountId,amountToDebit));
    }

    @EventSourcingHandler
    public void on(ProvisionForTaxesRegisteredEvent event){
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        this.provisionAmount=event.getProvisionForTaxes();
    }

}
