package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.registration.command.ReceiveProductStatusCommand;
import com.affaince.subscription.product.registration.command.event.ProductStatusReceivedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandark on 02-04-2016.
 */
public class ProductStatusReceivedEventListener {

    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @EventHandler
    public void on(ProductStatusReceivedEvent event) throws Exception {
        ReceiveProductStatusCommand command = new ReceiveProductStatusCommand(event.getProductId(), event.getCurrentPurchasePrice(), event.getCurrentMRP(), event.getCurrentStockInUnits(), event.getCurrentPriceDate());
        commandGateway.executeAsync(command);
    }
}
