package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.ReceiveProductStatusCommand;
import com.affaince.subscription.product.command.event.ProductStatusReceivedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 02-04-2016.
 */
@Component
public class ProductStatusReceivedEventListener {

    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @EventHandler
    public void on(ProductStatusReceivedEvent event) throws Exception {
        ReceiveProductStatusCommand command = new ReceiveProductStatusCommand(event.getProductId(), event.getCurrentPurchasePricePerUnit(), event.getCurrentMRP(), event.getCurrentStockInUnits(), event.getCurrentPriceDate());
        commandGateway.executeAsync(command);
    }
}
