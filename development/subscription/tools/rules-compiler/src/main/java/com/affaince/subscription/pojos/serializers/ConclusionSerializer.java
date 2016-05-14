package com.affaince.subscription.pojos.serializers;

import com.affaince.subscription.pojos.Conclusion;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ConclusionSerializer extends JsonSerializer<Conclusion> {
    @Override
    public void serialize(Conclusion conclusion,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
       // jsonGenerator.writeString(conclusion.getUnit());
       // jsonGenerator.writeNumber(conclusion.getBenefitValue());
    }
}
