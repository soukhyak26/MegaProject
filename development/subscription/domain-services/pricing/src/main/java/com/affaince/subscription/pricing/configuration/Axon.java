package com.affaince.subscription.pricing.configuration;

import com.affaince.subscription.configuration.Default;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Configuration
public class Axon extends Default {


    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
        }};
    }
}
