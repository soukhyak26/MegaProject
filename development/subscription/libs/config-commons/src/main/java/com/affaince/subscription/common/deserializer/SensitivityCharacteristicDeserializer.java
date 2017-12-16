package com.affaince.subscription.common.deserializer;

import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class SensitivityCharacteristicDeserializer extends JsonDeserializer<SensitivityCharacteristic> {
    @Override
    public SensitivityCharacteristic deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            int characteristicNumber = jsonParser.getIntValue();
            for (SensitivityCharacteristic sensitivityCharacteristic : SensitivityCharacteristic.values()) {
                if (sensitivityCharacteristic.getCharacteristicNumber() == characteristicNumber) {
                    return sensitivityCharacteristic;
                }
            }
        }
        return null;

    }

}
