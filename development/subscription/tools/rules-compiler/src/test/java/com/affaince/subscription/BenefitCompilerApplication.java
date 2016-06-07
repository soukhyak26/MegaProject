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
                    "money_convert is 1000 currency = 1 point " +
                    "and period_convert is 1 month = 1 point " +
                "configure as " +
                    "total_revenue/money_convert + total_period/period_convert " +
                "eligible when " +
                    "total_subscription_amount = 1000 " +
                    "and (current_subscription_period > 50 or total_loyalty_period > 36) " +
                "offer as " +
                    "1 point = 3 currency " +
                "apply when " +
                    "payment_mode = 100 percent_advance deposit with each delivery;");
        // JSON serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(ruleSet);
            Rule rule = mapper.readValue(jsonString, Rule.class);
            System.out.println(rule.getApplicability());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }
}
