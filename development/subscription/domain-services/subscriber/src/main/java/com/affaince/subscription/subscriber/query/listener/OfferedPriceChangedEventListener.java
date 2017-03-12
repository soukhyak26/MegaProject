package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.OfferedPriceChangedEvent;
import com.affaince.subscription.subscriber.query.repository.LatestPriceBucketViewRepository;
import com.affaince.subscription.subscriber.query.view.LatestPriceBucketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 04-09-2016.
 */
@Component
public class OfferedPriceChangedEventListener {

    private final LatestPriceBucketViewRepository latestPriceBucketViewRepository;

    @Autowired
    public OfferedPriceChangedEventListener(LatestPriceBucketViewRepository latestPriceBucketViewRepository) {
        this.latestPriceBucketViewRepository = latestPriceBucketViewRepository;
    }

    @EventHandler
    public void on(OfferedPriceChangedEvent event) {
        final LatestPriceBucketView latestPriceBucketView = new LatestPriceBucketView(
                event.getProductId(), event.getPriceBucketId(),
                event.getOfferedPriceOrPercentDiscountPerUnit(), event.getCurrentPriceDate()
        );
        latestPriceBucketViewRepository.save(latestPriceBucketView);
    }
}
