package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.PaymentAccountCreatedEvent;
import com.affaince.subscription.payments.query.repository.PaymentAccountViewRepository;
import com.affaince.subscription.payments.query.view.PaymentAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/9/2017.
 */
@Component
public class PaymentAccountCreatedEventListener {
    private final PaymentAccountViewRepository paymentAccountViewRepository;
    @Autowired
    public PaymentAccountCreatedEventListener(PaymentAccountViewRepository paymentAccountViewRepository) {
        this.paymentAccountViewRepository = paymentAccountViewRepository;
    }

    @EventHandler
    public void on(PaymentAccountCreatedEvent event){
        PaymentAccountView paymentAccountView= new PaymentAccountView(event.getSubscriptionId(),event.getSubscriberId(),event.getCreationDate());
        paymentAccountViewRepository.save(paymentAccountView);
    }
}
