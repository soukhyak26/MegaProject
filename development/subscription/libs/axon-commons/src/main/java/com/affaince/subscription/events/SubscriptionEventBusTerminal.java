package com.affaince.subscription.events;

import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.Cluster;
import org.axonframework.eventhandling.EventBusTerminal;
import org.axonframework.eventhandling.io.EventMessageReader;
import org.axonframework.eventhandling.io.EventMessageWriter;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.UnknownSerializedTypeException;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandark on 05-08-2015.
 */
public class SubscriptionEventBusTerminal implements EventBusTerminal {
    private static final int DEAULT_TIMEOUT_IN_MILLISECONDS = 100;
    private static final String PAYLOAD_TYPE_NAME= "payloadTypeName" ;
    private final Serializer serializer;
    private final SubscribableChannel channel;
    private final Cluster cluster;

    public SubscriptionEventBusTerminal(Serializer serializer, SubscribableChannel channel, Cluster cluster) {
        this.serializer = serializer;
        this.channel = channel;
        this.cluster = cluster;
    }

    @Override
    public void publish(EventMessage... eventMessages) {
        for (EventMessage event : eventMessages) {
            try {
                Map<String, String> metadataMap = new HashMap<>();
                metadataMap.put(PAYLOAD_TYPE_NAME, event.getPayloadType().getName());
                event = event.andMetaData(metadataMap);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                EventMessageWriter out = new EventMessageWriter(new DataOutputStream(outputStream), serializer);
                out.writeEventMessage(event);
                if (!channel.send(new GenericMessage<Object>(outputStream.toByteArray(), event.getMetaData()), DEAULT_TIMEOUT_IN_MILLISECONDS)) {
                    throw new EventPublicationFailedException("Timed out waiting to publish a event of type " + event.getPayloadType());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onClusterCreated(final Cluster cluster) {
        channel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                try {
                    EventMessageReader reader = new EventMessageReader(new DataInputStream(new ByteArrayInputStream(
                            (byte[]) message.getPayload())), serializer);
                    cluster.publish(reader.readEventMessage());
                } catch (UnknownSerializedTypeException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
