package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.business.event.PurchaseCostCreditedEvent;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.repository.PurchaseCostAccountViewRepository;
import com.affaince.subscription.business.query.view.ProductView;
import com.affaince.subscription.business.query.view.PurchaseCostAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class PurchaseCostCreditedEventListener {
    private final PurchaseCostAccountViewRepository purchaseCostAccountViewRepository;
    private final ProductViewRepository productViewRepository;

    @Autowired
    public PurchaseCostCreditedEventListener(PurchaseCostAccountViewRepository purchaseCostAccountViewRepository,ProductViewRepository productViewRepository) {
        this.purchaseCostAccountViewRepository = purchaseCostAccountViewRepository;
        this.productViewRepository=productViewRepository;
    }

    @EventHandler
    public void on(PurchaseCostCreditedEvent event) {
        ProductView productView =productViewRepository.findByProductId(event.getProductId());
        double productPurchaseBudgetedAmount=event.getAdditionalBudgetedAmount();
        productView.setTotalAnticipatedSubscriptions(event.getGetTotalProductSubscriptionCount());  //TODO:Are these total subscriptions per year???
        productView.setProductPurchaseBudgetedAmount(productPurchaseBudgetedAmount);
        productViewRepository.save(productView);


        PurchaseCostAccountView purchaseCostAccountView = purchaseCostAccountViewRepository.findOne(event.getId());
        purchaseCostAccountView.credit(event.getAdditionalBudgetedAmount());
        purchaseCostAccountViewRepository.save(purchaseCostAccountView);
    }

}
