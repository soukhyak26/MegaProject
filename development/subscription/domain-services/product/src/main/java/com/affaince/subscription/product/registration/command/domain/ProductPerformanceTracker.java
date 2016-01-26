package com.affaince.subscription.product.registration.command.domain;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductPerformanceTracker {
    private YearMonth monthOfYear;
    private double demandDensity;
    private double averageDemandPerSubscriber;
    private long totalDeliveriesPerPeriod;
    private double averageWeightPerDelivery;

    Map<LocalDate, InstantaneousPerformanceTracker> instantaneousPerformanceTrackers;
    AggregationPerformanceTracker aggregationPerformanceTracker;

    public ProductPerformanceTracker() {
        instantaneousPerformanceTrackers = new TreeMap<>();
        aggregationPerformanceTracker = new AggregationPerformanceTracker();
    }

    public Map<LocalDate, InstantaneousPerformanceTracker> getInstantaneousPerformanceTrackers() {
        return this.instantaneousPerformanceTrackers;
    }

    public void setInstantaneousPerformanceTrackers(Map<LocalDate, InstantaneousPerformanceTracker> instantaneousPerformanceTrackers) {
        this.instantaneousPerformanceTrackers = instantaneousPerformanceTrackers;
    }

    public AggregationPerformanceTracker getAggregationPerformanceTracker() {
        return this.aggregationPerformanceTracker;
    }

    public void setAggregationPerformanceTracker(AggregationPerformanceTracker aggregationPerformanceTracker) {
        this.aggregationPerformanceTracker = aggregationPerformanceTracker;
    }

    public InstantaneousPerformanceTracker getLatestInstantaneousPerformanceTracker() {
        Set<LocalDate> timeBasedKeys = instantaneousPerformanceTrackers.keySet();
        LocalDate max = null;
        for (LocalDate time : timeBasedKeys) {
            if (time.compareTo(max) > 0) {
                max = time;
            }
        }
        return instantaneousPerformanceTrackers.get(max);

    }

    public double getTotalOperationalExpenses() {
        return getLatestInstantaneousPerformanceTracker().getTotalOperationalExpenses();
    }

    public void calculateSubscriptionMetrics(List<PriceBucket> priceBucket) {
/*
        this.netNewCustomers =
        this.totalNumberOfChurnedCustomers
        this.totalNumberOfExistingCustomers
        this.percentageCustomerChurn
        this.startingMRR
        this.newMRRPerPriceBucket
        this.chrunedMRRPerPriceBucket
        this.totalChurnedMRR
        this.percentageMRRChurn
        this.endingMRR
        this.netNewMRR
        this.averageRevenuePerNewSubscriber
        this.averageRevenuePerSubscriber
        this.costOfGoodsSold
        this.grossMargin
        this.percentageGrossMargin
        this.totalOperationalExpenses
        this.peratingProfit
        this.percentageOperatingProfit
        this.subscriberLIfetimeValue
        this.subscriberLifetimePeriod
        this.costOfAcquiringASubscriber
        this.SLVToCASRatio
        this.monthsToRecoverCAS
        this.salesAndMarketingExpenses
*/
    }

    public double getLatestPurchasePrice() {
        InstantaneousPerformanceTracker performanceTracker = getLatestInstantaneousPerformanceTracker();
        return performanceTracker.getPurchasePrice();
    }

    public double getLatestMRP() {
        InstantaneousPerformanceTracker performanceTracker = getLatestInstantaneousPerformanceTracker();
        return performanceTracker.getMRP();
    }

    public double getExpectedMerchantProfitPercentage() {
        InstantaneousPerformanceTracker performanceTracker = getLatestInstantaneousPerformanceTracker();
        return performanceTracker.getExpectedMerchantProfitPercentage();
    }

    public double getDemandDensity() {
        return demandDensity;
    }

    public void setDemandDensity(double demandDensity) {
        this.demandDensity = demandDensity;
    }

    public double getAverageDemandPerSubscriber() {
        return averageDemandPerSubscriber;
    }

    public void setAverageDemandPerSubscriber(double averageDemandPerSubscriber) {
        this.averageDemandPerSubscriber = averageDemandPerSubscriber;
    }

    public long getTotalDeliveriesPerPeriod() {
        return totalDeliveriesPerPeriod;
    }

    public void setTotalDeliveriesPerPeriod(long totalDeliveriesPerPeriod) {
        this.totalDeliveriesPerPeriod = totalDeliveriesPerPeriod;
    }

    public double getAverageWeightPerDelivery() {
        return averageWeightPerDelivery;
    }

    public void setAverageWeightPerDelivery(double averageWeightPerDelivery) {
        this.averageWeightPerDelivery = averageWeightPerDelivery;
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }

    public void setMonthOfYear(YearMonth monthOfYear) {
        this.monthOfYear = monthOfYear;
    }
}
