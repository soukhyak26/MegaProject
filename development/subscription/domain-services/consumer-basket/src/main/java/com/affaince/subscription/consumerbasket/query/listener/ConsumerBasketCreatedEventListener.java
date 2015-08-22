package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.consumerbasket.command.event.ConsumerBasketCreatedEvent;
import com.affaince.subscription.consumerbasket.query.repository.ConsumerBasketRepository;
import com.affaince.subscription.consumerbasket.query.view.ConsumerBasketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
@Component
public class ConsumerBasketCreatedEventListener {

    private final ConsumerBasketRepository basketRepository;

    @Autowired
    public ConsumerBasketCreatedEventListener(ConsumerBasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @EventHandler
    public void on(ConsumerBasketCreatedEvent event) {
        ConsumerBasketView consumerBasketView = new ConsumerBasketView(event.getBasketId(),
                event.getUserId(), null, null, null, null, 0.0, 0.0);
        basketRepository.save(consumerBasketView);
    }
}
