package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.PaymentInitiatedEvent;
import com.affaince.subscription.payments.query.repository.SubscriptionPaymentViewRepository;
import com.affaince.subscription.payments.query.view.SubscriptionPaymentView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 24/8/16.
 */
@Component
public class PaymentInitiatedEventListener {
    private final SubscriptionPaymentViewRepository subscriptionPaymentViewRepository;

    @Autowired
    public PaymentInitiatedEventListener(SubscriptionPaymentViewRepository subscriptionPaymentViewRepository) {
        this.subscriptionPaymentViewRepository = subscriptionPaymentViewRepository;
    }

    @EventHandler
    public void on(PaymentInitiatedEvent event) {
        SubscriptionPaymentView subscriptionPaymentView = subscriptionPaymentViewRepository.findById(event.getSubscriptionId());
        if(subscriptionPaymentView == null) {
            subscriptionPaymentView = new SubscriptionPaymentView(event.getSubscriptionId(), event.getTotalBalance());
        }
        subscriptionPaymentViewRepository.save(subscriptionPaymentView);
    }
}
