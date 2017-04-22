package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.product.command.UpdateDeliveryCountPerPriceBucketCommand;
import com.affaince.subscription.product.command.event.DeliveryStatusAndDispatchDateUpdatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-10-2015.
 */
@Component
public class DeliveryStatusAndDispatchDateUpdatedEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryStatusAndDispatchDateUpdatedEventListener.class);
    private final SubscriptionCommandGateway subscriptionCommandGateway;

    @Autowired
    public DeliveryStatusAndDispatchDateUpdatedEventListener(SubscriptionCommandGateway subscriptionCommandGateway) {
        this.subscriptionCommandGateway = subscriptionCommandGateway;
    }

    @EventHandler
    public void on(DeliveryStatusAndDispatchDateUpdatedEvent event) {
        event.getItemDispatchStatuses().stream().filter(itemDispatchStatus -> !itemDispatchStatus
                .getItemDeliveryStatus().equals(DeliveryStatus.DELIVERED)).forEach(itemDispatchStatus -> {
                    UpdateDeliveryCountPerPriceBucketCommand command = new UpdateDeliveryCountPerPriceBucketCommand(
                            itemDispatchStatus.getItemId(),
                            itemDispatchStatus.getPriceBucketId(),event.getDispatchDate()
                    );
                    try {
                        subscriptionCommandGateway.executeAsync(command);
                    } catch (Exception e) {
                        LOGGER.info("Fail to execute command UpdateDeliveryCountPerPriceBucketCommand");
                    }
                }
        );
    }
}
