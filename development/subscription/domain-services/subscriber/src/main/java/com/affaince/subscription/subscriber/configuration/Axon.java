package com.affaince.subscription.subscriber.configuration;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.subscriber.command.domain.BasketRule;
import com.affaince.subscription.subscriber.command.domain.LatestPriceBucket;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.event.BenefitAddedEvent;
import com.affaince.subscription.subscriber.command.event.OfferedPriceChangedEvent;
import com.affaince.subscription.subscriber.command.event.PaymentReceivedFromSourceEvent;
import com.affaince.subscription.subscriber.command.event.ProductActivatedEvent;
import com.mongodb.Mongo;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Configuration
public class Axon extends Default {

    @Bean
    public Repository<Subscriber> createRepository(DisruptorCommandBus commandBus) {

        Repository<Subscriber> repository = commandBus.createRepository(new GenericAggregateFactory<>(Subscriber.class));
        return repository;
    }

    @Bean
    public Repository<BasketRule> createBasketRuleRepository(DisruptorCommandBus commandBus) {
        Repository<BasketRule> repository = commandBus.createRepository(new GenericAggregateFactory<>(BasketRule.class));
        return repository;
    }

    @Bean
    public Repository<LatestPriceBucket> createLatestPriceBucketRepository(DisruptorCommandBus commandBus) {
        Repository<LatestPriceBucket> repository = commandBus.createRepository(new GenericAggregateFactory<>(LatestPriceBucket.class));
        return repository;
    }

    @Bean
    public MongoDbFactory mongoDbFactory(Mongo mongo, @Value("${view.db.name}") String dbName) throws Exception {
        return new SimpleMongoDbFactory(mongo, dbName);
    }

    @Bean(name = "types")
    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.subscriber.command.event.*", "");
            put("com.affaince.subscription.integration.command.event.paymentreceipt.PaymentReceivedEvent", PaymentReceivedFromSourceEvent.class.getName());
            put("com.affaince.subscription.benefits.command.event.BenefitAddedEvent", BenefitAddedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.OfferedPriceChangedEvent", OfferedPriceChangedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ProductActivatedEvent", ProductActivatedEvent.class.getName());
        }};
    }
}
