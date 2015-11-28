package com.affaince.subscription.logback.common;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 28-11-2015.
 */
@Configuration
public class LogbackConfiguration {
    public String getLoggerLevel(String loggerName, LoggerContext loggerContext) {
        if (loggerName == null) {
            return null;
        }
        Logger logger = loggerContext.exists(loggerName.trim());
        if (logger == null || logger.getLevel() == null) {
            return null;
        }
        return logger.getLevel().toString();
    }

    public List<String> getLoggerList(LoggerContext loggerContext) {
        List <String> strList = new ArrayList<>();
        for (Logger logger:loggerContext.getLoggerList()) {
            strList.add(logger.getName());
        }
        return strList;
    }

    @Bean
    public DiagnosticInformation logbackDiagnosticInformation () {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List <String> loggerList = getLoggerList(loggerContext);
        Multimap <String, String> loggersByLevel = ArrayListMultimap.create();
        for (String logger: loggerList) {
            loggersByLevel.put(getLoggerLevel(logger, loggerContext), logger);
        }
        return new DiagnosticInformation() {
            @Override
            public Map<? extends String, ?> getInformation() {
                return loggersByLevel.asMap();
            }
        };
    }
}
