package com.spike.camel.objectrouting.config;

import com.spike.camel.objectrouting.aggregate.MyAggregationStrategy2;
import com.spike.camel.objectrouting.determine.Determinator;
import com.spike.camel.objectrouting.processor.Processor1;
import com.spike.camel.objectrouting.processor.Processor2;
import com.spike.camel.objectrouting.processor.Processor3;
import com.spike.camel.objectrouting.processor.Publisher;
import com.spike.camel.objectrouting.repo.BeanRepository;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.toolbox.AggregationStrategies;
import org.springframework.context.annotation.Bean;

/**
 * Created by mandark on 03-04-2016.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    BeanRepository myBeansRepository() {
        return new BeanRepository();
    }

    @Bean
    Processor3 processor3() {
        return new Processor3();
    }

    @Bean
    Processor2 processor2() {
        return new Processor2();
    }

    @Bean
    Processor1 processor1() {
        return new Processor1();
    }

    @Bean
    Publisher publisher() {
        return new Publisher();
    }

    @Bean
    Determinator determinator() {
        return new Determinator();
    }

    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() {
                //INT_02: retreive subscriptioable items details from main application
                from("timer://foo?repeatCount=1")
                        .to("bean:myBeansRepository?method=findAll()")
                        .split(body()).to("bean:determinator?method=determineType(*)").choice()
                        .when(simple("${body.processorType}== ${type:com.spike.camel.objectrouting.determine.ProcessorType.PROCESSOR1}"))
                        .to("bean:processor1")
                        .to("bean:publisher")
                        .when(simple("${body.processorType}== ${type:com.spike.camel.objectrouting.determine.ProcessorType.PROCESSOR2}"))
                        .multicast(AggregationStrategies.bean(MyAggregationStrategy2.class))
                        .parallelProcessing()
                        //.aggregate(constant(true), AggregationStrategies.bean(MyAggregationStrategy2.class))
                        .to("bean:processor2", "bean:processor3")
                        .end()
                        .to("bean:publisher");

            }
        };
    }

}
