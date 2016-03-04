package com.affaince.subscription.pricing.processor.exception;

import com.affaince.subscription.pricing.query.view.ProductView;
import com.affaince.subscription.pricing.vo.PricingStrategyType;

/**
 * Created by mandark on 04-03-2016.
 */
public class PricingStrategyDeterminator {
    public PricingStrategyType decideProductPricingStrategy(ProductView productView) {
        //yet to be implemented
        return PricingStrategyType.DEFAULT_PRICING_STRATEGY;
    }
}
