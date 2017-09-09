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
                "(totalSubscriptionAmount/subscriptionValue/subscriptionPeriod)*totalSubscriptionPeriod " +
                "eligible when " +
                "totalSubscriptionAmount = 1000 " +
                "and (currentSubscriptionPeriod > 50 or totalLoyaltyPeriod > 36) " +
                "apply as " +
                "1/2N, 3/4N delivery in 40, 60 proportion;");
        // JSON serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(ruleSet);
            Rule rule = mapper.readValue(jsonString, Rule.class);
            System.out.println(rule.getBenefitDistributionParameters().getProportionValues());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }
}
