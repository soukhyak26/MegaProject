package com.affaince.subscription.pricing.processor.calculator;

import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;

import java.util.List;

/**
 * Created by mandark on 27-03-2016.
 */
public class OpeningPriceCalculator extends AbstractPriceCalculator {
    public double calculatePrice(String productId, List<PriceBucketView> activePriceBuckets, ProductStatisticsView productStatisticsView) {
        //if price is entered by merchant but there is no subscription yet as the product is not active yet...
        if (activePriceBuckets.size() == 1 && activePriceBuckets.get(0).getNumberOfExistingCustomersAssociatedWithAPrice() == 0) {

            PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);
            return latestPriceBucket.getOfferedPricePerUnit();
        } else {
            return getNextCalculator().calculatePrice(productId, activePriceBuckets, productStatisticsView);
        }
    }
}
