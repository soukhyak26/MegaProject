package com.affaince.subscription.subscriptionableitem.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by NIKUNJ on 7/12/2015.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.affaince")
public class Application {

    public static void main (String [] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class);
    }
}
