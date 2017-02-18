package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.AdditionalBudgetRecommendationConfirmedEvent;
import com.affaince.subscription.business.query.repository.BudgetChangeRecommendationViewRepository;
import com.affaince.subscription.business.query.repository.PurchaseCostAccountViewRepository;
import com.affaince.subscription.business.query.view.BudgetChangeRecommendationView;
import com.affaince.subscription.business.query.view.PurchaseCostAccountView;
import com.affaince.subscription.business.vo.RecommendationReceiver;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 18-02-2017.
 */
@Component
public class AdditionalBudgetRecommendationConfirmedEventListener {
    private PurchaseCostAccountViewRepository purchaseCostAccountViewRepository;
    private BudgetChangeRecommendationViewRepository budgetChangeRecommendationViewRepository;
    @Autowired
    public AdditionalBudgetRecommendationConfirmedEventListener(PurchaseCostAccountViewRepository purchaseCostAccountViewRepository,BudgetChangeRecommendationViewRepository budgetChangeRecommendationViewRepository) {
        this.purchaseCostAccountViewRepository=purchaseCostAccountViewRepository;
        this.budgetChangeRecommendationViewRepository = budgetChangeRecommendationViewRepository;
    }
    @EventHandler
    public void on(AdditionalBudgetRecommendationConfirmedEvent event){
        PurchaseCostAccountView purchaseCostAccountView=purchaseCostAccountViewRepository.findOne(event.getBusinessAccountId());
        purchaseCostAccountView.setCurrentAmount(event.getRevisedProvisionalAmount());
        purchaseCostAccountViewRepository.save(purchaseCostAccountView);

        BudgetChangeRecommendationView budgetChangeRecommendationView=budgetChangeRecommendationViewRepository.findByBusinessAccountIdAndRecommenderIdAndRecommendationDateAndRecommenderTypeAndRecommendationReasonAndRecommendationReceiver(event.getBusinessAccountId(),event.getRecommenderId(),event.getRecommendationDate(),event.getRecommenderType(),event.getRecommendationReason(), RecommendationReceiver.PURCHASE_COST_ACCOUNT).get(0);
        budgetChangeRecommendationView.setRecommendationAccepted(true);
        budgetChangeRecommendationViewRepository.save(budgetChangeRecommendationView);
    }
}
