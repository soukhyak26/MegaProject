package com.affaince.subscription.integration.jms;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.common.vo.EntityHistoryPacket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by mandar on 9/9/2017.
 */
@Component
public class ForecastReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastReceiver.class);
    @Autowired
    EventCreationResolver eventCreationResolver;
    @Autowired
    GenericEventPublisher genericEventPublisher;
    @JmsListener(destination = "ForecastOutputQueue", containerFactory = "myFactory")
    public void receiveMessage(String forecastString) {
        System.out.println("Received <" + forecastString + ">");
        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JodaModule());
        try {
            EntityHistoryPacket entityHistoryPacket=mapper.readValue(forecastString,new TypeReference<EntityHistoryPacket>(){});
            Object event = eventCreationResolver.resolveEventForADomainEntity(entityHistoryPacket.getEntityType(),entityHistoryPacket.getEntityId(),mapper.writeValueAsString(entityHistoryPacket.getDataFrameVOs()),entityHistoryPacket.getForecastDate());
            genericEventPublisher.publish(event);
        } catch (IOException e) {
            LOGGER.error("Failed to receive forecast", e.getStackTrace());
        }
        System.out.println("Published event");
    }
}
