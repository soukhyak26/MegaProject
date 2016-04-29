package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.accounting.Account;
import com.affaince.subscription.business.command.event.CreateProvisionEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 29/4/16.
 */
@Component
public class CreateProvisionEventListener {
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public CreateProvisionEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(CreateProvisionEvent event) {
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(event.getBusinessAccountId());
        if(businessAccountView == null) {
            businessAccountView = new BusinessAccountView(event.getBusinessAccountId(), event.getProvisionDate());
        }
        businessAccountView.setProvisionalPurchaseCostAccount(new Account(event.getProvisionForPurchaseCost()));
        businessAccountView.setDateForProvision(event.getProvisionDate());
        businessAccountViewRepository.save(businessAccountView);
    }
}
