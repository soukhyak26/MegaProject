package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import com.affaince.subscription.consumerbasket.command.event.SubscriptionActivatedEvent;
import com.affaince.subscription.consumerbasket.query.repository.ConsumerBasketRepository;
import com.affaince.subscription.consumerbasket.query.view.ConsumerBasketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
@Component
public class SubscriptionActivatedEventListener {

    private final ConsumerBasketRepository consumerBasketRepository;
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    public SubscriptionActivatedEventListener(ConsumerBasketRepository consumerBasketRepository) {
        this.consumerBasketRepository = consumerBasketRepository;
    }

    @EventHandler
    public void on(SubscriptionActivatedEvent event) {
        ConsumerBasketView consumerBasketView = consumerBasketRepository.findOne(event.getBasketId());
        consumerBasketView.setConsumerBasketStatus(ConsumerBasketActivationStatus.ACTIVATED);

    }
}
