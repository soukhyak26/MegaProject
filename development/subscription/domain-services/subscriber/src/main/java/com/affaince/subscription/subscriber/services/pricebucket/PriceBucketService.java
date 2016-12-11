package com.affaince.subscription.subscriber.services.pricebucket;

import com.affaince.subscription.subscriber.command.domain.LatestPriceBucket;
import com.affaince.subscription.subscriber.query.repository.LatestPriceBucketViewRepository;
import com.affaince.subscription.subscriber.query.view.LatestPriceBucketView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 11-12-2016.
 */
@Component
public class PriceBucketService {
    @Autowired
    private LatestPriceBucketViewRepository repository;

    public LatestPriceBucket fetchLatestPriceBucket(final String productId) {
        final LatestPriceBucketView latestPriceBucketView = repository.findOne(productId);
        final LatestPriceBucket latestPriceBucket = new LatestPriceBucket(latestPriceBucketView.getProductId(),
                latestPriceBucketView.getPriceBucketId(), latestPriceBucketView.getOfferedPricePerUnit(),
                latestPriceBucketView.getCurrentPriceDate());
        return latestPriceBucket;
    }
}
