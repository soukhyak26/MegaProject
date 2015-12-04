package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.UpdatePriceAndStockParametersEvent;
import com.affaince.subscription.product.registration.query.repository.ProductRepository;
import com.affaince.subscription.product.registration.query.view.PriceParameters;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class PriceAndStockParametersUpdatedEventListener {

    private final ProductRepository itemRepository;

    public PriceAndStockParametersUpdatedEventListener(ProductRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @EventHandler
    public void on(UpdatePriceAndStockParametersEvent event) {
        ProductView productView = itemRepository.findOneByItemId(event.getItemId());
        PriceParameters priceParameters = productView.getPriceParameters();
        priceParameters.setCurrentMRP(event.getCurrentMRP());
        priceParameters.setCurrentStockInUnits(event.getCurrentStockInUnits());
        priceParameters.setCurrentPriceDate(event.getCurrentPrizeDate());
        productView.setPriceParameters(priceParameters);
        itemRepository.save(productView);
    }
}
