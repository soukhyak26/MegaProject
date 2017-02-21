package com.affaince.subscription.common.deserializer;

import com.affaince.subscription.common.type.EntityStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class EntityStatusDeserializer extends JsonDeserializer<EntityStatus> {
    @Override
    public EntityStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        EntityStatus entityStatus = EntityStatus.valueOf(jsonParser.getIntValue());
        if (entityStatus != null) {
            return entityStatus;
        }
        throw new JsonMappingException("invalid value for type, must be '0','1' or '2'");

    }
}
