package com.affaince.subscription.product.services.pricing.calculator.instant;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.services.pricing.calculator.AbstractPriceCalculator;
import com.affaince.subscription.product.vo.PriceCalculationParameters;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 27-03-2016.
 */
@Component
public class OpeningPriceCalculator extends AbstractPriceCalculator {
    public PriceBucketView calculatePrice(PriceCalculationParameters priceCalculationParameters) {
        //if price is entered by merchant but there is no subscription yet as the product is not active yet...
        if (priceCalculationParameters.getActivePriceBuckets().size() == 1 && priceCalculationParameters.getActivePriceBuckets().get(0).getNumberOfExistingCustomersAssociatedWithAPrice() == 0 && priceCalculationParameters.getActivePriceBuckets().get(0).getEntityStatus() == EntityStatus.CREATED) {
            PriceBucketView latestPriceBucket = getLatestPriceBucket(priceCalculationParameters.getActivePriceBuckets());
            latestPriceBucket.setEntityStatus(EntityStatus.ACTIVE);
            return latestPriceBucket;
        } else {
            return getNextCalculator().calculatePrice(priceCalculationParameters);
        }
    }
}
