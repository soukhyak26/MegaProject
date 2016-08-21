package com.affaince.subscription.payments.configuration;

import com.affaince.subscription.configuration.ActiveMQConfiguration;
import com.affaince.subscription.payments.command.domain.Payment;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anayonkar on 21/8/16.
 */
@Configuration
@EnableJms
public class Axon extends ActiveMQConfiguration {


    @Bean
    public Repository<Payment> createPaymentRepository(DisruptorCommandBus commandBus) {
        Repository<Payment> repository = commandBus.createRepository(new GenericAggregateFactory<>(Payment.class));
        return repository;
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{

        }};
    }

}
