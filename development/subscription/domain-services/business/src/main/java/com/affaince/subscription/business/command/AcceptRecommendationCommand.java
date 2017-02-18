package com.affaince.subscription.business.command;

import com.affaince.subscription.business.query.view.BudgetChangeRecommendationView;

import java.util.List;

/**
 * Created by mandar on 18-02-2017.
 */
public class AcceptRecommendationCommand {
    private Integer businessAccountId;
    private List<BudgetChangeRecommendationView> acceptedRecommendations;
    private List<BudgetChangeRecommendationView> rejectedRecommendations;

    public AcceptRecommendationCommand(Integer businessAccountId, List<BudgetChangeRecommendationView> acceptedRecommendations, List<BudgetChangeRecommendationView> rejectedRecommendations) {
        this.businessAccountId = businessAccountId;
        this.acceptedRecommendations = acceptedRecommendations;
        this.rejectedRecommendations = rejectedRecommendations;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public List<BudgetChangeRecommendationView> getAcceptedRecommendations() {
        return acceptedRecommendations;
    }

    public List<BudgetChangeRecommendationView> getRejectedRecommendations() {
        return rejectedRecommendations;
    }
}
