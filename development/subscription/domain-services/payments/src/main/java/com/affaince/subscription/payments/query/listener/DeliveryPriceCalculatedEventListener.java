package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.CalculatePaymentInstallmentCommand;
import com.affaince.subscription.payments.command.event.DeliveryPriceCalculatedEvent;
import com.affaince.subscription.payments.vo.InstalmentPaymentTracker;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryPriceCalculatedEventListener {

    public static final Logger LOG = LoggerFactory.getLogger(DeliveryPriceCalculatedEventListener.class);

    private SubscriptionCommandGateway subscriptionCommandGateway;

    @Autowired
    public DeliveryPriceCalculatedEventListener(SubscriptionCommandGateway subscriptionCommandGateway) {
        this.subscriptionCommandGateway = subscriptionCommandGateway;
    }

    @EventHandler
    public void on (DeliveryPriceCalculatedEvent event) {
        final CalculatePaymentInstallmentCommand command = new CalculatePaymentInstallmentCommand(
            event.getSubscriptionId(), event.getDeliveryWisePriceMap()
        );
        try {
            subscriptionCommandGateway.executeAsync(command);
        } catch (Exception e) {
            LOG.error("Exception in CalculatePaymentInstallmentCommand dispatch: " + e.getMessage());
        }
    }
}
