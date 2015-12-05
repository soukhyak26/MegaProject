package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.CreateProductEvent;
import com.affaince.subscription.product.registration.query.repository.ProductRepository;
import com.affaince.subscription.product.registration.query.view.PriceParameters;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Component
public class ProductCreatedEventListener {
    private final ProductRepository itemRepository;

    @Autowired
    public ProductCreatedEventListener(ProductRepository repository) {
        this.itemRepository = repository;
    }


    @EventHandler
    public void on(CreateProductEvent event) {
        final PriceParameters priceParameters = new PriceParameters(
                event.getPurchasePricePerUnit(),
                event.getCurrentMRP(),
                event.getCurrentStockInUnits(),
                event.getCurrentOfferedPrice(),
                event.getCurrentPriceDate()
        );
        final ProductView productView = new ProductView(
                event.getProductId(),
                event.getBatchId(),
                event.getCategoryId(),
                event.getCategoryName(),
                event.getSubCategoryId(),
                event.getSubCategoryName(),
                priceParameters
        );
        itemRepository.save(productView);
    }
}
