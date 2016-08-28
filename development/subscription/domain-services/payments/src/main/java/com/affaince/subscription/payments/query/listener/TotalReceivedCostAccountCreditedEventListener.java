package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.TotalReceivedCostAccountCreditedEvent;
import com.affaince.subscription.payments.query.repository.SubscriptionPaymentViewRepository;
import com.affaince.subscription.payments.query.view.SubscriptionPaymentView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 28/8/16.
 */
@Component
public class TotalReceivedCostAccountCreditedEventListener {
    private final SubscriptionPaymentViewRepository subscriptionPaymentViewRepository;

    @Autowired
    public TotalReceivedCostAccountCreditedEventListener(SubscriptionPaymentViewRepository subscriptionPaymentViewRepository) {
        this.subscriptionPaymentViewRepository = subscriptionPaymentViewRepository;
    }

    @EventHandler
    public void on(TotalReceivedCostAccountCreditedEvent event) {
        SubscriptionPaymentView subscriptionPaymentView = subscriptionPaymentViewRepository.findById(event.getId());
        subscriptionPaymentView.getTotalReceivedCostAccount().credit(event.getAmountToCredit());
        subscriptionPaymentViewRepository.save(subscriptionPaymentView);
    }
}
