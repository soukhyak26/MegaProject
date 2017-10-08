package com.affaince.subscription.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.serialization.RevisionResolver;
import org.axonframework.serialization.SerializedType;
import org.axonframework.serialization.json.JacksonSerializer;

import java.util.Map;

/**
 * Created by rahul on 6/6/17.
 */
public class AxonJacksonSerializer extends JacksonSerializer {
    private Map<String, String> types;

    public AxonJacksonSerializer(ObjectMapper objectMapper, RevisionResolver revisionResolver, Map<String, String> types) {
        super(objectMapper, revisionResolver);
        this.types = types;
    }

    public String resolveClassName(SerializedType serializedType) {
        String name = serializedType.getName();
        String result = types.get(name);
        return result == null ? name : result;
    }

    public Map<String, String> getTypes() {
        return types;
    }
}
