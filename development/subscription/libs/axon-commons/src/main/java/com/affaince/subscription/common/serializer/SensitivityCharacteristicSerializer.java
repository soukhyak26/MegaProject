package com.affaince.subscription.common.serializer;

import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class SensitivityCharacteristicSerializer extends JsonSerializer<SensitivityCharacteristic> {
    @Override
    public void serialize(SensitivityCharacteristic sensitivityCharacteristic, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("characteristicNumber");
        generator.writeNumber(sensitivityCharacteristic.getCharacteristicNumber());
        generator.writeEndObject();
    }

}
