package com.spike.camel.objectrouting.config;

import com.spike.camel.objectrouting.bean.MyBean;
import com.spike.camel.objectrouting.processor.Processor1;
import com.spike.camel.objectrouting.processor.Publisher;
import com.spike.camel.objectrouting.repo.BeanRepository;
import org.apache.camel.builder.RouteBuilder;
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
    Processor1 processor1(){
        return new Processor1();
    }
    @Bean
    Publisher publisher(){
        return new Publisher();
    }
    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() {
                //INT_02: retreive subscriptioable items details from main application
                from("direct:start").to("bean:myBeansRepository?method=findAll()").split(body())
                        //.choice().when().simple("&{detrminator.determineType}==com.spike.camel.objectrouting.determine.ProcessorType.PROCESSOR1")
                        .to("bean:processor1").to("bean:publisher").to("file://D://Applications//CamelSpike//output.txt");
            }
        };
    }

}
