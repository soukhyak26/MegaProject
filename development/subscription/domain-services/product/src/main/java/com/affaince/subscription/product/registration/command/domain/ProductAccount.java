package com.affaince.subscription.product.registration.command.domain;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductAccount extends AbstractAnnotatedEntity {
    private Map<LocalDate, PriceBucket> activePriceBuckets;
    private Map<LocalDate, ProductPerformanceTracker> performanceTracker;
    private Map<YearMonth, AggregationPerformanceTracker> monthlyPerformanceMetrics;
    private long currentStockInUnits;
    private ProductPricingCategory productPricingCategory;
    private double creditPoints;
    private double variableExpenseSlope;

    public ProductAccount() {
        performanceTracker = new TreeMap<>();
        activePriceBuckets = new TreeMap<>();
    }

    public Map<LocalDate, ProductPerformanceTracker> getPerformanceTracker() {
        return this.performanceTracker;
    }

    public void setPerformanceTracker(Map<LocalDate, ProductPerformanceTracker> performanceTracker) {
        this.performanceTracker = performanceTracker;
    }

    public long getCurrentStockInUnits() {
        return this.currentStockInUnits;
    }

    public void setCurrentStockInUnits(long currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return this.productPricingCategory;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }

    public void addPerformanceTracker(LocalDate date, ProductPerformanceTracker tracker) {
        this.performanceTracker.put(date, tracker);
    }


    public ProductPerformanceTracker getLatestPerformanceTracker() {
        Set<LocalDate> dateKeys = performanceTracker.keySet();
        LocalDate max = null;
        for (LocalDate date : dateKeys) {
            if (date.isAfter(max)) {
                max = date;
            }
        }
        return performanceTracker.get(max);

    }

    public PriceBucket findActivePriceBucketByDate(LocalDate dateIdentifier) {
        return this.activePriceBuckets.get(dateIdentifier);
    }

    public Map<LocalDate, PriceBucket> getActivePriceBuckets() {
        return activePriceBuckets;
    }

    public void setActivePriceBuckets(Map<LocalDate, PriceBucket> activePriceBuckets) {
        this.activePriceBuckets = activePriceBuckets;
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

    public Map<YearMonth, AggregationPerformanceTracker> getMonthlyPerformanceMetrics() {
        return this.monthlyPerformanceMetrics;
    }

    public void setMonthlyPerformanceMetrics(Map<YearMonth, AggregationPerformanceTracker> monthlyPerformanceMetrics) {
        this.monthlyPerformanceMetrics = monthlyPerformanceMetrics;
    }

    public double getVariableExpenseSlope() {
        return this.variableExpenseSlope;
    }

    public void setVariableExpenseSlope(double variableExpenseSlope) {
        this.variableExpenseSlope = variableExpenseSlope;
    }
}
