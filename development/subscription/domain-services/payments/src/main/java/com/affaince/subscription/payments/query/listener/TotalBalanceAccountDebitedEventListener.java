package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.TotalBalanceAccountDebitedEvent;
import com.affaince.subscription.payments.query.repository.SubscriptionPaymentViewRepository;
import com.affaince.subscription.payments.query.view.SubscriptionPaymentView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 24/8/16.
 */
@Component
public class TotalBalanceAccountDebitedEventListener {
    private final SubscriptionPaymentViewRepository subscriptionPaymentViewRepository;

    @Autowired
    public TotalBalanceAccountDebitedEventListener(SubscriptionPaymentViewRepository subscriptionPaymentViewRepository) {
        this.subscriptionPaymentViewRepository = subscriptionPaymentViewRepository;
    }

    @EventHandler
    public void on(TotalBalanceAccountDebitedEvent event) {
        SubscriptionPaymentView subscriptionPaymentView = subscriptionPaymentViewRepository.findBySubscriptionId(event.getId());
        subscriptionPaymentView.getTotalBalanceAccount().debit(event.getAmountToDebit());
        subscriptionPaymentViewRepository.save(subscriptionPaymentView);
    }
}
