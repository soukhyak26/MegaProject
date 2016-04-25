package com.affaince.subscription.pricing.detereminator;


import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.vo.PriceDeterminationCriteria;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public interface PriceDeterminator {
    public PriceBucketView calculateOfferedPrice(PriceDeterminationCriteria priceDeterminationCriteria);

}