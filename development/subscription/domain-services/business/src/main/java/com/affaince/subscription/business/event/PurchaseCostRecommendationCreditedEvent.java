package com.affaince.subscription.business.event;

import com.affaince.subscription.business.vo.RecommendationReason;
import com.affaince.subscription.business.vo.RecommenderType;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 15-02-2017.
 */
public class PurchaseCostRecommendationCreditedEvent {
    private Integer id;
    private String productId;
    private LocalDate recommendationDate;
    private double additionalBudgetedAmount;
    private RecommenderType recommenderType;
    private RecommendationReason recommendationReason;

    public PurchaseCostRecommendationCreditedEvent(Integer id, String productId, LocalDate recommendationDate, double additionalBudgetedAmount,RecommenderType recommenderType,RecommendationReason recommendationReason) {
        this.id = id;
        this.productId = productId;
        this.recommendationDate=recommendationDate;
        this.additionalBudgetedAmount = additionalBudgetedAmount;
        this.recommenderType=recommenderType;
        this.recommendationReason=recommendationReason;
    }

    public PurchaseCostRecommendationCreditedEvent() {
    }

    public Integer getId() {
        return id;
    }

    public String getProductId() {
        return productId;
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
