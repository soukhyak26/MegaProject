package com.affaince.subscription.pricing.processor.calculator;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;

import java.util.List;

/**
 * Created by mandark on 27-03-2016.
 */
public class OpeningPriceCalculator extends AbstractPriceCalculator {
    public PriceBucketView calculatePrice(String productId, List<PriceBucketView> activePriceBuckets, ProductStatisticsView productStatisticsView) {
        PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);
        //if price is entered by merchant but there is no subscription yet as the product is not active yet...

        if (activePriceBuckets.size() == 1 && activePriceBuckets.get(0).getNumberOfExistingCustomersAssociatedWithAPrice() == 0 && latestPriceBucket.getEntityStatus()== EntityStatus.CREATED ) {
            latestPriceBucket.setEntityStatus(EntityStatus.ACTIVE);
            return latestPriceBucket;
        } else {
            return getNextCalculator().calculatePrice(productId, activePriceBuckets, productStatisticsView);
        }
    }
}
