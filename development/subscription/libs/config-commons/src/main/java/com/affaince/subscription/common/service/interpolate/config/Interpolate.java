package com.affaince.subscription.common.service.interpolate.config;

import com.affaince.subscription.common.service.interpolate.CubicSplineInterpolator;
import com.affaince.subscription.common.service.interpolate.InterpolatorChain;
import com.affaince.subscription.common.service.interpolate.SimpleLinearInterpolator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mandar on 2/28/2017.
 */
@Configuration
@EnableConfigurationProperties({MinSizeConstraints.class, MaxSizeConstraints.class})
public class Interpolate {
    @Bean
    public InterpolatorChain interpolatorChain(){return new InterpolatorChain();}

    @Bean
    public SimpleLinearInterpolator simpleLinearInterpolator() { return new SimpleLinearInterpolator();}

    @Bean
    public CubicSplineInterpolator cubicSplineInterpolator() { return new CubicSplineInterpolator();}
}
