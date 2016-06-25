package com.affaince.subscription;

import com.affaince.subscription.compiler.BenefitCompiler;
import com.affaince.subscription.compiler.Rule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class BenefitCompilerApplication {
    public static void main(String[] args) throws IOException {
        BenefitCompiler compiler = new BenefitCompiler();
        Rule ruleSet =
        compiler.compile("given " +
                    "1000 currency and 2 month = 3 point " +
                "configure as " +
                    "() " +
                "eligible when " +
                    "total_subscription_amount = 1000 " +
                    "and (current_subscription_period > 50 or total_loyalty_period > 36) " +
                "apply as " +
                    "monthly_incremental;");
        // JSON serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(ruleSet);
            Rule rule = mapper.readValue(jsonString, Rule.class);
            System.out.println(rule.getPointConversionExpression());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }
}