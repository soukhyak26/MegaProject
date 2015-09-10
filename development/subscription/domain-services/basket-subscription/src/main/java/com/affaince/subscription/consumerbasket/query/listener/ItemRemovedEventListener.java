package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.consumerbasket.command.event.ItemRemovedEvent;
import com.affaince.subscription.consumerbasket.query.repository.ConsumerBasketRepository;
import com.affaince.subscription.consumerbasket.query.view.ConsumerBasketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-09-2015.
 */
@Component
public class ItemRemovedEventListener {

    private final ConsumerBasketRepository repository;

    @Autowired
    public ItemRemovedEventListener(ConsumerBasketRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ItemRemovedEvent event) {
        ConsumerBasketView consumerBasketView = repository.findOne(event.getBasketId());
        consumerBasketView.getBasketItems().removeIf(item -> item.getItemId().equals(event.getItemId()));
        repository.save(consumerBasketView);
    }
}
