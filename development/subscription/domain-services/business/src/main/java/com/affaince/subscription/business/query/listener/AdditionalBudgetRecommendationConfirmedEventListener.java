package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.AdditionalBudgetRecommendationConfirmedEvent;
import com.affaince.subscription.business.query.repository.*;
import com.affaince.subscription.business.query.view.*;
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
    private BenefitAccountViewRepository benefitAccountViewRepository;
    private TaxesAccountViewRepository taxesAccountViewRepository;
    private CommonExpenseAccountViewRepository commonExpenseAccountViewRepository;
    private VariableExpenseAccountViewRepository variableExpenseAccountViewRepository;
    private BudgetChangeRecommendationViewRepository budgetChangeRecommendationViewRepository;

    @Autowired
    public AdditionalBudgetRecommendationConfirmedEventListener(PurchaseCostAccountViewRepository purchaseCostAccountViewRepository,
                                                                BenefitAccountViewRepository benefitAccountViewRepository,
                                                                TaxesAccountViewRepository taxesAccountViewRepository,
                                                                CommonExpenseAccountViewRepository commonExpenseAccountViewRepository,
                                                                VariableExpenseAccountViewRepository variableExpenseAccountViewRepository,
                                                                BudgetChangeRecommendationViewRepository budgetChangeRecommendationViewRepository) {
        this.purchaseCostAccountViewRepository = purchaseCostAccountViewRepository;
        this.benefitAccountViewRepository = benefitAccountViewRepository;
        this.taxesAccountViewRepository = taxesAccountViewRepository;
        this.commonExpenseAccountViewRepository = commonExpenseAccountViewRepository;
        this.variableExpenseAccountViewRepository = variableExpenseAccountViewRepository;
        this.budgetChangeRecommendationViewRepository = budgetChangeRecommendationViewRepository;
    }

    @EventHandler
    public void on(AdditionalBudgetRecommendationConfirmedEvent event) {
        RecommendationReceiver recommendationReceiver = event.getRecommendationReceiver();
        switch (recommendationReceiver) {
            case PURCHASE_COST_ACCOUNT:
                PurchaseCostAccountView purchaseCostAccountView = purchaseCostAccountViewRepository.findOne(event.getBusinessAccountId());
                purchaseCostAccountView.setCurrentAmount(event.getRevisedProvisionalAmount());
                purchaseCostAccountViewRepository.save(purchaseCostAccountView);
                break;
            case BENEFITS_ACCOUNT:
                BenefitAccountView benefitAccountView = benefitAccountViewRepository.findOne(event.getBusinessAccountId());
                benefitAccountView.setProvisonAmount(event.getRevisedProvisionalAmount());
                benefitAccountViewRepository.save(benefitAccountView);
                break;
            case TAXES_ACCOUNT:
                TaxesAccountView taxesAccountView=taxesAccountViewRepository.findOne(event.getBusinessAccountId());
                taxesAccountView.setCurrentAmount(event.getRevisedProvisionalAmount());
                taxesAccountViewRepository.save(taxesAccountView);
                break;
            case COMMON_EXPENSES_ACCOUNT:
                CommonExpenseAccountView commonExpenseAccountView= commonExpenseAccountViewRepository.findOne(event.getBusinessAccountId());
                commonExpenseAccountView.setCurrentAmount(event.getRevisedProvisionalAmount());
                commonExpenseAccountViewRepository.save(commonExpenseAccountView);
                break;
            case SUBSCRIPTION_SPICIFIC_EXPENSES_ACCOUNT:
                VariableExpenseAccountView variableExpenseAccountView= variableExpenseAccountViewRepository.findOne(event.getBusinessAccountId());
                variableExpenseAccountView.setCurrentAmount(event.getRevisedProvisionalAmount());
                variableExpenseAccountViewRepository.save(variableExpenseAccountView);
        }

        BudgetChangeRecommendationView budgetChangeRecommendationView = budgetChangeRecommendationViewRepository.findByBusinessAccountIdAndRecommenderIdAndRecommendationDateAndRecommenderTypeAndRecommendationReasonAndRecommendationReceiver(event.getBusinessAccountId(), event.getRecommenderId(), event.getRecommendationDate(), event.getRecommenderType(), event.getRecommendationReason(), RecommendationReceiver.PURCHASE_COST_ACCOUNT).get(0);
        budgetChangeRecommendationView.setRecommendationAccepted(true);
        budgetChangeRecommendationViewRepository.save(budgetChangeRecommendationView);
    }
}
