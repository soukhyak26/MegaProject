package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.PricingStrategyType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class PricingStrategyTypeSerializer extends JsonSerializer<PricingStrategyType> {
    @Override
    public void serialize(PricingStrategyType pricingStrategyType, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("strategyType");
        generator.writeNumber(pricingStrategyType.getStrategyType());
        generator.writeEndObject();
    }
}
