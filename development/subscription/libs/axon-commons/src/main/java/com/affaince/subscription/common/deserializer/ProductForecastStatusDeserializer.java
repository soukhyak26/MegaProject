package com.affaince.subscription.common.deserializer;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class ProductForecastStatusDeserializer extends JsonDeserializer<ProductForecastStatus> {
    @Override
    public ProductForecastStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            int forecastStatusValue = jsonParser.getIntValue();
            for (ProductForecastStatus forecastStatus : ProductForecastStatus.values()) {
                if (forecastStatus.getForecastStatusValue() == forecastStatusValue) {
                    return forecastStatus;
                }
            }
        }
        return null;
    }
}
