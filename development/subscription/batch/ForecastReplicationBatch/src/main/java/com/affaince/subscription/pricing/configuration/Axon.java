package com.affaince.subscription.pricing.configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.ActiveMQConfiguration;
import com.affaince.subscription.pricing.forecast.ForecastingClient;
import com.affaince.subscription.pricing.forecast.ForecastingTrigger;
import com.affaince.subscription.pricing.forecast.ProductPricingTrigger;
import com.affaince.subscription.pricing.forecast.ProductsRetriever;
import com.affaince.subscription.pricing.forecast.interpolate.ForecastInterpolatedSubscriptionCountFinder;
import com.affaince.subscription.pricing.forecast.interpolate.Interpolator;
import com.mongodb.Mongo;
import org.apache.camel.CamelContext;
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

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
@EnableJms
@ComponentScan("com.affaince")
public class Axon extends ActiveMQConfiguration {

    @Autowired
    private CamelContext camelContext;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
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


    @Bean
    public Interpolator interpolator() {
        return new Interpolator();
    }

    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() throws Exception {

                //job for calculating forecast  and pseudoActuals for eligible products.
                from("{{subscription.forecast.timer.expression}}")
                        .routeId("productsRetriever")
                        .to("bean:productsRetriever")
                        .split(body())
                        .to("{{subscription.forecast.poston}}")
                        .multicast().to("direct:forecaster", "direct:stepForecaster");
/*
                from("{{subscription.forecast.poston}}")
                        .routeId("forecaster")
*/
                from("direct:forecaster")
                        .to("bean:forecastingTrigger")
                        .choice().when()
                        .simple("${body} != null")
                        .to("bean:forecastingClient?method=initiateForecast")
                        .endChoice();

                from("direct:stepForecaster")
                        .to("bean:forecastingClient?method=initiatePseudoActual");


/*
                Predicate demandTrendChecker = or(body().isEqualTo(ProductDemandTrend.UPWARD), body().isEqualTo(ProductDemandTrend.DOWNWARD));
                //job for calculating pseudoActuals for each product.

                from("{{subscription.pricing.timer.expression}}")
                        .routeId("PriceDeterminator")
                        .to("bean:forecastInterpolatedSubscriptionCountFinder")
                        .to("bean:productPricingTrigger")
                        .choice()
                        .when(demandTrendChecker)
                        .to("bean:pricingClient")
                        .endChoice();
*/

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