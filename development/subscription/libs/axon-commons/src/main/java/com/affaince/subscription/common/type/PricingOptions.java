package com.affaince.subscription.common.type;

import com.affaince.subscription.common.deserializer.PricingOptionsDeserializer;
import com.affaince.subscription.common.serializer.PricingOptionsSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by mandar on 09-10-2016.
 */
@JsonSerialize(using = PricingOptionsSerializer.class)
@JsonDeserialize(using = PricingOptionsDeserializer.class)
public enum PricingOptions {
    ACCEPT_AUTOMATED_PRICE_GENERATION(1), MANUAL_PRICE_INPUT(2), PROMPT_PRICE_RECOMMENDATION(3);
    private int pricingOptionValue;

    PricingOptions(int pricingOptionValue) {
        this.pricingOptionValue = pricingOptionValue;
    }

    public static PricingOptions valueOf(int value) {
        for (PricingOptions r : PricingOptions.values()) {
            if (r.getPricingOptionValue() == value) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getPricingOptionValue() {
        return pricingOptionValue;
    }

}
