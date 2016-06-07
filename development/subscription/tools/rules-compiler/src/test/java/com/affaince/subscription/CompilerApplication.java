package com.affaince.subscription;

import com.affaince.subscription.compiler.spike.Compiler;
import com.affaince.subscription.pojos.RuleSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CompilerApplication {
    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        //RuleSet ruleSet = compiler.compile("if -(A + 2) > 0.5 then ;");
        RuleSet ruleSet = compiler.compile("if basketPrice > 3000 and cycle = 5 then 3 percent;");
        // JSON serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(ruleSet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }
}
