package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.consumerbasket.command.SubscriptionActivationCommand;
import com.affaince.subscription.consumerbasket.query.event.PaymentConfirmedEvent;
import com.affaince.subscription.consumerbasket.query.repository.ConsumerBasketRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
@Component
public class PaymentConfirmedEventListener {

    private final ConsumerBasketRepository consumerBasketRepository;
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    public PaymentConfirmedEventListener(ConsumerBasketRepository consumerBasketRepository) {
        this.consumerBasketRepository = consumerBasketRepository;
    }

    @EventHandler
    public void on(PaymentConfirmedEvent event) throws Exception {
        SubscriptionActivationCommand command = new SubscriptionActivationCommand(event.getBasketId());
        commandGateway.executeAsync(command);
    }
}
