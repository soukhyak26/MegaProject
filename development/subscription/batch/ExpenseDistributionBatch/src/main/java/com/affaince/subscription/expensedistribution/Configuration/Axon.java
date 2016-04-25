package com.affaince.subscription.expensedistribution.Configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.RabbitMQConfiguration;
import com.affaince.subscription.expensedistribution.determinator.OperatingExpenseStrategyDeterminator;
import com.affaince.subscription.expensedistribution.processor.DefaultOperatingExpenseDistributionDeterminator;
import com.affaince.subscription.expensedistribution.processor.ExtraPolationBasedOperatingExpenseDistributionDeterminator;
import com.affaince.subscription.expensedistribution.processor.ForecastBasedOperatingExpenseDistributionDeterminator;
import com.affaince.subscription.expensedistribution.query.repository.DeliveryViewRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.CamelContextConfiguration;
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
    private CamelContext camelContext;
    @Autowired
    private DeliveryViewRepository deliveryViewRepository;

    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }

    @Bean
    public OperatingExpenseStrategyDeterminator operatingExpenseStrategyDeterminator() {
        return new OperatingExpenseStrategyDeterminator();
    }

    @Bean
    public DefaultOperatingExpenseDistributionDeterminator defaultOperatingExpenseDistributionDeterminator () {
        return new DefaultOperatingExpenseDistributionDeterminator();
    }

    @Bean
    public ExtraPolationBasedOperatingExpenseDistributionDeterminator extraPolationBasedOperatingExpenseDistributionDeterminator () {
        return new ExtraPolationBasedOperatingExpenseDistributionDeterminator();
    }

    @Bean
    public ForecastBasedOperatingExpenseDistributionDeterminator forecastBasedOperatingExpenseDistributionDeterminator () {
        return new ForecastBasedOperatingExpenseDistributionDeterminator();
    }


    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() {
                from("timer://foo?repeatCount=1").choice()
                        .to("bean:operatingExpenseStrategyDeterminator?decideOperatingExpenseStrategy(*)")
                        .when(simple("${body.operatingExpenseDistributionStrategyType}==${type:com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.DEFAULT_STRATEGY}"))
                        .to("bean:defaultOperatingExpenseDistributionDeterminator").to("bean:publisher")
                        .when(simple("${body.operatingExpenseDistributionStrategyType}==${type:com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.EXTRAPOLATION_BASED_STRATEGY}"))
                        .to("bean:extraPolationBasedOperatingExpenseDistributionDeterminator").to("bean:publisher")
                        .when(simple("${body.operatingExpenseDistributionStrategyType}==${type:com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.FORECAST_BASED_STRATEGY}"))
                        .to("bean:forecastBasedOperatingExpenseDistributionDeterminator").to("bean:publisher");
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