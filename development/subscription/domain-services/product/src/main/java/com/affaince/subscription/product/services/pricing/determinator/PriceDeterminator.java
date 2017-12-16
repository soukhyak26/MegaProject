package com.affaince.subscription.product.services.pricing.determinator;


import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.domain.PriceBucket;
import com.affaince.subscription.product.domain.Product;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public interface PriceDeterminator {
    PriceBucket calculateOfferedPrice(Product product, ProductDemandTrend productDemandTrend);

}
