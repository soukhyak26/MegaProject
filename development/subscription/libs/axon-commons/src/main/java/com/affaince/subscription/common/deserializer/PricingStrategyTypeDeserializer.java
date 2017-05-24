package com.affaince.subscription.common.deserializer;

import com.affaince.subscription.common.type.PricingOptions;
import com.affaince.subscription.common.type.PricingStrategyType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class PricingStrategyTypeDeserializer extends JsonDeserializer<PricingStrategyType> {
/*
    @Override
    public PricingStrategyType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PricingStrategyType pricingStrategyType=PricingStrategyType.valueOf(jsonParser.getIntValue());
        if( pricingStrategyType !=null){
            return pricingStrategyType;

        }
        throw new JsonMappingException("invalid value for type, must be '0','1' or '2'");
    }
*/

    @Override
    public PricingStrategyType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            int pricingStrategyTypeValue = jsonParser.getIntValue();
            for (PricingStrategyType pricingStrategyType : PricingStrategyType.values()) {
                if (pricingStrategyType.getStrategyType() == pricingStrategyTypeValue) {
                    return pricingStrategyType;
                }
            }
        }
        return null;
    }


}
