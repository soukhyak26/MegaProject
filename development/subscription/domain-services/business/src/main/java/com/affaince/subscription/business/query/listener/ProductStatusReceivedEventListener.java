package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.ProductStatusReceivedCommand;
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
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    //@Autowired
    public ProductStatusReceivedEventListener() {

    }

    @EventHandler
    public void on(ProductStatusReceivedEvent event) throws Exception {
        /*double currentPurchasePrice = productStatusReceivedEvent.getCurrentPurchasePrice();
        BusinessAccountView businessAccountView = businessAccountViewRepository.findById(
                Integer.valueOf(productStatusReceivedEvent.getCurrentPriceDate().getYear()).toString());
        businessAccountView.debitPurchaseCost(currentPurchasePrice);*/
        ProductStatusReceivedCommand command = new ProductStatusReceivedCommand(event.getProductId(), event.getCurrentPurchasePrice(), event.getCurrentMRP(), event.getCurrentStockInUnits(), event.getCurrentPriceDate());
        commandGateway.executeAsync(command);
    }
}
