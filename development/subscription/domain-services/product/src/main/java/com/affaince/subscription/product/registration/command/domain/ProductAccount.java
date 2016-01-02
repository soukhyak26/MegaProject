package com.affaince.subscription.product.registration.command.domain;

import org.joda.time.LocalDate;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductAccount {
    private Map<LocalDate, PriceBucket> activeForecastedPriceBuckets;
    private Map<LocalDate, PriceBucket> activeActualPriceBuckets;
    private Map<LocalDate, ProductPerformanceTracker> forecastPerUnitPeriod;
    private Map<LocalDate, ProductPerformanceTracker> actualsPerUnitPeriod;
    private long currentStockInUnits;
    private ProductPricingCategory productPricingCategory;
    private double creditPoints;

    public ProductAccount() {
        forecastPerUnitPeriod = new TreeMap<>();
        actualsPerUnitPeriod = new TreeMap<>();
        activeForecastedPriceBuckets = new TreeMap<>();
        activeActualPriceBuckets = new TreeMap<>();
    }

    public void setForecastPerUnitPeriod(Map<LocalDate, ProductPerformanceTracker> forecastPerUnitPeriod) {
        this.forecastPerUnitPeriod = forecastPerUnitPeriod;
    }

    public void setActualsPerUnitPeriod(Map<LocalDate, ProductPerformanceTracker> actualsPerUnitPeriod) {
        this.actualsPerUnitPeriod = actualsPerUnitPeriod;
    }


    public void setCurrentStockInUnits(long currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }

    public Map<LocalDate, ProductPerformanceTracker> getForecastPerUnitPeriod() {

        return this.forecastPerUnitPeriod;
    }

    public Map<LocalDate, ProductPerformanceTracker> getActualsPerUnitPeriod() {
        return this.actualsPerUnitPeriod;
    }

    public long getCurrentStockInUnits() {
        return this.currentStockInUnits;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return this.productPricingCategory;
    }

    public void addPerformanceTrackerToForecast(LocalDate date, ProductPerformanceTracker tracker) {
        this.forecastPerUnitPeriod.put(date, tracker);
    }

    public void addPerformanceTrackerToActuals(LocalDate date, ProductPerformanceTracker tracker) {
        this.actualsPerUnitPeriod.put(date, tracker);
    }

    public ProductPerformanceTracker getLatestActuals() {
        Set<LocalDate> timeBasedKeys = actualsPerUnitPeriod.keySet();
        LocalDate max = null;
        for (LocalDate time : timeBasedKeys) {
            if (time.compareTo(max) > 0) {
                max = time;
            }
        }
        return actualsPerUnitPeriod.get(max);

    }

    public ProductPerformanceTracker getLatestForecast() {
        Set<LocalDate> timeBasedKeys = forecastPerUnitPeriod.keySet();
        LocalDate max = null;
        for (LocalDate time : timeBasedKeys) {
            if (time.compareTo(max) > 0) {
                max = time;
            }
        }
        return forecastPerUnitPeriod.get(max);

    }

    public void setActiveForecastedPriceBuckets(Map<LocalDate, PriceBucket> activeForecastedPriceBuckets) {
        this.activeForecastedPriceBuckets = activeForecastedPriceBuckets;
    }

    public PriceBucket findActiveForecastedPriceBucketByDate(LocalDate dateIdentifier) {
        return this.activeForecastedPriceBuckets.get(dateIdentifier);
    }

    public Map<LocalDate, PriceBucket> getActiveForecastedPriceBuckets() {
        return activeForecastedPriceBuckets;
    }

    public void addNewForecastedPriceBucket(LocalDate date, PriceBucket forecastedPriceBucket) {
        activeForecastedPriceBuckets.put(date, forecastedPriceBucket);
    }

    public PriceBucket getLatestForecastedPriceBucket() {
        Set<LocalDate> timeBasedKeys = activeForecastedPriceBuckets.keySet();
        LocalDate max = null;
        for (LocalDate time : timeBasedKeys) {
            if (time.compareTo(max) > 0) {
                max = time;
            }
        }
        return activeForecastedPriceBuckets.get(max);
    }

    public void setActiveActualPriceBuckets(Map<LocalDate, PriceBucket> activeActualPriceBuckets) {
        this.activeActualPriceBuckets = activeActualPriceBuckets;
    }

    public PriceBucket findActiveActualPriceBucketByDate(LocalDate dateIdentifier) {
        return this.activeActualPriceBuckets.get(dateIdentifier);
    }

    public Map<LocalDate, PriceBucket> getActiveActualPriceBuckets() {
        return activeActualPriceBuckets;
    }

    public void addNewActualPriceBucket(LocalDate date, PriceBucket actualPriceBucket) {
        activeActualPriceBuckets.put(date, actualPriceBucket);
    }

    public PriceBucket getLatestActualPriceBucket() {
        Set<LocalDate> timeBasedKeys = activeActualPriceBuckets.keySet();
        LocalDate max = null;
        for (LocalDate time : timeBasedKeys) {
            if (time.compareTo(max) > 0) {
                max = time;
            }
        }
        return activeActualPriceBuckets.get(max);
    }

    public double getCreditPoints() {
        return this.creditPoints;
    }

    public void setCreditPoints(double creditPoints) {
        this.creditPoints = creditPoints;
    }
}
