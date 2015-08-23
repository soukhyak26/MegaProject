package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.consumerbasket.command.event.ItemAddedToConsumerBasketEvent;
import com.affaince.subscription.consumerbasket.query.repository.ConsumerBasketRepository;
import com.affaince.subscription.consumerbasket.query.view.BasketItem;
import com.affaince.subscription.consumerbasket.query.view.ConsumerBasketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 23-08-2015.
 */
@Component
public class ItemAddedToConsumerBasketEventHandler {

    private final ConsumerBasketRepository repository;

    @Autowired
    public ItemAddedToConsumerBasketEventHandler(ConsumerBasketRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ItemAddedToConsumerBasketEvent event) {
        BasketItem basketItem = new BasketItem(event.getItemId(), event.getProductId(),
                event.getQuantityPerBasket(), event.getFrequency(), event.getItemMRP(),
                event.getItemDiscountedPrice());
        ConsumerBasketView consumerBasketView = repository.findOne(event.getBasketId());
        List<BasketItem> basketItems = consumerBasketView.getBasketItems();
        if (basketItems == null) {
            basketItems = new ArrayList<>();
        }
        basketItems.add(basketItem);
        consumerBasketView.setBasketItems(basketItems);
        repository.save(consumerBasketView);
    }
}
