package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.common.type.Frequency;
import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
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
public class ItemAddedToConsumerBasketEventListener {

    private final ConsumerBasketRepository repository;

    @Autowired
    public ItemAddedToConsumerBasketEventListener(ConsumerBasketRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ItemAddedToConsumerBasketEvent event) {
        final Frequency frequency = new Frequency(event.getQuantityPerBasket(),
                new Period(event.getFrequency(), PeriodUnit.valueOf(event.getFrequencyUnit())));
        BasketItem basketItem = new BasketItem(event.getItemId(), frequency, event.getDiscountedOfferedPrice());
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
