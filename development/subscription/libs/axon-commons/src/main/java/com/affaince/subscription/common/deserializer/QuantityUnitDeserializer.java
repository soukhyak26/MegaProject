package com.affaince.subscription.common.deserializer;

import com.affaince.subscription.common.type.QuantityUnit;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class QuantityUnitDeserializer extends JsonDeserializer<QuantityUnit> {
    @Override
    public QuantityUnit deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            String quantityUnitValue = jsonParser.getText().trim();
            for (QuantityUnit unit : QuantityUnit.values()) {
                if (unit.getUnitName().equals(quantityUnitValue)) {
                    return unit;
                }
            }
        }
        return null;
    }

}
