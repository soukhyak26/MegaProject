package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.TotalSubscriptionCostAccountDebitedEvent;
import com.affaince.subscription.payments.query.repository.SubscriptionPaymentViewRepository;
import com.affaince.subscription.payments.query.view.SubscriptionPaymentView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 28/8/16.
 */
@Component
public class TotalSubscriptionCostAccountDebitedEventListener {
    private final SubscriptionPaymentViewRepository subscriptionPaymentViewRepository;

    @Autowired
    public TotalSubscriptionCostAccountDebitedEventListener(SubscriptionPaymentViewRepository subscriptionPaymentViewRepository) {
        this.subscriptionPaymentViewRepository = subscriptionPaymentViewRepository;
    }

    @EventHandler
    public void on(TotalSubscriptionCostAccountDebitedEvent event) {
        SubscriptionPaymentView subscriptionPaymentView = subscriptionPaymentViewRepository.findById(event.getId());
        subscriptionPaymentView.getTotalSubscriptionCostAccount().debit(event.getAmountToDebit());
        subscriptionPaymentViewRepository.save(subscriptionPaymentView);
    }
}