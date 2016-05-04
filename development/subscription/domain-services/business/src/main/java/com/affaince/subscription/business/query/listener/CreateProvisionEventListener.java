package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.accounting.Account;
import com.affaince.subscription.business.command.event.CreateProvisionEvent;
import com.affaince.subscription.business.provision.ProvisionIndex;
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
        /*for(ProvisionIndex provisionIndex : ProvisionIndex.values()) {
            switch (provisionIndex) {
                case MAX_CAPACITY:
                    break;
                default:
                    businessAccountView.getProvisionalAccountList().add(provisionIndex.getIndex(), new Account(event.getProvisionList().get(provisionIndex.getIndex())));
            }
        }*/
        for(int i = 0 ; i < ProvisionIndex.MAX_CAPACITY.getIndex() ; i++) {
            businessAccountView.getProvisionalAccountList().add(i, new Account(event.getProvisionList().get(i)));
        }
        businessAccountView.setDateForProvision(event.getProvisionDate());
        businessAccountViewRepository.save(businessAccountView);
    }
}
