package com.affaince.subscription.mail.ftl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by anayonkar on 10/7/16.
 */
public class TemplateFactory {
    public static Template getTemplate(String ftlFile) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassLoaderForTemplateLoading(FTLDemo.class.getClassLoader(), "/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template template = cfg.getTemplate(ftlFile);
        return template;
    }
}
