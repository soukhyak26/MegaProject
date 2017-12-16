package com.affaince.subscription.payments.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.CalculatePaymentInstallmentCommand;
import com.affaince.subscription.payments.event.DeliveryPriceCalculatedEvent;
import com.affaince.subscription.payments.query.repository.PaymentAccountViewRepository;
import com.affaince.subscription.payments.query.repository.PaymentSchemesViewRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryPriceCalculatedEventListener {

    public static final Logger LOG = LoggerFactory.getLogger(DeliveryPriceCalculatedEventListener.class);

    private final SubscriptionCommandGateway subscriptionCommandGateway;
    private final PaymentAccountViewRepository paymentAccountViewRepository;
    private final PaymentSchemesViewRepository paymentSchemesViewRepository;

    @Autowired
    public DeliveryPriceCalculatedEventListener(SubscriptionCommandGateway subscriptionCommandGateway, PaymentAccountViewRepository paymentAccountViewRepository, PaymentSchemesViewRepository paymentSchemesViewRepository) {
        this.subscriptionCommandGateway = subscriptionCommandGateway;
        this.paymentAccountViewRepository = paymentAccountViewRepository;
        this.paymentSchemesViewRepository = paymentSchemesViewRepository;
    }

    @EventHandler
    public void on (DeliveryPriceCalculatedEvent event) {
        String paymentSchemeId = paymentAccountViewRepository.findOne(event.getSubscriptionId()).getPaymentSchemeId();
        String paymentScheme = paymentSchemesViewRepository.findOne(paymentSchemeId).getSchemeRuleInJsonFormat();
        final CalculatePaymentInstallmentCommand command = new CalculatePaymentInstallmentCommand(
            event.getSubscriptionId(), event.getDeliveryWisePriceMap(), paymentScheme
        );
        try {
            subscriptionCommandGateway.executeAsync(command);
        } catch (Exception e) {
            LOG.error("Exception in CalculatePaymentInstallmentCommand dispatch: " + e.getMessage());
        }
    }
}
