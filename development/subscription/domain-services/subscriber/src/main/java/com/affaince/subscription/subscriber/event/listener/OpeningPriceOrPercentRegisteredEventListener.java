package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.subscriber.event.OpeningPriceOrPercentRegisteredEvent;
import com.affaince.subscription.subscriber.query.repository.LatestPriceBucketViewRepository;
import com.affaince.subscription.subscriber.query.view.LatestPriceBucketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 12-11-2016.
 */
@Component
public class OpeningPriceOrPercentRegisteredEventListener {
    private final LatestPriceBucketViewRepository latestPriceBucketViewRepository;

    @Autowired
    public OpeningPriceOrPercentRegisteredEventListener(LatestPriceBucketViewRepository latestPriceBucketViewRepository) {
        this.latestPriceBucketViewRepository = latestPriceBucketViewRepository;
    }

    @EventHandler
    public void on(OpeningPriceOrPercentRegisteredEvent event) {
        final LatestPriceBucketView latestPriceBucketView = new LatestPriceBucketView(
                event.getProductId(), event.getPriceBucketId(),
                event.getOfferedPriceOrPercentDiscountPerUnit(), event.getFromDate()
        );
        latestPriceBucketViewRepository.save(latestPriceBucketView);
    }
}
