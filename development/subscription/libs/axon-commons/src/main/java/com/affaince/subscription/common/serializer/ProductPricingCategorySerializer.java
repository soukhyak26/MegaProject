package com.affaince.subscription.common.serializer;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class ProductPricingCategorySerializer extends JsonSerializer<ProductPricingCategory> {

    @Override
    public void serialize(ProductPricingCategory productPricingCategory, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("productPricingCategory");
        generator.writeNumber(productPricingCategory.getIndex());
        generator.writeEndObject();
    }

}
