package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.business.event.SubscriptionSpecificExpenseDebitedEvent;
import com.affaince.subscription.business.query.repository.VariableExpenseAccountViewRepository;
import com.affaince.subscription.business.query.view.VariableExpenseAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 9/5/16.
 */
@Component
public class SubscriptionSpecificExpenseDebitedEventListener {
    private final VariableExpenseAccountViewRepository variableExpenseAccountViewRepository;

    @Autowired
    public SubscriptionSpecificExpenseDebitedEventListener(VariableExpenseAccountViewRepository variableExpenseAccountViewRepository) {
        this.variableExpenseAccountViewRepository = variableExpenseAccountViewRepository;
    }

    @EventHandler
    public void on(SubscriptionSpecificExpenseDebitedEvent event) {
        VariableExpenseAccountView variableExpenseAccountView = variableExpenseAccountViewRepository.findOne(event.getYear());
        variableExpenseAccountView.debit(event.getAmountToDebit());
        variableExpenseAccountViewRepository.save(variableExpenseAccountView);
    }
}
