package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.PurchaseCostCreditedEvent;
import com.affaince.subscription.business.command.event.PurchaseCostDebitedEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.repository.PurchaseCostAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import com.affaince.subscription.business.query.view.PurchaseCostAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class PurchaseCostDebitedEventListener {
    private final PurchaseCostAccountViewRepository purchaseCostAccountViewRepository;

    @Autowired
    public PurchaseCostDebitedEventListener(PurchaseCostAccountViewRepository purchaseCostAccountViewRepository) {
        this.purchaseCostAccountViewRepository = purchaseCostAccountViewRepository;
    }

    @EventHandler
    public void on(PurchaseCostDebitedEvent event) {
        PurchaseCostAccountView purchaseCostAccountView = purchaseCostAccountViewRepository.findOne(event.getId());
        purchaseCostAccountView.debit(event.getExcessBudgetedAmount());
        purchaseCostAccountViewRepository.save(purchaseCostAccountView);
    }
}
