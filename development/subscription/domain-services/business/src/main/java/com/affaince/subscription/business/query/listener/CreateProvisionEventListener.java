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
        businessAccountView.setProvisionalLossesAccount(new Account(event.getProvisionForLosses()));
        businessAccountView.setProvisionalBenefitsAccount(new Account(event.getProvisionForBenefits()));
        businessAccountView.setProvisionalTaxesAccount(new Account(event.getProvisionForTaxes()));
        businessAccountView.setProvisionalOthersAccount(new Account(event.getProvisionForOthers()));
        businessAccountView.setProvisionalCommonExpensesAccount(new Account(event.getProvisionForCommonExpenses()));
        businessAccountView.setProvisionalNodalAccountAccount(new Account(event.getProvisionForNodalAccount()));
        businessAccountView.setProvisionalRevenueAccount(new Account(event.getProvisionForRevenue()));
        businessAccountView.setProvisionalBookingAmountAccount(new Account(event.getProvisionForBookinAmount()));
        businessAccountView.setProvisoinalSubscriptionSpecificExpensesAccount(new Account(event.getProvisionForSubscriptionSpecificExpenses()));
        businessAccountView.setDateForProvision(event.getProvisionDate());
        businessAccountViewRepository.save(businessAccountView);
    }
}
