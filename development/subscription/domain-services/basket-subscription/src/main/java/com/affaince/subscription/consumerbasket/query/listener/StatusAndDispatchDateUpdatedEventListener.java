package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.consumerbasket.command.ItemDispatchStatus;
import com.affaince.subscription.consumerbasket.command.event.StatusAndDispatchDateUpdatedEvent;
import com.affaince.subscription.consumerbasket.query.repository.BasketRepository;
import com.affaince.subscription.consumerbasket.query.view.BasketView;
import com.affaince.subscription.consumerbasket.query.view.DeliveryItem;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-10-2015.
 */
@Component
public class StatusAndDispatchDateUpdatedEventListener {

    private final BasketRepository basketRepository;

    @Autowired
    public StatusAndDispatchDateUpdatedEventListener(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @EventHandler
    public void on(StatusAndDispatchDateUpdatedEvent event) {
        BasketView basketView = basketRepository.findOne(event.getBasketId());
        for (ItemDispatchStatus itemDispatchStatus : event.getItemDispatchStatuses()) {
            DeliveryItem deliveryItem = new DeliveryItem(itemDispatchStatus.getItemId(), null);
            deliveryItem = basketView.getDeliveryItems().get(basketView.getDeliveryItems().indexOf(deliveryItem));
            deliveryItem.setDeliveryStatus(DeliveryStatus.valueOf(itemDispatchStatus.getItemDeliveryStatus()));
        }
        basketView.setDispatchDate(new LocalDate(event.getDispatchDate()));
        basketView.setStatus(DeliveryStatus.valueOf(event.getBasketDeliveryStatus()));
        basketRepository.save(basketView);
    }
}
