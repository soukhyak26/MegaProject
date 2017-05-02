package com.affaince.subscription.product.configuration;

import com.affaince.subscription.common.idconverter.ProductVersionIdReaderConverter;
import com.affaince.subscription.common.idconverter.ProductVersionIdWriterConverter;
import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.converters.*;
import com.affaince.subscription.product.factory.AggregatorFactory;
import com.affaince.subscription.product.services.aggregators.PeriodBasedAggregator;
import com.affaince.subscription.product.services.forecast.*;
import com.affaince.subscription.product.services.pricing.calculator.historybased.RegressionBasedPriceCalculator;
import com.affaince.subscription.product.services.pricing.calculator.historybased.regression.RegressionBasedDemandFunctionProcessor;
import com.affaince.subscription.product.services.pricing.determinator.DefaultPriceDeterminator;
import com.affaince.subscription.product.services.pricing.determinator.DemandBasedPriceDeterminator;
import com.affaince.subscription.product.services.pricing.processor.PricingStrategyDeterminator;
import com.affaince.subscription.product.services.pricing.processor.function.RegressionBasedCostFunctionProcessor;
import com.mongodb.Mongo;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventhandling.EventTemplate;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.jms.annotation.EnableJms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 19072015.
 */
@Configuration
@EnableJms
@EnableAutoConfiguration(exclude = {EmbeddedServletContainerFactory.class})
@ComponentScan("com.affaince")

public class Axon extends Default {


    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory() {
        return new org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory();
    }

    @Bean
    public Repository<Product> createRepository(DisruptorCommandBus commandBus) {

        Repository<Product> repository = commandBus.createRepository(new GenericAggregateFactory<>(Product.class));
        return repository;
    }

    @Bean
    public MongoDbFactory mongoDbFactory(Mongo mongo, @Value("${view.db.name}") String dbName) throws Exception {
        return new SimpleMongoDbFactory(mongo, dbName);
    }

    @Bean
    public CustomConversions customConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new ProductVersionIdReaderConverter());
        converters.add(new ProductVersionIdWriterConverter());
        converters.add(new ProductwisePriceBucketIdReaderConverter());
        converters.add(new ProductwisePricebucketIdWriterConverter());
        converters.add(new ProductwiseTaggedPriceVersionIdReaderConverter());
        converters.add(new ProductwiseTaggedPriceVersionIdWriterConverter());
        converters.add(new ProductwiseFixedExpenseIdReaderConverter());
        converters.add(new ProductwiseFixedExpenseIdWriterConverter());
        converters.add(new ProductwiseVariableExpenseIdReaderConverter());
        converters.add(new ProductwiseVariableExpenseIdWriterConverter());
        converters.add(new PriceBucketTransactionIdReaderConverter());
        converters.add(new PriceBucketTransactionIdWriterConverter());

        return new CustomConversions(converters);
    }
    @Bean
    public MappingMongoConverter mappingMongoConverter(Mongo mongo, MongoDbFactory mongoDbFactory) throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions());
        //converter.setCustomConversions(customConversionsForProductwisePriceBucketId());
        // converter.setCustomConversions(customConversionsForEndDate());
        return converter;
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.product.command.event.*", "");
            //put("com.affaince.subscription.integration.command.event.shoppingitemreceipt.ProductRegisteredEvent", ProductRegisteredEvent.class.getName());
            //evene to be recieved from main application regarding current tagged price and stock
            put("com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent", ProductStatusReceivedEvent.class.getName());
            //??
            put("com.affaince.subscription.integration.command.event.forecast.ShoppingItemForecastReceivedEvent", ProductForecastReceivedEvent.class.getName());
            //when a new subscription is added or an existing subscription is changed by subscriber then below event carry the changes from subscriber domain to product domain
            put("com.affaince.subscription.subscriber.command.event.SubscriptionActivitySummaryEvent", SubscriptionActivitySummaryEvent.class.getName());
            //put("com.affaince.subscription.subscriber.command.event.DeliveryCreatedEvent", DeliveryCreatedEvent.class.getName());
            put("com.affaince.subscription.expensedistribution.event.SubscriptionSpecificOperatingExpenseCalculatedEvent", SubscriptionSpecificOperatingExpenseCalculatedEvent.class.getName());
            put("com.affaince.subscription.expensedistribution.event.FixedExpenseUpdatedToProductEvent", FixedExpenseUpdatedToProductEvent.class.getName());
            put("com.affaince.subscription.business.command.event.ProvisionCreatedEvent", ProvisionCreatedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.SubscriptionRuleAddedEvent", SubscriptionRuleAddedEvent.class.getName());

            put("com.affaince.subscription.subscriber.command.event.DeliveryStatusAndDispatchDateUpdatedEvent", DeliveryStatusAndDispatchDateUpdatedEvent.class.getName());
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
    public RegressionBasedCostFunctionProcessor costFunctionProcessor() {
        return new RegressionBasedCostFunctionProcessor();
    }
    /*@Bean
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
    }*/

    @Bean
    public RegressionBasedDemandFunctionProcessor demandFunctionProcessor() {
        return new RegressionBasedDemandFunctionProcessor();
    }

    @Bean
    public RegressionBasedPriceCalculator regressionBasedPriceCalculator() {
        return new RegressionBasedPriceCalculator();
    }
    @Bean
    DemandBasedPriceDeterminator demandBasedPriceDeterminator() {
        return new DemandBasedPriceDeterminator();
    }

    @Bean
    ProductDemandForecastBuilder productDemandForecastBuilder() {
        return new ProductDemandForecastBuilder();
    }

    @Bean
    PeriodBasedAggregator periodBasedAggregator() {
        return new PeriodBasedAggregator();
    }

}