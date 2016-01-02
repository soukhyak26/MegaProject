package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.OfferedPriceUpdatedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductRepository;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class PriceAndStockParametersUpdatedEventListener {

    private final ProductRepository productRepository;

    @Autowired
    public PriceAndStockParametersUpdatedEventListener(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(OfferedPriceUpdatedEvent event) {
        ProductView productView = productRepository.findOne(event.getProductId());
/*
        PriceParameters priceParameters = productView.getPriceParameters();
        priceParameters.setCurrentMRP(event.getCurrentMRP());
        priceParameters.setCurrentStockInUnits(event.getCurrentStockInUnits());
        priceParameters.setCurrentPriceDate(event.getCurrentPrizeDate());
        productView.setPriceParameters(priceParameters);
*/
        productRepository.save(productView);
    }
}
