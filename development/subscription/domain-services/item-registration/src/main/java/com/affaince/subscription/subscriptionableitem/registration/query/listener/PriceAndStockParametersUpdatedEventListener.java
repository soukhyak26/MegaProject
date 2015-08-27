package com.affaince.subscription.subscriptionableitem.registration.query.listener;

import com.affaince.subscription.subscriptionableitem.registration.command.event.UpdatePriceAndStockParametersEvent;
import com.affaince.subscription.subscriptionableitem.registration.query.repository.SubscriptionableItemRepository;
import com.affaince.subscription.subscriptionableitem.registration.query.view.PriceParameters;
import com.affaince.subscription.subscriptionableitem.registration.query.view.SubscriptionableItemView;
import org.axonframework.eventhandling.annotation.EventHandler;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class PriceAndStockParametersUpdatedEventListener {

    private final SubscriptionableItemRepository itemRepository;

    public PriceAndStockParametersUpdatedEventListener(SubscriptionableItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @EventHandler
    public void on(UpdatePriceAndStockParametersEvent event) {
        SubscriptionableItemView subscriptionableItemView = itemRepository.findOneByItemId(event.getItemId());
        PriceParameters priceParameters = subscriptionableItemView.getPriceParameters();
        priceParameters.setCurrentMRP(event.getCurrentMRP());
        priceParameters.setCurrentStockInUnits(event.getCurrentStockInUnits());
        priceParameters.setCurrentPriceDate(event.getCurrentPrizeDate());
        subscriptionableItemView.setPriceParameters(priceParameters);
        itemRepository.save(subscriptionableItemView);
    }
}
