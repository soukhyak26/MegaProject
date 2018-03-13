package com.affiance.business.vo;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 2/10/2018.
 */
public interface BudgetChangeRecommendation {
    public String getRecommenderId();

    public LocalDate getRecommendationDate();

    public RecommenderType getRecommenderType();

    public double getRecommendationAmount();
    public RecommendationReason getRecommendationReason();

}
