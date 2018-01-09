package com.affiance.prediction.app;

import com.affaince.subscription.common.publish.invoker.EventPublishInvoker;
import com.affaince.subscription.common.vo.EntityHistoryPacket;
import com.affiance.prediction.event.ForecastCreatedEvent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * Created by mandar on 8/15/2017.
 */
@SpringBootApplication
@EnableAutoConfiguration
@PropertySource({"classpath:Application.properties"})
@EnableBinding(Sink.class)
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    @Autowired
    private EventPublishInvoker eventPublishInvoker;
    public static void main(String[] args) {
        SpringApplication.run(
                Application.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void listen(String data) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@IN Sink: " + data);
        // Post message to the message queue named "ForecastOutputQueue"
        System.out.println("Received <" + data + ">");
        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JodaModule());
        try {
            EntityHistoryPacket entityHistoryPacket=mapper.readValue(data,new TypeReference<EntityHistoryPacket>(){});
            eventPublishInvoker.invoke(new ForecastCreatedEvent(entityHistoryPacket.getEntityType(),entityHistoryPacket.getEntityId(),entityHistoryPacket.getDataFrameVOs(),entityHistoryPacket.getEntityMetadata(),entityHistoryPacket.getForecastDate()));
        } catch (IOException e) {
            LOGGER.error("Failed to receive forecast", e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Published event");
    }

/*
    @StreamListener(Sink.INPUT)
    public void listen(String data){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@IN Sink: "+ data);
    }
*/

}