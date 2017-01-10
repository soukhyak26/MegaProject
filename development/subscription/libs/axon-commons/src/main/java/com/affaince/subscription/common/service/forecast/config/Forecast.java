package com.affaince.subscription.common.service.forecast.config;

import com.affaince.subscription.common.service.forecast.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mandar on 10-01-2017.
 */
@Configuration
@EnableConfigurationProperties({HistoryMinSizeConstraints.class, HistoryMaxSizeConstraints.class})
public class Forecast {
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

    @Bean
    public ARIMABasedDemandForecaster arimaForecaster() {
        return new ARIMABasedDemandForecaster();
    }
}
