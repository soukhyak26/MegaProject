package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.CreateProvisionEvent;
import com.affaince.subscription.business.command.event.PurchaseCostDebitedEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class PurchaseCostDebitedEventListener {
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public PurchaseCostDebitedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(PurchaseCostDebitedEvent event) {
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(event.getBusinessAccountId());
        businessAccountView.getProvisionalPurchaseCostAccount().debit(event.getAmountToDebit());
        businessAccountViewRepository.save(businessAccountView);
    }
}
