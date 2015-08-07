package com.affaince.subscription.subscriptionableitem.registration.query.listener;

import com.affaince.subscription.subscriptionableitem.registration.command.event.UpdatePriceAndStockParametersEvent;
import com.affaince.subscription.subscriptionableitem.registration.query.repository.SubscriptionableItemRepository;
import com.affaince.subscription.subscriptionableitem.registration.query.view.SubscriptionableItemView;
import org.axonframework.eventhandling.annotation.EventHandler;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class PriceAndStockParametersUpdatedEventListener {

    private SubscriptionableItemRepository itemRepository;

    public PriceAndStockParametersUpdatedEventListener (SubscriptionableItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @EventHandler
    public void on (UpdatePriceAndStockParametersEvent event) {
        SubscriptionableItemView subscriptionableItemView = itemRepository.findOneByItemId(event.getItemId());
        subscriptionableItemView.setCurrentMRP(event.getCurrentMRP());
        subscriptionableItemView.setCurrentStockInUnits(event.getCurrentStockInUnits());
        subscriptionableItemView.setCurrentPriceDate(event.getCurrentPrizeDate());
        itemRepository.save(subscriptionableItemView);
    }
}
