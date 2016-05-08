package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.registration.command.UpdateDeliveryExpenseToProductCommand;
import com.affaince.subscription.product.registration.command.event.SubscriptionSpecificOperatingExpenseCalculatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rsavaliya on 8/5/16.
 */
@Component
public class SubscriptionSpecificOperatingExpenseCalculatedEventListener {

    private final SubscriptionCommandGateway subscriptionCommandGateway;

    @Autowired
    public SubscriptionSpecificOperatingExpenseCalculatedEventListener(SubscriptionCommandGateway subscriptionCommandGateway) {
        this.subscriptionCommandGateway = subscriptionCommandGateway;
    }

    @EventHandler
    public void on (SubscriptionSpecificOperatingExpenseCalculatedEvent event) throws Exception {
        final UpdateDeliveryExpenseToProductCommand command = new UpdateDeliveryExpenseToProductCommand (
                event.getProductId(), event.getOperationExpense()
        );
        subscriptionCommandGateway.executeAsync(command);
    }
}
