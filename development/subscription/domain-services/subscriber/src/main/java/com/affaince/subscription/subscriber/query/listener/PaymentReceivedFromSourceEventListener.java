package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.PaymentReceivedFromSourceCommand;
import com.affaince.subscription.subscriber.command.event.PaymentReceivedFromSourceEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
@Component
public class PaymentReceivedFromSourceEventListener {

    private final SubscriptionViewRepository subscriptionViewRepository;
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    public PaymentReceivedFromSourceEventListener(SubscriptionViewRepository subscriptionViewRepository) {
        this.subscriptionViewRepository = subscriptionViewRepository;
    }

    @EventHandler
    public void on(PaymentReceivedFromSourceEvent event) throws Exception {
        final SubscriptionView subscriptionView = subscriptionViewRepository.findBySubscriberId(event.getSubscriberId());
        PaymentReceivedFromSourceCommand command = new PaymentReceivedFromSourceCommand(subscriptionView.getSubscriptionId(), event.getSubscriberId(),
                event.getPaymentAmount(), event.getPaymentDate());
        commandGateway.executeAsync(command);
    }
}
