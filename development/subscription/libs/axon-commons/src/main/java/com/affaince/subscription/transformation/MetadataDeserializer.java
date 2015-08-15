package com.affaince.subscription.transformation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.axonframework.domain.MetaData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandark on 05-08-2015.
 */
public class MetadataDeserializer extends JsonDeserializer<MetaData> {
    @Override
    public MetaData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonDeserializer deserializer = deserializationContext.findRootValueDeserializer(deserializationContext.getTypeFactory().constructMapType(HashMap.class, String.class, String.class));
        return MetaData.from((Map) deserializer.deserialize(jsonParser, deserializationContext));
    }
}
