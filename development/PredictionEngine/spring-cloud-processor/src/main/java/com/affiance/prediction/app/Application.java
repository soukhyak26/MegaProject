package com.affiance.prediction.app;

import com.affiance.prediction.algos.ARIMABasedDemandForecaster;
import com.affiance.prediction.vo.DataFrameVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import deserializer.DataFrameVODeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 8/15/2017.
 */
@EnableBinding(Processor.class)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(
                Application.class, args);
    }
}