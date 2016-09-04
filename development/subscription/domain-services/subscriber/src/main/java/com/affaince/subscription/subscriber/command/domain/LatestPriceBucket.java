package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.subscriber.command.UpdateLatestPriceBucketCommand;
import com.affaince.subscription.subscriber.command.event.LatestPriceBucketUpdatedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.joda.time.LocalDateTime;

/**
 * Created by rbsavaliya on 04-09-2016.
 */
public class LatestPriceBucket extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String productId;
    private String priceBucketId;
    private double offeredPricePerUnit;
    private LocalDateTime currentPriceDate;

    public LatestPriceBucket(String productId, String priceBucketId, double offeredPricePerUnit, LocalDateTime currentPriceDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.currentPriceDate = currentPriceDate;

        apply(new LatestPriceBucketUpdatedEvent(productId, priceBucketId, offeredPricePerUnit, currentPriceDate));
    }

    public LatestPriceBucket() {
    }

    public void update(UpdateLatestPriceBucketCommand command) {
        this.priceBucketId = priceBucketId;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.currentPriceDate = currentPriceDate;

        apply(new LatestPriceBucketUpdatedEvent(productId, priceBucketId, offeredPricePerUnit, currentPriceDate));
    }
}
