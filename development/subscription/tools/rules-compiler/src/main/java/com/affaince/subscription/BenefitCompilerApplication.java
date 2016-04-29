package com.affaince.subscription;

import com.affaince.subscription.compiler.BenefitCompiler;
import com.affaince.subscription.compiler.Compiler;
import com.affaince.subscription.pojos.RuleSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class BenefitCompilerApplication {
    public static void main(String[] args) {
        BenefitCompiler compiler = new BenefitCompiler();
        //RuleSet ruleSet = compiler.compile("if -(A + 2) > 0.5 then ;");
        compiler.compile("given money_convert is 1000 currency = 1 point and period_convert is 1 month = 1 point configure as total_revenue/money_convert + total_period/period_convert offer 1 point = 10 currency discount apply when payment mode = 100 percent advance payment deposit with each delivery;");
        // JSON serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

       /* String jsonString = null;
        try {
            //jsonString = mapper.writeValueAsString(ruleSet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);*/
    }
}
