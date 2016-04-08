package com.affaince.subscription.pricing.configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.RabbitMQConfiguration;
import com.affaince.subscription.pricing.aggregate.CoefficientAggregationStrategy;
import com.affaince.subscription.pricing.detereminator.DefaultPriceDeterminator;
import com.affaince.subscription.pricing.detereminator.DemandBasedPriceDeterminator;
import com.affaince.subscription.pricing.processor.PricingStrategyDeterminator;
import com.affaince.subscription.pricing.processor.function.RegressionBasedCostFunctionProcessor;
import com.affaince.subscription.pricing.processor.function.RegressionBasedDemandFunctionProcessor;
import com.affaince.subscription.pricing.query.repository.ProductViewRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.apache.camel.util.toolbox.AggregationStrategies;
import org.axonframework.eventhandling.EventTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
public class Axon extends RabbitMQConfiguration {

    @Autowired
    CamelContext camelContext;
    @Autowired
    ProductViewRepository productViewRepository;

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
