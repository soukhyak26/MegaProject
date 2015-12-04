package com.affaince.subscription.product.registration.command.domain;

import java.util.Map;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductAccount {
    private Map<String,ProductPerformanceTracker> forecastPerUnitPeriod;
    private Map<String,ProductPerformanceTracker> actualsPerUnitPeriod;

    public void loadForecast(){

    }
    public void loadActuals(){

    }

    public Map<String, ProductPerformanceTracker> getForecastPerUnitPeriod() {
        return this.forecastPerUnitPeriod;
    }

    public Map<String, ProductPerformanceTracker> getActualsPerUnitPeriod() {
        return this.actualsPerUnitPeriod;
    }
}
