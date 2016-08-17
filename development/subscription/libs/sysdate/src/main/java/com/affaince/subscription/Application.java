package com.affaince.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by rbsavaliya on 17-08-2016.
 */
@SpringBootApplication
@ComponentScan("com.affaince")
@PropertySource({"classpath:Application.properties"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setWebEnvironment(false);
        springApplication.run();
    }
}
