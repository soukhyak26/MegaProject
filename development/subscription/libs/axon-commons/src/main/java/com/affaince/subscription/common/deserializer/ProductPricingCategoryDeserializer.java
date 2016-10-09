package com.affaince.subscription.common.deserializer;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class ProductPricingCategoryDeserializer extends JsonDeserializer<ProductPricingCategory> {
    @Override
    public ProductPricingCategory deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            int index = jsonParser.getIntValue();
            for (ProductPricingCategory category : ProductPricingCategory.values()) {
                if (category.getIndex() == index) {
                    return category;
                }
            }
        }
        return null;
    }
}
