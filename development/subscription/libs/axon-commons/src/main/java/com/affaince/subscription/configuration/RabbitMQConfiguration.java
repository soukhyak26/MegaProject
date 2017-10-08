package com.affaince.subscription.configuration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.axonframework.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.RoutingKeyResolver;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by rsavaliya on 10/1/16.
 */
@Configuration
@ConditionalOnProperty(name = "subscription.RabbitMQConfiguration", havingValue = "true", matchIfMissing = false)
public class RabbitMQConfiguration {

    @Resource(name = "types")
    Map<String, String> types;

    @Bean
    public String exchange(@Value("${axon.eventBus.exchangeName}") String exchangeName,
                           @Value("${subscription.rabbitmq.exchange.host}") String host,
                           @Value("${subscription.rabbitmq.queue}") String queueName,
                           @Value("${asyncCluster.identifier}") String asyncClusterIdentifier) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, "topic", true);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueDeclare(asyncClusterIdentifier, true, false, false, null);
        System.out.println("\n\t\t\t\t******************************\n\t\t\t\t"
                + "@@@@@@Types: " + types
                + "\n\t\t\t\t******************************\n");
        for (String bindingKey : types.keySet()) {
            channel.queueBind(queueName, exchangeName, bindingKey);
            channel.queueBind(asyncClusterIdentifier, exchangeName, bindingKey);
        }
        return exchangeName;
    }

    @Bean
    public RoutingKeyResolver routingKeyResolver() {
        return new EventTypeRoutingKeyResolver();
    }


    @Bean
    public AMQPMessageConverter messageConverter(Serializer serializer, RoutingKeyResolver routingKeyResolver) {
        return new DefaultAMQPMessageConverter(serializer, routingKeyResolver, true);
    }

}
