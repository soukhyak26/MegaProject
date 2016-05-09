package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.SubscriptionSpecificExpenseDebitedEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 9/5/16.
 */
@Component
public class SubscriptionSpecificExpenseDebitedEventListener {
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public SubscriptionSpecificExpenseDebitedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(SubscriptionSpecificExpenseDebitedEvent event) {
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(event.getBusinessAccountId());
        businessAccountView.getProvisoinalSubscriptionSpecificExpensesAccount().debit(event.getAmountToDebit());
        businessAccountViewRepository.save(businessAccountView);
    }
}
