package com.affaince.subscription.product.services.pricing.processor.calculator.classic;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.services.pricing.processor.calculator.AbstractPriceCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandark on 27-03-2016.
 */
@Component
public class OpeningPriceCalculator extends AbstractPriceCalculator {
    public PriceBucketView calculatePrice(List<PriceBucketView> activePriceBuckets, ProductActualsView productActualsView, ProductForecastView productForecastView) {
        //if price is entered by merchant but there is no subscription yet as the product is not active yet...
        if (activePriceBuckets.size() == 1 && activePriceBuckets.get(0).getNumberOfExistingCustomersAssociatedWithAPrice() == 0 && activePriceBuckets.get(0).getEntityStatus()== EntityStatus.CREATED ) {
            PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);
            latestPriceBucket.setEntityStatus(EntityStatus.ACTIVE);
            return latestPriceBucket;
        } else {
            return getNextCalculator().calculatePrice(activePriceBuckets, productActualsView, productForecastView);
        }
    }
}
