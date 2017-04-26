package com.affaince.subscription.common.deserializer;

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
    @Override
    public PricingStrategyType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PricingStrategyType pricingStrategyType=PricingStrategyType.valueOf(jsonParser.getIntValue());
        if( pricingStrategyType !=null){
            return pricingStrategyType;

        }
        throw new JsonMappingException("invalid value for type, must be '0','1' or '2'");
    }


}
