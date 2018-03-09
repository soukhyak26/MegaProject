package com.verifier.domains.business.view;

import com.verifier.domains.business.vo.RecommendationReason;
import com.verifier.domains.business.vo.RecommendationReceiver;
import com.verifier.domains.business.vo.RecommenderType;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by mandar on 18-02-2017.
 */
@Document(collection = "BudgetChangeRecommendationView")
public class BudgetChangeRecommendationView {
    @Id
    private String recommendationId;
    private String businessAccountId;
    private String recommenderId;
    private LocalDate recommendationDate;
    private double additionalBudgetedAmount;
    private RecommenderType recommenderType;
    private RecommendationReason recommendationReason;
    private RecommendationReceiver recommendationReceiver;
    private boolean recommendationAccepted;

    public BudgetChangeRecommendationView(String recommendationId, String businessAccountId, String recommenderId, LocalDate recommendationDate, double additionalBudgetedAmount, RecommenderType recommenderType, RecommendationReason recommendationReason, RecommendationReceiver recommendationReceiver, boolean recommendationAccepted) {
        this.recommendationId = recommendationId;
        this.businessAccountId = businessAccountId;
        this.recommenderId = recommenderId;
        this.recommendationDate = recommendationDate;
        this.additionalBudgetedAmount = additionalBudgetedAmount;
        this.recommenderType = recommenderType;
        this.recommendationReason = recommendationReason;
        this.recommendationReceiver = recommendationReceiver;
        this.recommendationAccepted = recommendationAccepted;
    }

    public String getBusinessAccountId() {
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

    public RecommendationReceiver getRecommendationReceiver() {
        return recommendationReceiver;
    }

    public String getRecommendationId() {
        return recommendationId;
    }

    public boolean isRecommendationAccepted() {
        return recommendationAccepted;
    }

    public void setRecommendationAccepted(boolean recommendationAccepted) {
        this.recommendationAccepted = recommendationAccepted;
    }
}
