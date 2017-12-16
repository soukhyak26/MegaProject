package com.affaince.subscription.business.domain;

import com.affaince.subscription.business.event.AdditionalBudgetRecommendationConfirmedEvent;
import com.affaince.subscription.business.event.AdditionalBudgetRecommendationRejectedEvent;
import com.affaince.subscription.business.event.BenefitDebitedEvent;
import com.affaince.subscription.business.event.ProvisionForBenefitsRegisteredEvent;
import com.affaince.subscription.business.query.view.BudgetChangeRecommendationView;
import com.affaince.subscription.business.vo.AdditionalBudgetRecommendation;
import com.affaince.subscription.business.vo.RecommendationReason;
import com.affaince.subscription.business.vo.RecommenderType;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class BenefitsAccount extends AbstractAnnotatedEntity {
    private List<AdditionalBudgetRecommendation> recommendations;
    private double provisionAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public BenefitsAccount(LocalDate startDate,LocalDate endDate) {
        this.provisionAmount = provisionAmount;
        this.endDate = endDate;
        this.recommendations = new ArrayList<>();
    }
    public void debit(double amount) {
        this.provisionAmount -= amount;
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, currentAmount));
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

    public void issueBenefitsAmountToBeneficiary(Integer businessAccountId, String contributorId,double amountToDebit) {
        apply(new BenefitDebitedEvent(businessAccountId, contributorId,amountToDebit, SysDate.now()));
    }

    public void addToAdditionalProvisionRecommendation(String recommenderId, LocalDate recommendationDate, RecommenderType recommenderType, double recommendedAmount, RecommendationReason recommendationReason) {
        recommendations.add(new AdditionalBudgetRecommendation(recommenderId, recommendationDate, recommenderType, recommendedAmount, recommendationReason));
    }

    public void registerProvisionForBenefits(Integer id, LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods) {
        apply(new ProvisionForBenefitsRegisteredEvent(id, startDate, endDate, provisionForPurchaseOfGoods));
    }

    @EventSourcingHandler
    public void on(BenefitDebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    @EventSourcingHandler
    public void on(ProvisionForBenefitsRegisteredEvent event){
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        this.provisionAmount=event.getProvisionForBenefits();
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
