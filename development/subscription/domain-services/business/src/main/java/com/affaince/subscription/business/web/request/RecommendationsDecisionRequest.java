package com.affaince.subscription.business.web.request;

import com.affaince.subscription.business.query.view.BudgetChangeRecommendationView;

import java.util.List;

/**
 * Created by mandar on 18-02-2017.
 */
public class RecommendationsDecisionRequest {
    private List<BudgetChangeRecommendationView> acceptedOrOverridenRecommendations;
    private List<BudgetChangeRecommendationView> rejectedRecommendations;

    public List<BudgetChangeRecommendationView> getAcceptedOrOverridenRecommendations() {
        return acceptedOrOverridenRecommendations;
    }

    public List<BudgetChangeRecommendationView> getRejectedRecommendations() {
        return rejectedRecommendations;
    }
}
