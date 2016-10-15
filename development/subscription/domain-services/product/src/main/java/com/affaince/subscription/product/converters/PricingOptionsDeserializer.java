package com.affaince.subscription.product.converters;

import com.affaince.subscription.product.vo.PricingOptions;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by mandar on 09-10-2016.
 */
public class PricingOptionsDeserializer extends JsonDeserializer<PricingOptions> {

    @Override
    public PricingOptions deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            int pricingOptionValue = jsonParser.getIntValue();
            for (PricingOptions pricingOptions : PricingOptions.values()) {
                if (pricingOptions.getPricingOptionValue() == pricingOptionValue) {
                    return pricingOptions;
                }
            }
        }
        return null;
    }


}
