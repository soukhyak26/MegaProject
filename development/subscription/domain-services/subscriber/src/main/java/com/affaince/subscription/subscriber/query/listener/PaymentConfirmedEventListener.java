package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.SubscriptionActivationCommand;
import com.affaince.subscription.subscriber.query.event.PaymentConfirmedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
@Component
public class PaymentConfirmedEventListener {

    private final SubscriptionViewRepository subscriptionViewRepository;
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    public PaymentConfirmedEventListener(SubscriptionViewRepository subscriptionViewRepository) {
        this.subscriptionViewRepository = subscriptionViewRepository;
    }

    @EventHandler
    public void on(PaymentConfirmedEvent event) throws Exception {
        SubscriptionActivationCommand command = new SubscriptionActivationCommand(event.getSubscriptionId());
        commandGateway.executeAsync(command);
    }
}
