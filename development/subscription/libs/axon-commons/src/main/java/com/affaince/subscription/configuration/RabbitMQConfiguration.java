package com.affaince.subscription.configuration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.axonframework.eventhandling.EventBusTerminal;
import org.axonframework.eventhandling.amqp.AMQPMessageConverter;
import org.axonframework.eventhandling.amqp.DefaultAMQPMessageConverter;
import org.axonframework.eventhandling.amqp.RoutingKeyResolver;
import org.axonframework.eventhandling.amqp.spring.ListenerContainerLifecycleManager;
import org.axonframework.eventhandling.amqp.spring.SpringAMQPTerminal;
import org.axonframework.serializer.Serializer;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by rsavaliya on 10/1/16.
 */
/*@Configuration
@EnableAutoConfiguration*/
public class RabbitMQConfiguration extends Default {

    @Bean
    public RabbitAdmin rabbitAdmin(CachingConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public Queue queue(@Value("${subscription.rabbitmq.queue}") String queueName) {
        return new Queue(queueName);
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public String exchange(@Value("${axon.eventBus.exchangeName}") String exchangeName,
                           @Value("${subscription.rabbitmq.exchange.host}") String host,
                           @Value("${subscription.rabbitmq.queue}") String queueName) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, "topic", true);
        channel.queueDeclare(queueName, true, false, false, null);
        System.out.println("\n\t\t\t\t******************************\n\t\t\t\t"
                + "@@@@@@Types: " + types()
                + "\n\t\t\t\t******************************\n");
        for (String bindingKey : types().keySet()) {
            channel.queueBind(queueName, exchangeName, bindingKey);
        }
        //channel.queueBind(queueName, exchangeName, "com.affaince.notification.events.PaymentProcessedEvent");
        return exchangeName;
    }

    @Bean
    public RoutingKeyResolver routingKeyResolver() {
        return new EventTypeRoutingKeyResolver();
    }

    @Bean
    public EventBusTerminal eventBusTerminal(String exchange, ListenerContainerLifecycleManager listenerContainerLifecycleManager,
                                             Serializer serializer, CachingConnectionFactory connectionFactory,
                                             RoutingKeyResolver routingKeyResolver, AMQPMessageConverter messageConverter) {
        SpringAMQPTerminal springAMQPTerminal = new SpringAMQPTerminal();
        springAMQPTerminal.setExchangeName(exchange);
        springAMQPTerminal.setListenerContainerLifecycleManager(listenerContainerLifecycleManager);
        springAMQPTerminal.setSerializer(serializer);
        springAMQPTerminal.setRoutingKeyResolver(routingKeyResolver);
        springAMQPTerminal.setConnectionFactory(connectionFactory);
        springAMQPTerminal.setMessageConverter(messageConverter);
        return springAMQPTerminal;
    }

    @Bean
    public AMQPMessageConverter messageConverter(Serializer serializer, RoutingKeyResolver routingKeyResolver) {
        return new DefaultAMQPMessageConverter(serializer, routingKeyResolver, true);
    }

    @Bean
    public ListenerContainerLifecycleManager listenerContainerLifecycleManager(CachingConnectionFactory connectionFactory) {
        ListenerContainerLifecycleManager listenerContainerLifecycleManager = new ListenerContainerLifecycleManager();
        listenerContainerLifecycleManager.setConnectionFactory(connectionFactory);
        return listenerContainerLifecycleManager;
    }
}
