package com.affaince.subscription.common.serializer;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class ProductForecastStatusSerializer extends JsonSerializer<ProductForecastStatus> {
    @Override
    public void serialize(ProductForecastStatus productForecastStatus, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {
        generator.writeStartObject();
        generator.writeFieldName("forecastStatusValue");
        generator.writeNumber(productForecastStatus.getForecastStatusValue());
        generator.writeEndObject();
    }

}
