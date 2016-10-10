package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.PricingChoiceType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mandar on 08-10-2016.
 */
public class PricingChoiceTypeSerializer extends JsonSerializer<PricingChoiceType> {
    @Override
    public void serialize(PricingChoiceType pricingChoiceType, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("pricingChoiceType");
        generator.writeNumber(pricingChoiceType.getChoiceValue());
        generator.writeEndObject();
    }
}
