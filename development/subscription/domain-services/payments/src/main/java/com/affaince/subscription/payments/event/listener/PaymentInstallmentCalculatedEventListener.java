package com.affaince.subscription.payments.event.listener;

import com.affaince.subscription.payments.event.PaymentInstallmentCalculatedEvent;
import com.affaince.subscription.payments.query.repository.PaymentInstallmentViewRepository;
import com.affaince.subscription.payments.query.view.PaymentInstallmentView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentInstallmentCalculatedEventListener {

    @Autowired
    private PaymentInstallmentViewRepository repository;

    @EventHandler
    public void on (PaymentInstallmentCalculatedEvent event) {
        final PaymentInstallmentView paymentInstallmentView = new PaymentInstallmentView(
                event.getSubscriberId(), event.getSubscriptionId(), event.getInstalmentPaymentTrackers()
        );
        repository.save(paymentInstallmentView);
    }
}
