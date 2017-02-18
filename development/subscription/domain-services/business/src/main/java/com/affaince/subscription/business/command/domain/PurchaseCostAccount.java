package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class PurchaseCostAccount extends AbstractAnnotatedEntity {

    private double additionalRecommendedProvisionAmount;
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


    public double getProvisionAmount() {
        return provisionAmount;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getAdditionalRecommendedProvisionAmount() {
        return additionalRecommendedProvisionAmount;
    }

    public void addToAdditionalProvisionRecommendation(double amount){
        additionalRecommendedProvisionAmount +=amount;
    }
    public void fireDebitedEvent(Integer businessAccountId, double amountToDebit) {
        apply(new PurchaseCostDebitedEvent(businessAccountId, amountToDebit));
    }


    @EventSourcingHandler
    public void on(ProvisionForPurchaseCostRegisteredEvent event){
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        //Manual provision should be directly registered in provisionAmount
        this.provisionAmount=event.getProvisionForPurchaseOfGoods();
    }

    @EventSourcingHandler
    public void on(PurchaseCostDebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    public void addToPurchaseCostDueToSubscriptionCountChange(Integer id,String productId,long totalSubscriptionsRegistered,double productPurchasePricePerUnit) {
        double additionalBudgetedAmount=totalSubscriptionsRegistered*productPurchasePricePerUnit;
        apply(new PurchaseCostCreditedEvent(id,productId,totalSubscriptionsRegistered,productPurchasePricePerUnit,additionalBudgetedAmount));
    }

    public void addToPurchaseCostDueToPurchasePriceChange(Integer id,String productId,double additionalBudgetProvision) {
        apply(new PurchaseCostRevisedEvent(id,productId,additionalBudgetProvision));
    }



    public void addToRecommendationForAdditionToPurchaseCost(Integer id,String productId,long totalSubscriptionsRegistered,double productPurchasePricePerUnit) {
        double additionalBudgetedAmount=totalSubscriptionsRegistered*productPurchasePricePerUnit;
        apply(new PurchaseCostRecommendationCreditedEvent(id,productId,totalSubscriptionsRegistered,productPurchasePricePerUnit,additionalBudgetedAmount));
    }

    @EventSourcingHandler
    public void on(PurchaseCostRecommendationCreditedEvent event){
        this.addToAdditionalProvisionRecommendation((event.getAdditionalBudgetedAmount()));
    }

    @EventSourcingHandler
    public void on(PurchaseCostCreditedEvent event){
        this.provisionAmount +=event.getAdditionalBudgetedAmount();
    }
}
