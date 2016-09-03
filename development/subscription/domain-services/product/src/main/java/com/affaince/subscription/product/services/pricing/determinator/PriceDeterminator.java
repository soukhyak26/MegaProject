package com.affaince.subscription.product.services.pricing.determinator;


import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public interface PriceDeterminator {
    public PriceBucket calculateOfferedPrice(Product product, ProductDemandTrend productDemandTrend);

}
