package com.affaince.subscription.payments.event.listener;

import com.affaince.subscription.payments.event.PaymentSchemeSetForPaymentEvent;
import com.affaince.subscription.payments.query.repository.PaymentAccountViewRepository;
import com.affaince.subscription.payments.query.view.PaymentAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/9/2017.
 */
@Component
public class PaymentSchemeSetForPaymentEventListener {
    private final PaymentAccountViewRepository paymentAccountViewRepository;

    @Autowired
    public PaymentSchemeSetForPaymentEventListener(PaymentAccountViewRepository paymentAccountViewRepository) {
        this.paymentAccountViewRepository = paymentAccountViewRepository;
    }

    @EventHandler
    public void on(PaymentSchemeSetForPaymentEvent event){
        PaymentAccountView paymentAccountView= paymentAccountViewRepository.findOne(event.getSubscriptionId());
        paymentAccountView.setPaymentSchemeId(event.getPaymentSchemeId());
        paymentAccountViewRepository.save(paymentAccountView);
    }
}
