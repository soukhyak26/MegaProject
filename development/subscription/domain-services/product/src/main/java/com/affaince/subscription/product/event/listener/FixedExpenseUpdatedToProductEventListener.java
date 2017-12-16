package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.command.UpdateFixedExpenseToProductCommand;
import com.affaince.subscription.product.event.FixedExpenseUpdatedToProductEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 16-10-2016.
 */
@Component
public class FixedExpenseUpdatedToProductEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(FixedExpenseUpdatedToProductEventListener.class);
    private final SubscriptionCommandGateway subscriptionCommandGateway;

    @Autowired
    public FixedExpenseUpdatedToProductEventListener(SubscriptionCommandGateway subscriptionCommandGateway) {
        this.subscriptionCommandGateway = subscriptionCommandGateway;
    }

    @EventHandler
    public void on(FixedExpenseUpdatedToProductEvent event) {
        UpdateFixedExpenseToProductCommand command = new UpdateFixedExpenseToProductCommand(event.getProductId(),
                event.getOperationExpense()
        );
        try {
            subscriptionCommandGateway.executeAsync(command);
        } catch (Exception ex) {
            LOGGER.error("Exception while sending UpdateFixedExpenseToProductCommand through cmmand gateway");
        }
    }
}
