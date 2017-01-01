package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.PurchaseCostCreditedEvent;
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
public class PurchaseCostCreditedEventListener {
    private final PurchaseCostAccountViewRepository purchaseCostAccountViewRepository;

    @Autowired
    public PurchaseCostCreditedEventListener(PurchaseCostAccountViewRepository purchaseCostAccountViewRepository) {
        this.purchaseCostAccountViewRepository = purchaseCostAccountViewRepository;
    }

    @EventHandler
    public void on(PurchaseCostCreditedEvent event) {
        PurchaseCostAccountView purchaseCostAccountView = purchaseCostAccountViewRepository.findOne(event.getYear());
        purchaseCostAccountView.credit(event.getAmountToCredit());
        purchaseCostAccountViewRepository.save(purchaseCostAccountView);
    }

}
