package com.affaince.subscription.product.registration.command.domain;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductAccount {
    private Map<String,ProductPerformanceTracker> forecastPerUnitPeriod;
    private Map<String,ProductPerformanceTracker> actualsPerUnitPeriod;
    private ProductPricingCategory productPricingCategory;

    public ProductAccount() {
        forecastPerUnitPeriod = new TreeMap<>();
        actualsPerUnitPeriod = new TreeMap<>();
    }

    public void addForecast (ProductPerformanceTracker tracker, String date) {
        forecastPerUnitPeriod.put(date, tracker);
    }

    public void addActuals (ProductPerformanceTracker tracker, String date) {
        actualsPerUnitPeriod.put(date, tracker);
    }

    public ProductPerformanceTracker getForecast (String date) {
        return this.forecastPerUnitPeriod.get(date);
    }

    public ProductPerformanceTracker getActuals (String date) {
        return this.actualsPerUnitPeriod.get(date);
    }

    public Map<String, ProductPerformanceTracker> getForecastPerUnitPeriod() {
        return this.forecastPerUnitPeriod;
    }

    public Map<String, ProductPerformanceTracker> getActualsPerUnitPeriod() {
        return this.actualsPerUnitPeriod;
    }
}
