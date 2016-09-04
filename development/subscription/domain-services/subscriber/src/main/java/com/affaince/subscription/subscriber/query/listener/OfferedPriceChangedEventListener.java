package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.subscriber.command.UpdateLatestPriceBucketCommand;
import com.affaince.subscription.subscriber.command.event.OfferedPriceChangedEvent;
import com.affaince.subscription.subscriber.query.repository.LatestPriceBucketViewRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 04-09-2016.
 */
@Component
public class OfferedPriceChangedEventListener {

    private final LatestPriceBucketViewRepository latestPriceBucketViewRepository;
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public OfferedPriceChangedEventListener(LatestPriceBucketViewRepository latestPriceBucketViewRepository, SubscriptionCommandGateway commandGateway) {
        this.latestPriceBucketViewRepository = latestPriceBucketViewRepository;
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(OfferedPriceChangedEvent offeredPriceChangedEvent) {
        final UpdateLatestPriceBucketCommand command = new UpdateLatestPriceBucketCommand(
                offeredPriceChangedEvent.getProductId(), offeredPriceChangedEvent.getPriceBucketId(),
                offeredPriceChangedEvent.getOfferedPricePerUnit(), offeredPriceChangedEvent.getCurrentPriceDate()
        );
        commandGateway.send(command);
    }
}
