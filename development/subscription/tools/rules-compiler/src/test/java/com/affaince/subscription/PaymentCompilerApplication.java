package com.affaince.subscription;

import com.affaince.subscription.compiler.paymentscheme.PaymentSchemeCompiler;
import com.affaince.subscription.pojos.PaymentExpression;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;

import java.io.IOException;

public class PaymentCompilerApplication {
    @Test
    public void test100PercentAdvancePaymentTest() throws IOException {
        PaymentSchemeCompiler compiler = new PaymentSchemeCompiler();
        PaymentExpression paymentExpression =
        compiler.compile("pay " +
                "100% of current subscription amount " +
                "on " +
                "subscription confirmation " +
                "and " +
                "pay " +
                "residual due amount " +
                "after " +
                "1/2remaining-N, 3/4remaining-N delivery " +
                "in " +
                "default proportion");
        // JSON serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(paymentExpression);
            PaymentExpression paymentExpression1 = mapper.readValue(jsonString, PaymentExpression.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }

    @Test
    public void test1DeliveryAdvancePaymentTest() throws IOException {
        PaymentSchemeCompiler compiler = new PaymentSchemeCompiler();
        PaymentExpression paymentExpression =
                compiler.compile("pay " +
                        "100% of  " +
                        "1/4N delivery on " +
                        "subscription confirmation " +
                        "and " +
                        "pay " +
                        "residual due amount " +
                        "after " +
                        "1/2N, 3/4N delivery " +
                        "in " +
                        "3,7 proportion");
        // JSON serialization
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(paymentExpression);
            PaymentExpression paymentExpression1 = mapper.readValue(jsonString, PaymentExpression.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }
}