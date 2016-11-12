package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.accounting.*;
import com.affaince.subscription.business.command.event.ProvisionCreatedEvent;
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
    public void on(ProvisionCreatedEvent event) {
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(event.getBusinessAccountId());
        if(businessAccountView == null) {
            businessAccountView = new BusinessAccountView(event.getBusinessAccountId(), event.getProvisionDate());
        }
        businessAccountView.setProvisionalPurchaseCostAccount(new PurchaseCostAccount(event.getProvisionForPurchaseCost(), event.getProvisionDate()));
        businessAccountView.setProvisionalLossesAccount(new LossesAccount(event.getProvisionForLosses(), event.getProvisionDate()));
        businessAccountView.setProvisionalBenefitsAccount(new BenefitsAccount(event.getProvisionForBenefits(), event.getProvisionDate()));
        businessAccountView.setProvisionalTaxesAccount(new TaxesAccount(event.getProvisionForTaxes(), event.getProvisionDate()));
        businessAccountView.setProvisionalOthersAccount(new OthersAccount(event.getProvisionForOthers(), event.getProvisionDate()));
        businessAccountView.setProvisionalCommonExpensesAccount(new CommonExpensesAccount(event.getProvisionForCommonExpenses(), event.getProvisionDate()));
        businessAccountView.setProvisoinalSubscriptionSpecificExpensesAccount(new SubscriptionSpecificExpensesAccount(event.getProvisionForSubscriptionSpecificExpenses(), event.getProvisionDate()));
        businessAccountView.setDateForProvision(event.getProvisionDate());
        businessAccountViewRepository.save(businessAccountView);
    }
}
