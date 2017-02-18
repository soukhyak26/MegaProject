package com.affaince.subscription.business.command.event;

import com.affaince.subscription.business.vo.RecommendationReason;
import com.affaince.subscription.business.vo.RecommenderType;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 18-02-2017.
 */
public class AdditionalBudgetRecommendationConfirmedEvent {
    private int businessAccountId;
    private String recommenderId;
    private LocalDate recommendationDate;
    private double additionalBudgetedAmount;
    private double revisedProvisionalAmount;
    private RecommenderType recommenderType;
    private RecommendationReason recommendationReason;
    public AdditionalBudgetRecommendationConfirmedEvent(int businessAccountId, String recommenderId, LocalDate recommendationDate, double additionalBudgetedAmount, double revisedProvisionalAmount,RecommenderType recommenderType, RecommendationReason recommendationReason) {
        this.businessAccountId=businessAccountId;
        this.recommenderId=recommenderId;
        this.recommendationDate=recommendationDate;
        this.additionalBudgetedAmount=additionalBudgetedAmount;
        this.revisedProvisionalAmount=revisedProvisionalAmount;
        this.recommenderType=recommenderType;
        this.recommendationReason=recommendationReason;
    }

    public int getBusinessAccountId() {
        return businessAccountId;
    }

    public String getRecommenderId() {
        return recommenderId;
    }

    public LocalDate getRecommendationDate() {
        return recommendationDate;
    }

    public double getAdditionalBudgetedAmount() {
        return additionalBudgetedAmount;
    }

    public RecommenderType getRecommenderType() {
        return recommenderType;
    }

    public RecommendationReason getRecommendationReason() {
        return recommendationReason;
    }

    public double getRevisedProvisionalAmount() {
        return revisedProvisionalAmount;
    }
}
