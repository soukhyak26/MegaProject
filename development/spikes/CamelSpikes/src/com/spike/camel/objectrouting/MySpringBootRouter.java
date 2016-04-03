package com.spike.camel.objectrouting;

/**
 * Created by mandark on 02-04-2016.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.spike")
@EnableAspectJAutoProxy

public class MySpringBootRouter extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MySpringBootRouter.class);
        System.out.println("$$$$$$In Springbot router$$$$$$$$$$");
        springApplication.run();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MySpringBootRouter.class);
    }

}