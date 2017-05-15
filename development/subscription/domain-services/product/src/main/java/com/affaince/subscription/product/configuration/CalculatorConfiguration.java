package com.affaince.subscription.product.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 5/14/2017.
 */
@ConfigurationProperties("pricing")
@Component
public class CalculatorConfiguration {
    List<CalculatorChainConfig> calculatorchain = new ArrayList<>();

    public static class CalculatorChainConfig {
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
            return "CalculatorChainConfig{" +
                    "name='" + name + '\'' +
                    ", cls='" + cls + '\'' +
                    ", next='" + next + '\'' +
                    '}';
        }
    }

    public List<CalculatorChainConfig> getCalculatorchain() {
        return calculatorchain;
    }

    public void setCalculatorchain(List<CalculatorChainConfig> calculatorchain) {
        this.calculatorchain = calculatorchain;
    }
}
