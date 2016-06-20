package com.affaince.subscription.product.services.pricing.determinator;


import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.vo.PriceDeterminationCriteria;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public interface PriceDeterminator {
    public PriceBucketView calculateOfferedPrice(PriceDeterminationCriteria priceDeterminationCriteria);

}
