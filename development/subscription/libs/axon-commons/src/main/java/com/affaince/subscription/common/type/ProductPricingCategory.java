package com.affaince.subscription.common.type;

import com.affaince.subscription.common.deserializer.ProductPricingCategoryDeserializer;
import com.affaince.subscription.common.serializer.ProductPricingCategorySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
@JsonSerialize(using = ProductPricingCategorySerializer.class)
@JsonDeserialize(using = ProductPricingCategoryDeserializer.class)

public enum ProductPricingCategory {

    NO_COMMITMENT(0), PRICE_COMMITMENT(1), DISCOUNT_COMMITMENT(2);

    private int index;

    ProductPricingCategory(int index) {
        this.index = index;
    }

    public static ProductPricingCategory valueOf(int value) {
        for (ProductPricingCategory r : ProductPricingCategory.values()) {
            if (r.getIndex() == value) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getIndex() {
        return index;
    }

}
