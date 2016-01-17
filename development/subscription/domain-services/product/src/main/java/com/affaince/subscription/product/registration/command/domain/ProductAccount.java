package com.affaince.subscription.product.registration.command.domain;

import org.joda.time.LocalDate;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductAccount {
    private Map<LocalDate, PriceBucket> activePriceBuckets;
    private Map<LocalDate, ProductPerformanceTracker> performanceTracker;
    private long currentStockInUnits;
    private ProductPricingCategory productPricingCategory;
    private double creditPoints;

    public ProductAccount() {
        performanceTracker = new TreeMap<>();
        activePriceBuckets = new TreeMap<>();
    }

    public void setPerformanceTracker(Map<LocalDate, ProductPerformanceTracker> performanceTracker) {
        this.performanceTracker = performanceTracker;
    }


    public void setCurrentStockInUnits(long currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }

    public Map<LocalDate, ProductPerformanceTracker> getPerformanceTracker() {
        return this.performanceTracker;
    }

    public long getCurrentStockInUnits() {
        return this.currentStockInUnits;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return this.productPricingCategory;
    }

    public void addPerformanceTracker(LocalDate date, ProductPerformanceTracker tracker) {
        this.performanceTracker.put(date, tracker);
    }


    public ProductPerformanceTracker getLatestPerformanceTracker() {
        Set<LocalDate> timeBasedKeys = performanceTracker.keySet();
        LocalDate max = null;
        for (LocalDate time : timeBasedKeys) {
            if (time.compareTo(max) > 0) {
                max = time;
            }
        }
        return performanceTracker.get(max);

    }

    public void setActivePriceBuckets(Map<LocalDate, PriceBucket> activePriceBuckets) {
        this.activePriceBuckets = activePriceBuckets;
    }

    public PriceBucket findActivePriceBucketByDate(LocalDate dateIdentifier) {
        return this.activePriceBuckets.get(dateIdentifier);
    }

    public Map<LocalDate, PriceBucket> getActivePriceBuckets() {
        return activePriceBuckets;
    }

    public void addNewPriceBucket(LocalDate date, PriceBucket forecastedPriceBucket) {
        activePriceBuckets.put(date, forecastedPriceBucket);
    }

    public PriceBucket getLatestPriceBucket() {
        Set<LocalDate> timeBasedKeys = activePriceBuckets.keySet();
        LocalDate max = null;
        for (LocalDate time : timeBasedKeys) {
            if (time.compareTo(max) > 0) {
                max = time;
            }
        }
        return activePriceBuckets.get(max);
    }


    public double getCreditPoints() {
        return this.creditPoints;
    }

    public void setCreditPoints(double creditPoints) {
        this.creditPoints = creditPoints;
    }


    public double getLatestDemandDensity() {
        return getLatestPerformanceTracker().getDemandDensity();
    }
}
