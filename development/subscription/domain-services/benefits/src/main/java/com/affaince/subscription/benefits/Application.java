package com.affaince.subscription.benefits;

import org.axonframework.spring.config.AnnotationDriven;
import org.axonframework.spring.config.EnableAxon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by NIKUNJ on 7/12/2015.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.affaince")
@AnnotationDriven
@EnableAspectJAutoProxy
@EnableAxon
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
