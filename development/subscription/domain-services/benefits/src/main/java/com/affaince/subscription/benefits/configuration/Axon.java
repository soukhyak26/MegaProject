package com.affaince.subscription.benefits.configuration;

import com.affaince.subscription.benefits.command.domain.Benefit;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Configuration
@EnableJms
public class Axon extends RabbitMQConfiguration {

    @Bean
    public Repository<Benefit> createRepository(DisruptorCommandBus commandBus) {

        Repository<Benefit> repository = commandBus.createRepository(new GenericAggregateFactory<>(Benefit.class));
        return repository;
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
        }};
    }
}
