package com.affaince.subscription.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by rbsavaliya on 19-12-2015.
 */
@Aspect
@Component
public class LoggingAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    //@Before("within (com.affaince.subscription.subscriber.query.listener.*)")
    @Around("@annotation (org.axonframework.eventhandling.annotation.EventHandler) || @annotation (org.axonframework.commandhandling.annotation.CommandHandler)")
    public void haandlerAdvice (ProceedingJoinPoint joinPoint) throws Throwable {
        AspectLoggingObject aspectLoggingObject = new AspectLoggingObject(
                joinPoint.getTarget().getClass().getCanonicalName(),
                joinPoint.getArgs()[0].getClass().getCanonicalName(),
                joinPoint.getArgs()[0]
        );
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(aspectLoggingObject));
        joinPoint.proceed ();
    }

    private class AspectLoggingObject {
        private String handlerName;
        private String handledType;
        private Object value;

        public AspectLoggingObject(String handlerName, String handledType, Object value) {
            this.handlerName = handlerName;
            this.handledType = handledType;
            this.value = value;
        }

        public AspectLoggingObject() {
        }

        public String getHandlerName() {
            return handlerName;
        }

        public void setHandlerName(String handlerName) {
            this.handlerName = handlerName;
        }

        public String getHandledType() {
            return handledType;
        }

        public void setHandledType(String handledType) {
            this.handledType = handledType;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
