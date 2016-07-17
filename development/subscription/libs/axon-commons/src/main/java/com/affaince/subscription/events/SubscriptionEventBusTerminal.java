package com.affaince.subscription.events;

import com.affaince.subscription.query.view.CommonView;
import org.axonframework.domain.EventMessage;
import org.axonframework.domain.MetaData;
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
import java.util.UUID;

import static org.springframework.integration.jms.JmsHeaders.TYPE;

/**
 * Created by mandark on 05-08-2015.
 */
public class SubscriptionEventBusTerminal implements EventBusTerminal {
    private static final int DEAULT_TIMEOUT_IN_MILLISECONDS = 100;
    private static final String PAYLOAD_TYPE_NAME = "payloadTypeName";
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
                System.out.println("@@@@Inside EventMessageTerminal publish" + event);
                //Map<String, String> metadataMap = new HashMap<>();
                System.out.println("@@@@Inside EventMessageTerminal payload type metadata" + event.getPayloadType().getName());
                /*metadataMap.put(TYPE, event.getPayloadType().getName());
                metadataMap.put(MetadataFilter.FLOW_ID, UUID.randomUUID().toString());
                metadataMap.put(MetadataFilter.TIMESTAMP, event.getTimestamp().toString());*/
                //TODO: Verify if this is proper place to create metadata and flows. I think it should be at first command creation.
                CommonView commonView = new CommonView(UUID.randomUUID().toString(), "someFlowName");
                Map<String, Object> items = new HashMap<>();
                items.put(TYPE, event.getPayloadType().getName());
                items.put(CommonView.METADATA, commonView);
                MetaData metadata = new MetaData(items);
                event = event.andMetaData(metadata);
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
                System.out.println("@@@@Inside EventMessageTerminal onClusterCreated" + message);
                try {
                    EventMessageReader reader = new EventMessageReader(new DataInputStream(new ByteArrayInputStream(
                            (byte[]) message.getPayload())), serializer);
                    cluster.publish(reader.readEventMessage());
                } catch (UnknownSerializedTypeException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
