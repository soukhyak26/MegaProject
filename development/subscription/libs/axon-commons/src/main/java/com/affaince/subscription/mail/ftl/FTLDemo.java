package com.affaince.subscription.mail.ftl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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
public class FTLDemo {
    /*public static void main(String[] args) throws IOException, TemplateException {
        *//*Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassLoaderForTemplateLoading(FTLDemo.class.getClassLoader(), "/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map<String, Object> input = new HashMap<>();
        input.put("subscriberID", "username");
        input.put("paymentAmount", "1234");
        input.put("paymentDate", "01-01-2016");
        Template template = cfg.getTemplate("templates/PaymentProcessed.ftl");
        Writer stringWriter = new StringWriter();
        template.process(input, stringWriter);
        System.out.println(stringWriter);*//*
        System.out.println(PaymentProcessedTemplateGenerator.generateTemplate("abcd", 1234, LocalDate.now()));
    }*/
}
