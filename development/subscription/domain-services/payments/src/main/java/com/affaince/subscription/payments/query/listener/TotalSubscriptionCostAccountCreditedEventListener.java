package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.TotalSubscriptionCostAccountCreditedEvent;
import com.affaince.subscription.payments.query.repository.SubscriptionPaymentViewRepository;
import com.affaince.subscription.payments.query.view.SubscriptionPaymentView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 28/8/16.
 */
@Component
public class TotalSubscriptionCostAccountCreditedEventListener {
    private final SubscriptionPaymentViewRepository subscriptionPaymentViewRepository;

    @Autowired
    public TotalSubscriptionCostAccountCreditedEventListener(SubscriptionPaymentViewRepository subscriptionPaymentViewRepository) {
        this.subscriptionPaymentViewRepository = subscriptionPaymentViewRepository;
    }

    @EventHandler
    public void on(TotalSubscriptionCostAccountCreditedEvent event) {
        SubscriptionPaymentView subscriptionPaymentView = subscriptionPaymentViewRepository.findById(event.getId());
        subscriptionPaymentView.getTotalSubscriptionCostAccount().credit(event.getAmountToCredit());
        subscriptionPaymentViewRepository.save(subscriptionPaymentView);
    }
}
