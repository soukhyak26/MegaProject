package com.affaince.subscription.product.registration.configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.RabbitMQConfiguration;
import com.affaince.subscription.product.registration.command.domain.Product;
import com.affaince.subscription.product.registration.command.event.*;
import com.affaince.subscription.product.registration.services.pricing.aggregate.CoefficientAggregationStrategy;
import com.affaince.subscription.product.registration.services.pricing.determinator.DefaultPriceDeterminator;
import com.affaince.subscription.product.registration.services.pricing.determinator.DemandBasedPriceDeterminator;
import com.affaince.subscription.product.registration.services.pricing.processor.PricingStrategyDeterminator;
import com.affaince.subscription.product.registration.services.pricing.processor.function.RegressionBasedCostFunctionProcessor;
import com.affaince.subscription.product.registration.services.pricing.processor.function.RegressionBasedDemandFunctionProcessor;
import com.mongodb.Mongo;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.toolbox.AggregationStrategies;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventhandling.EventTemplate;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.jms.annotation.EnableJms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 19072015.
 */
@Configuration
@EnableJms
public class Axon extends RabbitMQConfiguration {

    @Bean
    public Repository<Product> createRepository(DisruptorCommandBus commandBus) {

        Repository<Product> repository = commandBus.createRepository(new GenericAggregateFactory<>(Product.class));
        return repository;
    }

    public
    @Bean
    MongoDbFactory mongoDbFactory(Mongo mongo, @Value("${view.db.name}") String dbName) throws Exception {
        return new SimpleMongoDbFactory(mongo, dbName);
    }


    @Bean
    public MappingMongoConverter mappingMongoConverter(Mongo mongo, MongoDbFactory mongoDbFactory) throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversionsForProductVersionId());
        return converter;
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.product.registration.command.event.*", "");
            put("com.affaince.subscription.integration.command.event.shoppingitemreceipt.ProductReceivedForRegistrationEvent", ProductRegisteredEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent", ProductStatusReceivedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.forecast.ShoppingItemForecastReceivedEvent", ProductForecastReceivedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent", ProductStatusReceivedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.ProductSubscriptionActivatedEvent", ProductSubscriptionActivatedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.DeliveryCreatedEvent", DeliveryCreatedEvent.class.getName());
            put("com.affaince.subscription.expensedistribution.event.SubscriptionSpecificOperatingExpenseCalculatedEvent", SubscriptionSpecificOperatingExpenseCalculatedEvent.class.getName());

        }};
    }

    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }

    @Bean
    public PricingStrategyDeterminator pricingStrategyDeterminator() {
        return new PricingStrategyDeterminator();
    }

    @Bean
    public DefaultPriceDeterminator defaultPriceDeterminator() {
        return new DefaultPriceDeterminator();
    }

    @Bean
    public RegressionBasedCostFunctionProcessor costFunctionProcessor (){
        return new RegressionBasedCostFunctionProcessor();
    }

    @Bean
    public RegressionBasedDemandFunctionProcessor demandFunctionProcessor (){
        return new RegressionBasedDemandFunctionProcessor();
    }

    @Bean
    DemandBasedPriceDeterminator demandBasedPriceDeterminator(){
        return new DemandBasedPriceDeterminator();
    }
    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() {
                //INT_02: retreive subscriptioable items details from main application
                from("timer://foo?repeatCount=1")
                        .to("bean:productViewRepository?method=findAll()")
                        .split(body())
                        .to("bean:pricingStrategyDeterminator?decideProductPricingStrategy(*)")
                        .choice()
                        .when(simple("${body.pricingStrategyType}==${type:com.affaince.subscription.pricing.vo.PricingStrategyType.DEFAULT_PRICING_STRATEGY}"))
                        .to("bean:priceDeterminationCriteriaComposer")
                        .to("bean:defaultPriceDeterminator")
                        .to("bean:publisher")
                        .when(simple("${body.pricingStrategyType}==${type:com.affaince.subscription.pricing.vo.PricingStrategyType.DEMAND_AND_COST_BASED_PRICING_STRATEGY}"))
                        .multicast(AggregationStrategies.bean(CoefficientAggregationStrategy.class))
                        .parallelProcessing()
                        .to("bean:costFunctionProcessor", "bean:demandFunctionProcessor")
                        .end()
                        .to("bean:demandBasedPriceDeterminator")
                        .to("bean:publisher");
            }
        };
    }

}