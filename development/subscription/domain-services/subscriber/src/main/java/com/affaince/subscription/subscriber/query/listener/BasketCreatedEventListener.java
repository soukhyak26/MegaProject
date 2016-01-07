package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.BasketCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.BasketViewRepository;
import com.affaince.subscription.subscriber.query.view.BasketView;
import com.affaince.subscription.subscriber.query.view.DeliveryItem;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
@Component
public class BasketCreatedEventListener {
    private final BasketViewRepository basketViewRepository;

    @Autowired
    public BasketCreatedEventListener(BasketViewRepository basketViewRepository) {
        this.basketViewRepository = basketViewRepository;
    }

    @EventHandler
    public void on(BasketCreatedEvent event) {
        List<DeliveryItem> deliveryItems = new ArrayList<>(event.getDeliveryItems().size());
        deliveryItems.addAll(event.getDeliveryItems().stream()
                .map(deliveryItem -> new DeliveryItem(deliveryItem.getDeliveryItemId(), deliveryItem.getDeliveryStatus()))
                .collect(Collectors.toList()));
        BasketView basketView = new BasketView(event.getBasketId(), event.getSubscriberId(), deliveryItems,
                event.getDeliveryDate(), event.getDispatchDate(), event.getStatus()
        );
        basketViewRepository.save(basketView);
    }
}
