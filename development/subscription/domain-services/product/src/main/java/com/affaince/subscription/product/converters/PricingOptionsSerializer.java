package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.PricingOptions;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class PricingOptionsSerializer extends JsonSerializer<PricingOptions> {

    @Override
    public void serialize(PricingOptions pricingOptions, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName("pricingOptions");
        generator.writeNumber(pricingOptions.getPricingOptionValue());
        generator.writeEndObject();
    }

}
