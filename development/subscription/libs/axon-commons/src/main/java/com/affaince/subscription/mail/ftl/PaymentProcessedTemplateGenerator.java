package com.affaince.subscription.mail.ftl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by anayonkar on 10/7/16.
 */
public class PaymentProcessedTemplateGenerator {
    public static String generateEmailMessage(String subscriberID, double paymentAmount, LocalDate paymentDate) {
        try {
            Template template = TemplateFactory.getTemplate("templates/PaymentProcessed.ftl");
            Map input = generateInputMap(subscriberID, paymentAmount, paymentDate);
            Writer stringWriter = new StringWriter();
            template.process(input, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Object> generateInputMap(String subscriberID, double paymentAmount, LocalDate paymentDate) {
        Map<String, Object> input = new HashMap<>();
        input.put("subscriberID", subscriberID);
        input.put("paymentAmount", paymentAmount);
        input.put("paymentDate", paymentDate);
        return input;
    }

}
