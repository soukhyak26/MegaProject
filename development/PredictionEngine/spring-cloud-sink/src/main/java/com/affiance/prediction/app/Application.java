package com.affiance.prediction.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by mandar on 8/15/2017.
 */
@EnableBinding(Sink.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(
                Application.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void listen(String data){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@IN Sink: "+ data);
    }

}