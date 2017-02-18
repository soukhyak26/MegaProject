package com.affaince.subscription.business.query.view;

import com.affaince.subscription.business.vo.RecommendationReason;
import com.affaince.subscription.business.vo.RecommendationReceiver;
import com.affaince.subscription.business.vo.RecommenderType;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 18-02-2017.
 */
@Document(collection = "BudgetChangeRecommendationView")
public class BudgetChangeRecommendationView {
    @Id
    private long recommendationId;
    private int businessAccountId;
    private String recommenderId;
    private LocalDate recommendationDate;
    private double additionalBudgetedAmount;
    private RecommenderType recommenderType;
    private RecommendationReason recommendationReason;
    private RecommendationReceiver recommendationReceiver;
    private boolean recommendationAccepted;

    public BudgetChangeRecommendationView(int businessAccountId, String recommenderId, LocalDate recommendationDate, double additionalBudgetedAmount, RecommenderType recommenderType, RecommendationReason recommendationReason, RecommendationReceiver recommendationReceiver) {
        this.recommendationId=recommendationDate.toDateTimeAtCurrentTime().getMillis();
        this.businessAccountId = businessAccountId;
        this.recommenderId = recommenderId;
        this.recommendationDate = recommendationDate;
        this.additionalBudgetedAmount = additionalBudgetedAmount;
        this.recommenderType = recommenderType;
        this.recommendationReason = recommendationReason;
        this.recommendationReceiver = recommendationReceiver;
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

    public RecommendationReceiver getRecommendationReceiver() {
        return recommendationReceiver;
    }

    public long getRecommendationId() {
        return recommendationId;
    }

    public boolean isRecommendationAccepted() {
        return recommendationAccepted;
    }

    public void setRecommendationAccepted(boolean recommendationAccepted) {
        this.recommendationAccepted = recommendationAccepted;
    }
}
