package com.affaince.subscription.subscriber.configuration;

import com.affaince.subscription.common.idconverter.DeliveryIdReaderConverter;
import com.affaince.subscription.common.idconverter.DeliveryIdWriterConverter;
import com.affaince.subscription.common.idconverter.ProductVersionIdReaderConverter;
import com.affaince.subscription.common.idconverter.ProductVersionIdWriterConverter;
import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.SubscriptionRule;
import com.affaince.subscription.subscriber.command.event.*;
import com.affaince.subscription.subscriber.services.benefit.state.ApplicabilityState;
import com.affaince.subscription.subscriber.services.benefit.state.BenefitCalculationState;
import com.affaince.subscription.subscriber.services.benefit.state.EligibilityState;
import com.affaince.subscription.subscriber.services.benefit.state.PointConversionState;
import com.mongodb.Mongo;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public Repository<SubscriptionRule> createBasketRuleRepository(DisruptorCommandBus commandBus) {
        Repository<SubscriptionRule> repository = commandBus.createRepository(new GenericAggregateFactory<>(SubscriptionRule.class));
        return repository;
    }

    @Bean
    public MongoDbFactory mongoDbFactory(Mongo mongo, @Value("${view.db.name}") String dbName) throws Exception {
        return new SimpleMongoDbFactory(mongo, dbName);
    }

    @Bean
    public CustomConversions customConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new DeliveryIdReaderConverter());
        converters.add(new DeliveryIdWriterConverter());
        return new CustomConversions(converters);
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(Mongo mongo, MongoDbFactory mongoDbFactory) throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions());
        return converter;
    }

    @Bean
    public BenefitCalculationState benefitCalculationState () {
        BenefitCalculationState benefitCalculationState = new EligibilityState(
                new PointConversionState(
                        new ApplicabilityState(null)
                )
        );
        return benefitCalculationState;
    }

    @Bean(name = "types")
    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.subscriber.command.event.*", "");
            put("com.affaince.subscription.payment.command.event.PaymentSchemeCreatedEvent", PaymentSchemeCreatedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.paymentreceipt.PaymentReceivedEvent", PaymentReceivedFromSourceEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.forecast.SubscriberForecastCreatedEvent", SubscriberForecastCreatedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.forecast.SubscriptionForecastCreatedEvent", SubscriptionForecastCreatedEvent.class.getName());
            put("com.affaince.subscription.benefits.command.event.BenefitAddedEvent", BenefitAddedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.OfferedPriceChangedEvent", OfferedPriceChangedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ProductActivatedEvent", ProductActivatedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.OpeningPriceOrPercentRegisteredEvent", OpeningPriceOrPercentRegisteredEvent.class.getName());
        }};
    }
}
