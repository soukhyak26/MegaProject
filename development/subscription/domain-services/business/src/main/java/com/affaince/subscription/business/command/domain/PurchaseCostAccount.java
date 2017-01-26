package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class PurchaseCostAccount extends AbstractAnnotatedEntity {

    private double provisionAmount;
    private LocalDate startDate;
    private LocalDate endDate;


    public PurchaseCostAccount(LocalDate startDate,LocalDate endDate){
        this.startDate=startDate;
        this.endDate=endDate;
    }
    public void debit(double amount) {
        this.provisionAmount -= amount;
    }

    public void credit(double amount) {
        this.provisionAmount += amount;
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
        apply(new PurchaseCostDebitedEvent(businessAccountId, amountToDebit));
    }

    @EventSourcingHandler
    public void on(ProvisionForPurchaseCostRegisteredEvent event){
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        this.provisionAmount=event.getProvisionForPurchaseOfGoods();
    }

    @EventSourcingHandler
    public void on(PurchaseCostDebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    public void addToPurchaseCost(Integer id,double amountTobeAdded) {
        apply(new PurchaseCostCreditedEvent(id,amountTobeAdded));
    }

    @EventSourcingHandler
    public void on(PurchaseCostCreditedEvent event){
        credit(event.getAmountToCredit());
    }
}
