package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.PricingChoiceType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by mandar on 08-10-2016.
 */
public class PricingChoiceTypeDeserializer extends JsonDeserializer<PricingChoiceType> {
    @Override
    public PricingChoiceType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            int choiceValue = jsonParser.getIntValue();
            for (PricingChoiceType pricingChoiceType : PricingChoiceType.values()) {
                if (pricingChoiceType.getChoiceValue() == choiceValue) {
                    return pricingChoiceType;
                }
            }
        }
        return null;
    }
}
