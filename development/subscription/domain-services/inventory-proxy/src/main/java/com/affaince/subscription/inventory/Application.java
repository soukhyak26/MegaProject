package com.affaince.subscription.inventory;

import org.axonframework.contextsupport.spring.AnnotationDriven;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by NIKUNJ on 7/12/2015.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan ("com.affaince")
@AnnotationDriven
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main (String [] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run();
    }
}
