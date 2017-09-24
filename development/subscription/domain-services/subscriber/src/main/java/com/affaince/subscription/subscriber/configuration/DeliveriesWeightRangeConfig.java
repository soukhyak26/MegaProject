package com.affaince.subscription.subscriber.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 9/23/2017.
 */
@Configuration
@ConfigurationProperties(prefix="subscription.forecast.deliveries.weightrange")
public class DeliveriesWeightRangeConfig {
    @NestedConfigurationProperty()
    private List<WeightRange> weightRange = new ArrayList<>();
    public static class WeightRange{
        private double min;
        private double max;

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        @Override
        public String toString() {
            return "WeightRange{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }

    public List<WeightRange> getWeightRange() {
        return weightRange;
    }

    public void setWeightRange(List<WeightRange> weightRange) {
        this.weightRange = weightRange;
    }
}
