package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.OperatingExpenseCreditedEvent;
import com.affaince.subscription.business.command.event.OperatingExpenseDebitedEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class OperatingExpenseCreditedEventListener {
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public OperatingExpenseCreditedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(OperatingExpenseCreditedEvent event) {
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(event.getBusinessAccountId());
        switch (event.getExpenseType()) {
            case COMMON_EXPENSE:
                businessAccountView.getCommonExpensesAccount().credit(event.getAmountToCredit());
                break;
            case SUBSCRIPTION_SPECIFIC_EXPENSE:
                businessAccountView.getSubscriptionSpecificExpensesAccount().credit(event.getAmountToCredit());
                break;
            default:
                //TODO : error handling
        }
        businessAccountViewRepository.save(businessAccountView);
    }
}
