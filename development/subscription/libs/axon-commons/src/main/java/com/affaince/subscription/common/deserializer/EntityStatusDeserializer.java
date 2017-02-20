package com.affaince.subscription.common.deserializer;

import com.affaince.subscription.common.type.EntityStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class EntityStatusDeserializer extends JsonDeserializer<EntityStatus> {
    @Override
    public EntityStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            int index = jsonParser.getIntValue();
            for (EntityStatus entityStatus : EntityStatus.values()) {
                if (entityStatus.getStatusCode() == index) {
                    return entityStatus;
                }
            }
        }
        return null;
    }
}
