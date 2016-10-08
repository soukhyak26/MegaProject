package com.affaince.subscription.product.web.request;

import com.affaince.subscription.product.vo.PricingChoiceType;

/**
 * Created by mandar on 08-10-2016.
 */
public class RegisterPriceChoiceRequest {
    private final PricingChoiceType pricingChoiceType;
    private final double pricingValue;

    public RegisterPriceChoiceRequest(PricingChoiceType pricingChoiceType, double pricingValue) {
        this.pricingChoiceType = pricingChoiceType;
        this.pricingValue = pricingValue;
    }

    public PricingChoiceType getPricingChoiceType() {
        return pricingChoiceType;
    }

    public double getPricingValue() {
        return pricingValue;
    }
}
