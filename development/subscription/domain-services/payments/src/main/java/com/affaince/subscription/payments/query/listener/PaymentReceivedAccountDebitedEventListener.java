package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.PaymentReceivedAccountDebitedEvent;
import com.affaince.subscription.payments.query.repository.SubscriptionPaymentViewRepository;
import com.affaince.subscription.payments.query.view.SubscriptionPaymentView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 24/8/16.
 */
@Component
public class PaymentReceivedAccountDebitedEventListener {
    private final SubscriptionPaymentViewRepository subscriptionPaymentViewRepository;

    @Autowired
    public PaymentReceivedAccountDebitedEventListener(SubscriptionPaymentViewRepository subscriptionPaymentViewRepository) {
        this.subscriptionPaymentViewRepository = subscriptionPaymentViewRepository;
    }

    @EventHandler
    public void on(PaymentReceivedAccountDebitedEvent event) {
        SubscriptionPaymentView subscriptionPaymentView = subscriptionPaymentViewRepository.findById(event.getId());
        subscriptionPaymentView.getPaymentReceivedAccount().debit(event.getAmountToDebit());
        subscriptionPaymentViewRepository.save(subscriptionPaymentView);
    }
}
