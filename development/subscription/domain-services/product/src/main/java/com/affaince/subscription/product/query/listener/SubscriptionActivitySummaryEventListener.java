package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.UpdateProductSubscriptionCommand;
import com.affaince.subscription.product.command.event.ProductSubscriptionActivatedEvent;
import com.affaince.subscription.product.command.event.SubscriptionActivitySummaryEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionActivitySummaryEventListener {

    SubscriptionCommandGateway commandGateway;
    @Autowired
    public SubscriptionActivitySummaryEventListener(SubscriptionCommandGateway commandGateway) {
        this.commandGateway= commandGateway;
    }

    //not a correct implementation
    @EventHandler
    public void on(SubscriptionActivitySummaryEvent event) throws Exception {

        UpdateProductSubscriptionCommand command=
                new UpdateProductSubscriptionCommand(event.getProductId(),
                        event.getSubscribedItems(), event.getSubscriptionChangedDate());
        commandGateway.executeAsync(command);
    }
}
