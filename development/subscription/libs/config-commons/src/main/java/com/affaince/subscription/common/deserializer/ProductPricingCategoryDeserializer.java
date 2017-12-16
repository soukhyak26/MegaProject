package com.affaince.subscription.common.deserializer;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class ProductPricingCategoryDeserializer extends JsonDeserializer<ProductPricingCategory> {
    @Override
    public ProductPricingCategory deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ProductPricingCategory productPricingCategory = ProductPricingCategory.valueOf(jsonParser.getIntValue());
        if (productPricingCategory != null) {
            return productPricingCategory;
        }
        throw new JsonMappingException("invalid value for type, must be '0','1' or '2'");
    }
}
