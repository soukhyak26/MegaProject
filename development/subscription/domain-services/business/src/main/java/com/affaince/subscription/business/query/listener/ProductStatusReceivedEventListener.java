package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProductStatusReceivedEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 29/4/16.
 */
@Component
public class ProductStatusReceivedEventListener {
    private final BusinessAccountViewRepository businessAccountViewRepository;

    @Autowired
    public ProductStatusReceivedEventListener(BusinessAccountViewRepository businessAccountViewRepository) {
        this.businessAccountViewRepository = businessAccountViewRepository;
    }

    @EventHandler
    public void on(ProductStatusReceivedEvent productStatusReceivedEvent) {
        double currentPurchasePrice = productStatusReceivedEvent.getCurrentPurchasePrice();
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(
                Integer.valueOf(productStatusReceivedEvent.getCurrentPriceDate().getYear()).toString());
        businessAccountView.debitPurchaseCost(currentPurchasePrice);
    }
}
