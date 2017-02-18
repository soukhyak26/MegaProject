package com.affaince.subscription.business.command.event;

import com.affaince.subscription.business.vo.RecommendationReason;
import com.affaince.subscription.business.vo.RecommenderType;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 18-02-2017.
 */
public class AdditionalBudgetRecommendationRejectedEvent {
    private int businessAccountId;
    private String recommenderId;
    private LocalDate recommendationDate;
    private double additionalBudgetedAmount;
    private RecommenderType recommenderType;
    private RecommendationReason recommendationReason;

    public AdditionalBudgetRecommendationRejectedEvent(int businessAccountId, String recommenderId, LocalDate recommendationDate, double additionalBudgetedAmount, RecommenderType recommenderType, RecommendationReason recommendationReason) {
        this.businessAccountId=businessAccountId;
        this.recommenderId=recommenderId;
        this.recommendationDate=recommendationDate;
        this.additionalBudgetedAmount=additionalBudgetedAmount;
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
}
