package com.affaince.subscription.benefit.simulator.configuration;

import com.affaince.subscription.benefit.simulator.benefit.state.ApplicabilityState;
import com.affaince.subscription.benefit.simulator.benefit.state.BenefitCalculationState;
import com.affaince.subscription.benefit.simulator.benefit.state.EligibilityState;
import com.affaince.subscription.benefit.simulator.benefit.state.PointConversionState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public BenefitCalculationState benefitCalculationState () {
        BenefitCalculationState benefitCalculationState = new EligibilityState(
                new PointConversionState(
                        new ApplicabilityState(null)
                )
        );
        return benefitCalculationState;
    }
}
