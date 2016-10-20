package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.TotalReceivableCostAccountCreditedEvent;
import com.affaince.subscription.payments.query.repository.SubscriptionPaymentViewRepository;
import com.affaince.subscription.payments.query.view.SubscriptionPaymentView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 28/8/16.
 */
@Component
public class TotalReceivableCostAccountCreditedEventListener {
    private final SubscriptionPaymentViewRepository subscriptionPaymentViewRepository;

    @Autowired
    public TotalReceivableCostAccountCreditedEventListener(SubscriptionPaymentViewRepository subscriptionPaymentViewRepository) {
        this.subscriptionPaymentViewRepository = subscriptionPaymentViewRepository;
    }

    @EventHandler
    public void on(TotalReceivableCostAccountCreditedEvent event) {
        SubscriptionPaymentView subscriptionPaymentView = subscriptionPaymentViewRepository.findBySubscriptionId(event.getId());
        subscriptionPaymentView.getTotalReceivableCostAccount().credit(event.getAmountToCredit());
        subscriptionPaymentViewRepository.save(subscriptionPaymentView);
    }
}
