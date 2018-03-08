package com.test.verifier.domains.business.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 18-02-2017.
 */
public class AdditionalBudgetRecommendation implements BudgetChangeRecommendation {
    private String recommenderId;
    private LocalDate recommendationDate;
    private RecommenderType recommenderType;
    private double recommendationAmount;
    private LocalDate periodStartDate;
    private LocalDate periodEndDate;
    private RecommendationReason recommendationReason;
    private RecommendationReceiver recommendationReceiver;

    public AdditionalBudgetRecommendation(String recommenderId, LocalDate recommendationDate, RecommenderType recommenderType, double recommendationAmount,LocalDate periodStartDate,LocalDate periodEndDate, RecommendationReason recommendationReason,RecommendationReceiver recommendationReceiver) {
        this.recommenderId = recommenderId;
        this.recommendationDate = recommendationDate;
        this.recommenderType = recommenderType;
        this.recommendationAmount = recommendationAmount;
        this.periodStartDate=periodStartDate;
        this.periodEndDate=periodEndDate;
        this.recommendationReason = recommendationReason;
        this.recommendationReceiver=recommendationReceiver;
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

    public LocalDate getPeriodStartDate() {
        return periodStartDate;
    }

    public LocalDate getPeriodEndDate() {
        return periodEndDate;
    }

    public RecommendationReason getRecommendationReason() {
        return recommendationReason;
    }

    public RecommendationReceiver getRecommendationReceiver() {
        return recommendationReceiver;
    }
}
