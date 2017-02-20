package com.affaince.subscription.common.serializer;

import com.affaince.subscription.common.type.EntityStatus;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class EntityStatusSerializer extends JsonSerializer<EntityStatus> {

    @Override
    public void serialize(EntityStatus entityStatus, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("entityStatus");
        generator.writeNumber(entityStatus.getStatusCode());
        generator.writeEndObject();
    }

}
