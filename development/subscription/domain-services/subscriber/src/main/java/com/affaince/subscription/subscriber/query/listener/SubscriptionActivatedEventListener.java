package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import com.affaince.subscription.subscriber.command.event.SubscriptionActivatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
@Component
public class SubscriptionActivatedEventListener {

    private final SubscriptionRepository subscriptionRepository;
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    public SubscriptionActivatedEventListener(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @EventHandler
    public void on(SubscriptionActivatedEvent event) {
        SubscriptionView subscriptionView = subscriptionRepository.findOne(event.getBasketId());
        subscriptionView.setConsumerBasketStatus(ConsumerBasketActivationStatus.ACTIVATED);

    }
}
