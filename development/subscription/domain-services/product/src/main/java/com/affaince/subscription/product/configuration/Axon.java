package com.affaince.subscription.product.configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.ActiveMQConfiguration;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.services.forecast.*;
import com.affaince.subscription.product.services.pricing.determinator.DefaultPriceDeterminator;
import com.affaince.subscription.product.services.pricing.determinator.DemandBasedPriceDeterminator;
import com.affaince.subscription.product.services.pricing.processor.PricingStrategyDeterminator;
import com.affaince.subscription.product.services.pricing.processor.function.RegressionBasedCostFunctionProcessor;
import com.affaince.subscription.product.services.pricing.processor.function.RegressionBasedDemandFunctionProcessor;
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
@EnableAutoConfiguration(exclude = {EmbeddedServletContainerFactory.class})
@ComponentScan("com.affaince")
@EnableConfigurationProperties({Axon.HistoryMinSizeConstraints.class, Axon.HistoryMaxSizeConstraints.class})
public class Axon extends ActiveMQConfiguration {

    @Autowired
    HistoryMinSizeConstraints historyMinSizeConstraints;

    /*@Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Product product() {
        Product product = new Product();
        return product;
    }

    @Bean
    public SpringPrototypeAggregateFactory<Product> aggregateFactory() {
        SpringPrototypeAggregateFactory<Product> aggregateFactory = new SpringPrototypeAggregateFactory<Product>();
        aggregateFactory.setPrototypeBeanName("product");
        return aggregateFactory;
    }*/
    @Autowired
    HistoryMaxSizeConstraints historyMaxSizeConstraints;

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
    public MappingMongoConverter mappingMongoConverter(Mongo mongo, MongoDbFactory mongoDbFactory) throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversionsForProductVersionId());
        // converter.setCustomConversions(customConversionsForEndDate());
        return converter;
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.product.command.event.*", "");
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
    public DemandForecasterChain demandForecasterChain() {
        return new DemandForecasterChain();
    }

    @Bean
    public SimpleMovingAverageDemandForecaster smaForecaster() {
        return new SimpleMovingAverageDemandForecaster();
    }

    @Bean
    public SimpleExponentialSmoothingDemandForecaster semaForecaster() {
        return new SimpleExponentialSmoothingDemandForecaster();
    }

    @Bean
    public TripleExponentialSmoothingDemandForecaster temaForecaster() {
        return new TripleExponentialSmoothingDemandForecaster();
    }

    @Bean
    public ARIMABasedDemandForecaster arimaForecaster() {
        return new ARIMABasedDemandForecaster();
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
    DemandBasedPriceDeterminator demandBasedPriceDeterminator() {
        return new DemandBasedPriceDeterminator();
    }

    @Bean
    ProductDemandForecastBuilder productDemandForecastBuilder() {
        return new ProductDemandForecastBuilder();
    }

    @ConfigurationProperties(prefix= "forecaster.threshold_min")
    public static class HistoryMinSizeConstraints {
        private int sma;
        private int sema;
        private int tema;
        private int arima;

        public int getSma() {
            return sma;
        }

        public void setSma(int sma) {
            this.sma = sma;
        }

        public int getSema() {
            return sema;
        }

        public void setSema(int sema) {
            this.sema = sema;
        }

        public int getTema() {
            return tema;
        }

        public void setTema(int tema) {
            this.tema = tema;
        }

        public int getArima() {
            return arima;
        }

        public void setArima(int arima) {
            this.arima = arima;
        }
    }

    @ConfigurationProperties(prefix = "forecaster.threshold_max")
    public static class HistoryMaxSizeConstraints {
        private int sma;
        private int sema;
        private int tema;
        private int arima;

        public int getSma() {
            return sma;
        }

        public void setSma(int sma) {
            this.sma = sma;
        }

        public int getSema() {
            return sema;
        }

        public void setSema(int sema) {
            this.sema = sema;
        }

        public int getTema() {
            return tema;
        }

        public void setTema(int tema) {
            this.tema = tema;
        }

        public int getArima() {
            return arima;
        }

        public void setArima(int arima) {
            this.arima = arima;
        }
    }


}