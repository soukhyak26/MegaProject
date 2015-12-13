package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.subscriber.command.event.BasketDeletedEvent;
import com.affaince.subscription.subscriber.query.repository.BasketRepository;
import com.affaince.subscription.subscriber.query.view.BasketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 11-10-2015.
 */
@Component
public class BasketDeletedEventListener {

    private final BasketRepository basketRepository;

    @Autowired
    public BasketDeletedEventListener(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @EventHandler
    public void on(BasketDeletedEvent event) {
        final BasketView basketView = basketRepository.findOne(event.getBasketId());
        basketView.setStatus(DeliveryStatus.DELETED);
        basketRepository.save(basketView);
    }
}
