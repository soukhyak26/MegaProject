package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.SubscriptionActivationCommand;
import com.affaince.subscription.subscriber.query.event.PaymentConfirmedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
@Component
public class PaymentConfirmedEventListener {

    private final SubscriptionRepository subscriptionRepository;
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    public PaymentConfirmedEventListener(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @EventHandler
    public void on(PaymentConfirmedEvent event) throws Exception {
        SubscriptionActivationCommand command = new SubscriptionActivationCommand(event.getSubscriptionId());
        commandGateway.executeAsync(command);
    }
}
