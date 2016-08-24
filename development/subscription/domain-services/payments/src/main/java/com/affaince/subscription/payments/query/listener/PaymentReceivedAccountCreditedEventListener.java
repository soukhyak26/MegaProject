package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.PaymentReceivedAccountCreditedEvent;
import com.affaince.subscription.payments.query.repository.SubscriptionPaymentViewRepository;
import com.affaince.subscription.payments.query.view.SubscriptionPaymentView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 24/8/16.
 */
@Component
public class PaymentReceivedAccountCreditedEventListener {
    private final SubscriptionPaymentViewRepository subscriptionPaymentViewRepository;

    @Autowired
    public PaymentReceivedAccountCreditedEventListener(SubscriptionPaymentViewRepository subscriptionPaymentViewRepository) {
        this.subscriptionPaymentViewRepository = subscriptionPaymentViewRepository;
    }

    @EventHandler
    public void on(PaymentReceivedAccountCreditedEvent event) {
        SubscriptionPaymentView subscriptionPaymentView = subscriptionPaymentViewRepository.findById(event.getId());
        subscriptionPaymentView.getPaymentReceivedAccount().credit(event.getAmountToCredit());
        subscriptionPaymentViewRepository.save(subscriptionPaymentView);
    }
}
