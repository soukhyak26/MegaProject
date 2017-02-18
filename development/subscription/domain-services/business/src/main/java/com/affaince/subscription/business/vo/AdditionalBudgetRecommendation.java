package com.affaince.subscription.business.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 18-02-2017.
 */
public class AdditionalBudgetRecommendation {
    private String recommenderId;
    private LocalDate recommendationDate;
    private RecommenderType recommenderType;
    private double recommendationAmount;
    private RecommendationReason recommendationReason;

    public AdditionalBudgetRecommendation(String recommenderId, LocalDate recommendationDate, RecommenderType recommenderType, double recommendationAmount, RecommendationReason recommendationReason) {
        this.recommenderId = recommenderId;
        this.recommendationDate = recommendationDate;
        this.recommenderType = recommenderType;
        this.recommendationAmount = recommendationAmount;
        this.recommendationReason = recommendationReason;
    }

    public String getRecommenderId() {
        return recommenderId;
    }

    public LocalDate getRecommendationDate() {
        return recommendationDate;
    }

    public RecommenderType getRecommenderType() {
        return recommenderType;
    }

    public double getRecommendationAmount() {
        return recommendationAmount;
    }

    public RecommendationReason getRecommendationReason() {
        return recommendationReason;
    }
}
