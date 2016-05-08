package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.OperatingExpenseDebitedEvent;
import com.affaince.subscription.business.command.event.PurchaseCostCreditedEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class OperatingExpenseDebitedEventListener {
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public OperatingExpenseDebitedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(OperatingExpenseDebitedEvent event) {
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(event.getBusinessAccountId());
        switch (event.getExpenseType()) {
            case COMMON_EXPENSE:
                businessAccountView.getProvisionalCommonExpensesAccount().debit(event.getAmountToDebit());
                break;
            case SUBSCRIPTION_SPECIFIC_EXPENSE:
                businessAccountView.getProvisoinalSubscriptionSpecificExpensesAccount().debit(event.getAmountToDebit());
                break;
            default:
                //TODO : error handling
        }
        businessAccountViewRepository.save(businessAccountView);
    }
}
