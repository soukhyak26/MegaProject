package com.affaince.subscription.business.domain;

import com.affaince.subscription.business.event.OthersAccountDebitedEvent;
import com.affaince.subscription.business.event.ProvisionForOtherCostRegisteredEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class OthersAccount extends AbstractAnnotatedEntity {
    private double provisionAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public OthersAccount(LocalDate startDate,LocalDate endDate) {
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
    private void on(OthersAccountDebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    public void fireDebitedEvent(Integer businessAccountId, double amountToDebit) {
        apply(new OthersAccountDebitedEvent(businessAccountId,amountToDebit));
    }

    public void registerProvisionForOtherCost(Integer id, LocalDate startDate, LocalDate endDate, double provisionForOtherCost) {
        apply(new ProvisionForOtherCostRegisteredEvent(id, startDate, endDate, provisionForOtherCost));
    }

    @EventSourcingHandler
    public void on(ProvisionForOtherCostRegisteredEvent event){
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        this.provisionAmount=event.getProvisionForOtherCost();
    }

}
