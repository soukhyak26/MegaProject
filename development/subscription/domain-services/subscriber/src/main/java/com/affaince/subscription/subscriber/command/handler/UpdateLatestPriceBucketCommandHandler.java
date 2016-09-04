package com.affaince.subscription.subscriber.command.handler;

import com.affaince.subscription.subscriber.command.UpdateLatestPriceBucketCommand;
import com.affaince.subscription.subscriber.command.domain.LatestPriceBucket;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.AggregateNotFoundException;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 04-09-2016.
 */
@Component
public class UpdateLatestPriceBucketCommandHandler {

    private final Repository<LatestPriceBucket> repository;

    @Autowired
    public UpdateLatestPriceBucketCommandHandler(Repository<LatestPriceBucket> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(UpdateLatestPriceBucketCommand command) {
        try {
            LatestPriceBucket latestPriceBucket = repository.load(command.getProductId());
            latestPriceBucket.update(command);
        } catch (AggregateNotFoundException e) {
            repository.add(new LatestPriceBucket(command.getProductId(), command.getPriceBucketId(),
                    command.getOfferedPricePerUnit(), command.getCurrentPriceDate()));
        }
    }
}
