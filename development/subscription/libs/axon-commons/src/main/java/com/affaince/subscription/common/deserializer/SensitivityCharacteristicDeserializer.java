package com.affaince.subscription.common.deserializer;

import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class SensitivityCharacteristicDeserializer extends JsonDeserializer<SensitivityCharacteristic> {
    @Override
    public SensitivityCharacteristic deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        if (node == null) {
            return null;
        }
        int value = node.intValue();
        return SensitivityCharacteristic.valueOf(value);
    }

}
