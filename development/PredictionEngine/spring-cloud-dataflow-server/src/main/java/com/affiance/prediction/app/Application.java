package com.affiance.prediction.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by mandar on 8/15/2017.
 */
@EnableDataFlowServer
@SpringBootApplication
@PropertySource({"classpath:Application.properties"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(
                Application.class, args);
    }
}
