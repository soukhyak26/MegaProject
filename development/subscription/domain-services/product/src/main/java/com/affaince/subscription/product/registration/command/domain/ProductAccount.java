package com.affaince.subscription.product.registration.command.domain;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductAccount {
    private Map<LocalDate, PriceBucket> activePriceBuckets;
    private Map<YearMonth, ProductPerformanceTracker> performanceTracker;
    private long currentStockInUnits;
    private ProductPricingCategory productPricingCategory;
    private double creditPoints;

    public ProductAccount() {
        performanceTracker = new TreeMap<>();
        activePriceBuckets = new TreeMap<>();
    }

    public void setPerformanceTracker(Map<YearMonth, ProductPerformanceTracker> performanceTracker) {
        this.performanceTracker = performanceTracker;
    }


    public void setCurrentStockInUnits(long currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }

    public Map<YearMonth, ProductPerformanceTracker> getPerformanceTracker() {
        return this.performanceTracker;
    }

    public long getCurrentStockInUnits() {
        return this.currentStockInUnits;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return this.productPricingCategory;
    }

    public void addPerformanceTracker(YearMonth monthOfYear, ProductPerformanceTracker tracker) {
        this.performanceTracker.put(monthOfYear, tracker);
    }


    public ProductPerformanceTracker getLatestPerformanceTracker() {
        Set<YearMonth> monthBasedKeys = performanceTracker.keySet();
        YearMonth max = null;
        for (YearMonth month : monthBasedKeys) {
            if (month.isAfter(max)) {
                max = month;
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
