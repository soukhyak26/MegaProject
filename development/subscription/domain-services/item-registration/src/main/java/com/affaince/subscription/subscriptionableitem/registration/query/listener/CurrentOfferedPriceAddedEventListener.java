package com.affaince.subscription.subscriptionableitem.registration.query.listener;

import com.affaince.subscription.subscriptionableitem.registration.command.event.CurrentOfferedPriceAddedEvent;
import com.affaince.subscription.subscriptionableitem.registration.query.repository.SubscriptionableItemRepository;
import com.affaince.subscription.subscriptionableitem.registration.query.view.SubscriptionableItemView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 07-08-2015.
 */
@Component
public class CurrentOfferedPriceAddedEventListener {

    private final SubscriptionableItemRepository itemRepository;

    @Autowired
    public CurrentOfferedPriceAddedEventListener(SubscriptionableItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @EventHandler
    public void on(CurrentOfferedPriceAddedEvent event) {
        SubscriptionableItemView itemView = itemRepository.findOneByItemId(event.getItemId());
        itemView.setCurrentOfferedPrice(event.getCurrentOfferedPrice());
        itemRepository.save(itemView);
    }
}
