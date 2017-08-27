package com.affiance.prediction.config;

import com.affiance.prediction.algos.*;
import com.affiance.prediction.deserializer.DataFrameVODeserializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.affiance.prediction.serializer.DataFrameVOSerializer;

/**
 * Created by mandar on 10-01-2017.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.affiance")
@EnableConfigurationProperties({HistoryMinSizeConstraints.class, HistoryMaxSizeConstraints.class})
public class Forecast {
/*
    @Bean
    public DemandForecasterChain demandForecasterChain() {
        return new DemandForecasterChain();
    }

    @Bean
    public SimpleLinearForecaster simpleLinearForecaster(){
        return new SimpleLinearForecaster();
    }
    @Bean
    public SimpleMovingAverageDemandForecaster smaForecaster() {
        return new SimpleMovingAverageDemandForecaster();
    }

    @Bean
    public SimpleExponentialSmoothingDemandForecaster semaForecaster() {
        return new SimpleExponentialSmoothingDemandForecaster();
    }

    @Bean
    public TripleExponentialSmoothingDemandForecaster temaForecaster() {
        return new TripleExponentialSmoothingDemandForecaster();
    }
*/
    @Bean
    ARIMABasedDemandForecaster arimaBasedDemandForecaster(){
        return new ARIMABasedDemandForecaster();
    }

    @Bean
    DataFrameVODeserializer dataFrameVODeserializer(){
        return new DataFrameVODeserializer();
    }

    @Bean
    DataFrameVOSerializer dataFrameVOSerializer(){
        return new DataFrameVOSerializer();
    }
}