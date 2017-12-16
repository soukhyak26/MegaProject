package com.affaince.subscription.product.services.pricing.calculator.instant;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.domain.PriceBucket;
import com.affaince.subscription.product.domain.Product;
import com.affaince.subscription.product.services.pricing.calculator.AbstractPriceCalculator;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 27-03-2016.
 */
@Component
public class OpeningPriceCalculator extends AbstractPriceCalculator {
    public PriceBucket calculatePrice(Product product, ProductDemandTrend productDemandTrend) {
        //if price is entered by merchant but there is no subscription yet as the product is not active yet...
        if (product.getActivePriceBuckets().size() == 1 && product.getActivePriceBuckets().values().iterator().next().getNumberOfExistingSubscriptions() == 0
                && product.getActivePriceBuckets().values().iterator().next().getEntityStatus() == EntityStatus.CREATED) {
            PriceBucket latestPriceBucket = product.findLatestActivePriceBucket();
            latestPriceBucket.setEntityStatus(EntityStatus.ACTIVE);
            return latestPriceBucket;
        } else {
            return getNextCalculator().calculatePrice(product, productDemandTrend);
        }
    }
}
