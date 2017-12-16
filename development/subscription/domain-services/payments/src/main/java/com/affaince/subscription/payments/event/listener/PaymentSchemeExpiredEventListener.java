package com.affaince.subscription.payments.event.listener;

import com.affaince.subscription.payments.event.PaymentSchemeExpiredEvent;
import com.affaince.subscription.payments.query.repository.PaymentSchemesViewRepository;
import com.affaince.subscription.payments.query.view.PaymentSchemesView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/20/2017.
 */
@Component
public class PaymentSchemeExpiredEventListener {
    private PaymentSchemesViewRepository paymentSchemesViewRepository;

    @Autowired
    public PaymentSchemeExpiredEventListener(PaymentSchemesViewRepository paymentSchemesViewRepository) {
        this.paymentSchemesViewRepository = paymentSchemesViewRepository;
    }

    @EventHandler
    public void on(PaymentSchemeExpiredEvent event){
        PaymentSchemesView paymentSchemesView = paymentSchemesViewRepository.findOne(event.getSchemeId());
        paymentSchemesView.setSchemeEndDate(event.getExpiryDate());
        paymentSchemesViewRepository.save(paymentSchemesView);
    }
}
