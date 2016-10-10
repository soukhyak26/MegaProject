package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.PricingStrategyType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class PricingStrategyTypeDeserializer extends JsonDeserializer<PricingStrategyType> {
    @Override
    public PricingStrategyType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            int strategyType = jsonParser.getIntValue();
            for (PricingStrategyType pricingStrategyType : PricingStrategyType.values()) {
                if (pricingStrategyType.getStrategyType() == strategyType) {
                    return pricingStrategyType;
                }
            }
        }
        return null;
    }

}
