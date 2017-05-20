package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.PaymentSchemeCreatedEvent;
import com.affaince.subscription.payments.query.repository.PaymentSchemesViewRepository;
import com.affaince.subscription.payments.query.view.PaymentSchemesView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/20/2017.
 */
@Component
public class PaymentSchemeCreatedEventListener {
    private PaymentSchemesViewRepository paymentSchemesViewRepository;
    @Autowired
    public PaymentSchemeCreatedEventListener(PaymentSchemesViewRepository paymentSchemesViewRepository) {
        this.paymentSchemesViewRepository = paymentSchemesViewRepository;
    }
    @EventHandler
    public void on(PaymentSchemeCreatedEvent event ){
        PaymentSchemesView paymentSchemesView= new PaymentSchemesView(event.getSchemeId(),event.getSchemeName(),event.getSchemeDescription(),event.getSchemeEquationInJsonFormat(),event.getSchemeRule(),event.getSchemeStartDate(),event.getSchemeEndDate());
        paymentSchemesViewRepository.save(paymentSchemesView);
    }
}
