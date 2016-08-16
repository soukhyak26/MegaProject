package com.affaince.subscription.pricing.Configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.ActiveMQConfiguration;
import com.mongodb.Mongo;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ThreadPoolBuilder;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.axonframework.eventhandling.EventTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
public class Axon extends ActiveMQConfiguration {

    @Autowired
    private CamelContext camelContext;

    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }
    @Bean
    public RestTemplate restTemplate(){
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

    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() throws Exception {
                ExecutorService executorService = new ThreadPoolBuilder(camelContext).poolSize(5).maxQueueSize(100).build("CustomThreadPool");
                from("quartz://timer?cron=0+0+20+*+*+?").to("bean:productsRetriever")
                        .split(body())
                        .threads()
                        .executorService(executorService)
                        .to("bean:forecastingTrigger");
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
