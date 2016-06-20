package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.UpdateProductSubscriptionCommand;
import com.affaince.subscription.product.command.event.ProductSubscriptionActivatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSubscriptionActivatedEventListener {

    SubscriptionCommandGateway commandGateway;
    @Autowired
    public ProductSubscriptionActivatedEventListener(SubscriptionCommandGateway commandGateway) {
        this.commandGateway= commandGateway;
    }

    //not a correct implementation
    @EventHandler
    public void on(ProductSubscriptionActivatedEvent event) throws Exception {
        UpdateProductSubscriptionCommand command= new UpdateProductSubscriptionCommand(event.getProductId(),event.getSubscriptionCount(),event.getSubscriptionActivationDate());
        commandGateway.executeAsync(command);
    }
}
