package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.*;
import com.affaince.subscription.business.process.operatingexpenses.DefaultOperatingExpensesDeterminator;
import com.affaince.subscription.business.process.operatingexpenses.OperatingExpensesDeterminator;
import com.affaince.subscription.business.query.view.BudgetChangeRecommendationView;
import com.affaince.subscription.business.vo.AdditionalBudgetRecommendation;
import com.affaince.subscription.business.vo.RecommendationReason;
import com.affaince.subscription.business.vo.RecommenderType;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonExpensesAccount extends AbstractAnnotatedEntity {
    private List<AdditionalBudgetRecommendation> recommendations;
    private double provisionAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public CommonExpensesAccount(LocalDate startDate,LocalDate endDate) {
        this.endDate = endDate;
        this.startDate=startDate;
        this.recommendations = new ArrayList<>();
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


    public void debitFromCommonExpenses(Integer businessAccountId, double amountToDebit) {
        apply(new CommonExpenseDebitedEvent(businessAccountId, amountToDebit));
    }

    public void addToAdditionalProvisionRecommendation(String recommenderId, LocalDate recommendationDate, RecommenderType recommenderType, double recommendedAmount, RecommendationReason recommendationReason) {
        recommendations.add(new AdditionalBudgetRecommendation(recommenderId, recommendationDate, recommenderType, recommendedAmount, recommendationReason));
    }

    @EventSourcingHandler
    public void on(CommonExpenseDebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    public void registerProvisionForCommonExpenses(Integer id, LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods,DefaultOperatingExpensesDeterminator defaultOperatingExpensesDeterminator) {
        apply(new ProvisionForCommonExpensesRegisteredEvent(id, startDate, endDate, provisionForPurchaseOfGoods));
        final Map<String, Double> perUnitOperatingExpenses = defaultOperatingExpensesDeterminator.calculateOperatingExpensesPerProduct(provisionForPurchaseOfGoods);
        perUnitOperatingExpenses.forEach((productId, perUnitExpense) -> apply(
                new FixedExpenseUpdatedToProductEvent(productId, startDate, perUnitExpense)
        ));
    }

    @EventSourcingHandler
    public void on(ProvisionForCommonExpensesRegisteredEvent event){
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        this.provisionAmount=event.getProvisionForCommonExpenses();
    }

    public void acceptOrOverrideRecommendation(BudgetChangeRecommendationView acceptedRecommendation) {
        double newPurchaseProvisionAmount=this.provisionAmount+acceptedRecommendation.getAdditionalBudgetedAmount();
        apply(new AdditionalBudgetRecommendationConfirmedEvent(acceptedRecommendation.getBusinessAccountId(), acceptedRecommendation.getRecommenderId(), acceptedRecommendation.getRecommendationDate(), acceptedRecommendation.getAdditionalBudgetedAmount(), newPurchaseProvisionAmount,acceptedRecommendation.getRecommenderType(), acceptedRecommendation.getRecommendationReason(),acceptedRecommendation.getRecommendationReceiver()));
    }

    public void rejectRecommendation(BudgetChangeRecommendationView rejectedRecommendation) {
        apply(new AdditionalBudgetRecommendationRejectedEvent(rejectedRecommendation.getBusinessAccountId(), rejectedRecommendation.getRecommenderId(), rejectedRecommendation.getRecommendationDate(), rejectedRecommendation.getAdditionalBudgetedAmount(), rejectedRecommendation.getRecommenderType(), rejectedRecommendation.getRecommendationReason(),rejectedRecommendation.getRecommendationReceiver()));
    }

    @EventSourcingHandler
    public void on(AdditionalBudgetRecommendationConfirmedEvent event) {
        for (AdditionalBudgetRecommendation recommendation : recommendations) {
            if (event.getAdditionalBudgetedAmount() == recommendation.getRecommendationAmount()
                    && event.getRecommendationDate() == recommendation.getRecommendationDate()
                    && event.getRecommendationReason() == recommendation.getRecommendationReason()
                    && event.getRecommenderId() == recommendation.getRecommenderId()) {
                this.provisionAmount =event.getRevisedProvisionalAmount();
                recommendations.remove(recommendation);
                break;
            }
        }

    }

    @EventSourcingHandler
    public void on(AdditionalBudgetRecommendationRejectedEvent event) {
        for (AdditionalBudgetRecommendation recommendation : recommendations) {
            if (event.getAdditionalBudgetedAmount() == recommendation.getRecommendationAmount()
                    && event.getRecommendationDate() == recommendation.getRecommendationDate()
                    && event.getRecommendationReason() == recommendation.getRecommendationReason()
                    && event.getRecommenderId() == recommendation.getRecommenderId()) {
                recommendations.remove(recommendation);
                break;
            }
        }
    }

}
