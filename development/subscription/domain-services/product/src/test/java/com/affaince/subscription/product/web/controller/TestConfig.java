package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Created by mandar on 05-07-2016.
 */
@SpringBootApplication
@ComponentScan(
        excludeFilters = {
                // Exclude the default message service
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ProductViewRepository.class),
               // @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ProductForecastMetricsViewRepository.class),
                // Exclude the default boot application or it's
                // @ComponentScan will pull in the default message service
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SubscriptionCommandGateway.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ForecastControllerTestApp.class)
        }
)
public class TestConfig {


}
