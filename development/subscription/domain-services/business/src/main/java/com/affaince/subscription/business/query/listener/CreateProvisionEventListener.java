package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.accounting.Account;
import com.affaince.subscription.business.accounting.AccountType;
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
        businessAccountView.setProvisionalPurchaseCostAccount(new Account(AccountType.PURCHASE_COST, event.getProvisionForPurchaseCost()));
        businessAccountView.setProvisionalLossesAccount(new Account(AccountType.LOSSES, event.getProvisionForLosses()));
        businessAccountView.setProvisionalBenefitsAccount(new Account(AccountType.BENEFITS, event.getProvisionForBenefits()));
        businessAccountView.setProvisionalTaxesAccount(new Account(AccountType.TAXES, event.getProvisionForTaxes()));
        businessAccountView.setProvisionalOthersAccount(new Account(AccountType.OTHERS, event.getProvisionForOthers()));
        businessAccountView.setProvisionalCommonExpensesAccount(new Account(AccountType.COMMON_EXPENSES, event.getProvisionForCommonExpenses()));
        businessAccountView.setProvisoinalSubscriptionSpecificExpensesAccount(new Account(AccountType.SUBSCRIPTION_SPECIFIC_EXPENSES, event.getProvisionForSubscriptionSpecificExpenses()));
        businessAccountView.setDateForProvision(event.getProvisionDate());
        businessAccountViewRepository.save(businessAccountView);
    }
}
