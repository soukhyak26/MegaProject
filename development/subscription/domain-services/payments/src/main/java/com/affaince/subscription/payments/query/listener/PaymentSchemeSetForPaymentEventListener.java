package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.PaymentSchemeSetForPaymentEvent;
import com.affaince.subscription.payments.query.repository.PaymentAccountViewRepository;
import com.affaince.subscription.payments.query.view.PaymentAccountView;
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

    public void on(PaymentSchemeSetForPaymentEvent event){
        PaymentAccountView paymentAccountView= paymentAccountViewRepository.findOne(event.getSubscriptionId());
        paymentAccountView.setPaymentSchemeId(event.getPaymentSchemeId());
        paymentAccountViewRepository.save(paymentAccountView);
    }
}
