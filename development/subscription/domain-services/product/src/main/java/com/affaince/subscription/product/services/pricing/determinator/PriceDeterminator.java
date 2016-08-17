package com.affaince.subscription.product.services.pricing.determinator;


import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.query.view.PriceBucketView;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public interface PriceDeterminator {
    public PriceBucketView calculateOfferedPrice(String productId, ProductDemandTrend productDemandTrend);

}
