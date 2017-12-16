package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.business.event.PurchaseCostRecommendationCreditedEvent;
import com.affaince.subscription.business.query.repository.BudgetChangeRecommendationViewRepository;
import com.affaince.subscription.business.query.view.BudgetChangeRecommendationView;
import com.affaince.subscription.business.vo.RecommendationReceiver;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 18-02-2017.
 */
@Component
public class PurchaseCostRecommendationCreditedEventListener {

    private BudgetChangeRecommendationViewRepository budgetChangeRecommendationViewRepository;

    @Autowired
    public PurchaseCostRecommendationCreditedEventListener(BudgetChangeRecommendationViewRepository budgetChangeRecommendationViewRepository) {
        this.budgetChangeRecommendationViewRepository = budgetChangeRecommendationViewRepository;
    }

    @EventHandler
    public void on(PurchaseCostRecommendationCreditedEvent event){
        BudgetChangeRecommendationView budgetChangeRecommendationView = new BudgetChangeRecommendationView(event.getId(),event.getProductId(),event.getRecommendationDate(),event.getAdditionalBudgetedAmount(),event.getRecommenderType(),event.getRecommendationReason(), RecommendationReceiver.PURCHASE_COST_ACCOUNT);
        budgetChangeRecommendationViewRepository.save(budgetChangeRecommendationView);
    }
}
