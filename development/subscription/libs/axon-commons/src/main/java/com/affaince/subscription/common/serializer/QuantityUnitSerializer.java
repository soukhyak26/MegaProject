package com.affaince.subscription.common.serializer;

import com.affaince.subscription.common.type.QuantityUnit;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class QuantityUnitSerializer extends JsonSerializer<QuantityUnit> {
    @Override
    public void serialize(QuantityUnit quantityUnit, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("quantityUnit");
        generator.writeString(quantityUnit.getUnitName());
        generator.writeEndObject();
    }

}
