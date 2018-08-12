package com.affaince.subscription.payments.simulator;

import org.axonframework.spring.config.AnnotationDriven;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by anayonkar on 21/8/16.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.affaince.subscription.payments.simulator")
@AnnotationDriven
@EnableWebMvc
@PropertySource({"classpath:Application.properties","classpath:common.properties"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
