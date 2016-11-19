package com.affaince.subscription.pricing.configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.pricing.build.interpolate.ForecastInterpolatedSubscriptionCountFinder;
import com.affaince.subscription.pricing.build.interpolate.Interpolator;
import com.affaince.subscription.pricing.determine.PricingClient;
import com.affaince.subscription.pricing.determine.ProductPricingTrigger;
import com.affaince.subscription.pricing.determine.ProductsRetriever;
import com.mongodb.Mongo;
import org.apache.camel.CamelContext;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.axonframework.eventhandling.EventTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.apache.camel.builder.PredicateBuilder.or;

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
@EnableJms
@ComponentScan("com.affaince")
public class Axon extends Default {

    @Autowired
    private CamelContext camelContext;

    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MongoDbFactory mongoDbFactory(Mongo mongo, @Value("${view.db.name}") String dbName) throws Exception {
        return new SimpleMongoDbFactory(mongo, dbName);
    }
    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>();
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


    @Bean
    public PricingClient pricingClient() {
        return new PricingClient();
    }

    @Bean
    public ProductsRetriever productsRetriever() {
        return new ProductsRetriever();
    }


    @Bean
    public ForecastInterpolatedSubscriptionCountFinder forecastInterpolatedSubscriptionCountFinder() {
        return new ForecastInterpolatedSubscriptionCountFinder();
    }

    @Bean
    public ProductPricingTrigger productPricingTrigger() {
        return new ProductPricingTrigger();
    }

    @Bean
    public Interpolator interpolator() {
        return new Interpolator();
    }

    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() throws Exception {


                Predicate demandTrendChecker = or(body().isEqualTo(ProductDemandTrend.UPWARD), body().isEqualTo(ProductDemandTrend.DOWNWARD));
                //job for calculating pseudoActuals for each product.

                from("{{subscription.pricing.timer.expression}}")
                        .routeId("PriceDeterminator")
                        .to("bean:productsRetriever")
                        .split(body())
                        .to("bean:forecastInterpolatedSubscriptionCountFinder")
                        .to("bean:productPricingTrigger")
                        .choice()
                        .when(demandTrendChecker)
                        .to("bean:pricingClient")
                        .endChoice();

            }
        };
    }
/*
    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() {
                from("timer://foo?repeatCount=1").choice()
                        .to("bean:operatingExpenseStrategyDeterminator?decideOperatingExpenseStrategy()")
                        .when(simple("${body.operatingExpenseDistributionStrategyType}==${type:com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.DEFAULT_STRATEGY}"))
                        .to("bean:defaultOperatingExpenseDistributionDeterminator")
                        .when(simple("${body.operatingExpenseDistributionStrategyType}==${type:com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.EXTRAPOLATION_BASED_STRATEGY}"))
                        .to("bean:extraPolationBasedOperatingExpenseDistributionDeterminator")
                        .when(simple("${body.operatingExpenseDistributionStrategyType}==${type:com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.FORECAST_BASED_STRATEGY}"))
                        .to("bean:forecastBasedOperatingExpenseDistributionDeterminator")
                       // .multicast(AggregationStrategies.bean(ProductWiseDeliveryStatsAggregation.class))
                        .to ("bean:calculatePerUnitExpense")
                        .to("bean:publisher");
            }
        };
    }
*/

    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {
                System.out.println("@@@@@@@@@@@Hey Camel Started@@@@@@@@@@@");
            }
        };
    }
}
