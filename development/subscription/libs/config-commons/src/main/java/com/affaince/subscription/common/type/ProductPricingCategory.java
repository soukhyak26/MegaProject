package com.affaince.subscription.common.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by rbsavaliya on 05-12-2015.
 */

public enum ProductPricingCategory {

    NO_COMMITMENT(0), PRICE_COMMITMENT(1), DISCOUNT_COMMITMENT(2);

    private final int index;

    ProductPricingCategory(final int index) {
        this.index = index;
    }

    @JsonCreator
    public static ProductPricingCategory valueOf(int value) {
        for (ProductPricingCategory r : ProductPricingCategory.values()) {
            if (r.getIndex() == value) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }
    @JsonValue
    public int getIndex() {
        return index;
    }

}
