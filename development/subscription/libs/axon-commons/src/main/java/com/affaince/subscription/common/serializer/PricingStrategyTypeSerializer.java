package com.affaince.subscription.common.serializer;

import com.affaince.subscription.common.type.PricingStrategyType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class PricingStrategyTypeSerializer extends JsonSerializer<PricingStrategyType> {
    @Override
    public void serialize(PricingStrategyType pricingStrategyType, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName("strategyType");
        generator.writeNumber(pricingStrategyType.getStrategyType());
        generator.writeEndObject();
    }
}
