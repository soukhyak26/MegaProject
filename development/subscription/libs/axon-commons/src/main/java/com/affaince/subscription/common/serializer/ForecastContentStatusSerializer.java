package com.affaince.subscription.common.serializer;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class ForecastContentStatusSerializer extends JsonSerializer<ForecastContentStatus> {
    @Override
    public void serialize(ForecastContentStatus forecastContentStatus, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("forecastContentStatus");
        generator.writeNumber(forecastContentStatus.getForecastStatusValue());
        generator.writeEndObject();
    }

}
