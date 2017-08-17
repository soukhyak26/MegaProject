package com.affiance.prediction.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 5/14/2017.
 */
@Configuration
@ConfigurationProperties(prefix="forecast")
public class ForecasterConfiguration {
    @NestedConfigurationProperty()
    private List<ForecasterChainConfig> forecasterchain = new ArrayList<>();

    public static class ForecasterChainConfig {
        private String name;
        private String cls;
        private String next;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCls() {
            return cls;
        }

        public void setCls(String cls) {
            this.cls = cls;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "ForecasterChainConfig{" +
                    "name='" + name + '\'' +
                    ", cls='" + cls + '\'' +
                    ", next='" + next + '\'' +
                    '}';
        }
    }

    public List<ForecasterChainConfig> getForecasterchain() {
        return forecasterchain;
    }

    public void setForecasterchain(List<ForecasterChainConfig> forecasterchain) {
        this.forecasterchain = forecasterchain;
    }
}
