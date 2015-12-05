package com.affaince.subscription.product.registration.command.domain;

import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductPerformanceTracker {
    private LocalDate fromDate;
    private LocalDate toDate;
    private Map<String,PriceBucket> timeWisePriceBuckets;
    private long netNewCustomers;
    private long totalNumberOfChurnedCustomers;
    private long totalNumberOfExistingCustomers;
    private double percentageCustomerChurn;
    private double startingMRR;
    private Map<PriceBucket,Double> newMRRPerPriceBucket;
    private Map<PriceBucket,Double> chrunedMRRPerPriceBucket;
    private double totalChurnedMRR;
    private double percentageMRRChurn;
    private double endingMRR;
    private double netNewMRR;
    private double averageRevenuePerNewSubscriber;
    private double averageRevenuePerSubscriber;
    private double costOfGoodsSold;
    private double grossMargin;
    private double percentageGrossMargin;
    private double totalOperationalExpenses;
    private double peratingProfit;
    private double percentageOperatingProfit;
    private double subscriberLIfetimeValue;
    private double subscriberLifetimePeriod;
    private double costOfAcquiringASubscriber;
    private double SLVToCASRatio;
    private double monthsToRecoverCAS;
    private double salesAndMarketingExpenses;

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Map<String, PriceBucket> getTimeWisePriceBuckets() {
        return timeWisePriceBuckets;
    }

    public PriceBucket findPriceBucketByDateIdentifier (String dateIdentifier) {
        return this.timeWisePriceBuckets.get(dateIdentifier);
    }

    public void addTimeWisePriceBuckets(PriceBucket priceBucket, String dateIdentifier) {
        this.timeWisePriceBuckets.put(dateIdentifier, priceBucket);
    }

    public long getNetNewCustomers() {
        return netNewCustomers;
    }

    public void setNetNewCustomers(long netNewCustomers) {
        this.netNewCustomers = netNewCustomers;
    }

    public long getTotalNumberOfChurnedCustomers() {
        return totalNumberOfChurnedCustomers;
    }

    public void setTotalNumberOfChurnedCustomers(long totalNumberOfChurnedCustomers) {
        this.totalNumberOfChurnedCustomers = totalNumberOfChurnedCustomers;
    }

    public long getTotalNumberOfExistingCustomers() {
        return totalNumberOfExistingCustomers;
    }

    public void setTotalNumberOfExistingCustomers(long totalNumberOfExistingCustomers) {
        this.totalNumberOfExistingCustomers = totalNumberOfExistingCustomers;
    }

    public double getPercentageCustomerChurn() {
        return percentageCustomerChurn;
    }

    public void setPercentageCustomerChurn(double percentageCustomerChurn) {
        this.percentageCustomerChurn = percentageCustomerChurn;
    }

    public double getStartingMRR() {
        return startingMRR;
    }

    public void setStartingMRR(double startingMRR) {
        this.startingMRR = startingMRR;
    }

    public Map<PriceBucket, Double> getNewMRRPerPriceBucket() {
        return newMRRPerPriceBucket;
    }

    public void setNewMRRPerPriceBucket(Map<PriceBucket, Double> newMRRPerPriceBucket) {
        this.newMRRPerPriceBucket = newMRRPerPriceBucket;
    }

    public Map<PriceBucket, Double> getChrunedMRRPerPriceBucket() {
        return chrunedMRRPerPriceBucket;
    }

    public void setChrunedMRRPerPriceBucket(Map<PriceBucket, Double> chrunedMRRPerPriceBucket) {
        this.chrunedMRRPerPriceBucket = chrunedMRRPerPriceBucket;
    }

    public double getTotalChurnedMRR() {
        return totalChurnedMRR;
    }

    public void setTotalChurnedMRR(double totalChurnedMRR) {
        this.totalChurnedMRR = totalChurnedMRR;
    }

    public double getPercentageMRRChurn() {
        return percentageMRRChurn;
    }

    public void setPercentageMRRChurn(double percentageMRRChurn) {
        this.percentageMRRChurn = percentageMRRChurn;
    }

    public double getEndingMRR() {
        return endingMRR;
    }

    public void setEndingMRR(double endingMRR) {
        this.endingMRR = endingMRR;
    }

    public double getNetNewMRR() {
        return netNewMRR;
    }

    public void setNetNewMRR(double netNewMRR) {
        this.netNewMRR = netNewMRR;
    }

    public double getAverageRevenuePerNewSubscriber() {
        return averageRevenuePerNewSubscriber;
    }

    public void setAverageRevenuePerNewSubscriber(double averageRevenuePerNewSubscriber) {
        this.averageRevenuePerNewSubscriber = averageRevenuePerNewSubscriber;
    }

    public double getAverageRevenuePerSubscriber() {
        return averageRevenuePerSubscriber;
    }

    public void setAverageRevenuePerSubscriber(double averageRevenuePerSubscriber) {
        this.averageRevenuePerSubscriber = averageRevenuePerSubscriber;
    }

    public double getCostOfGoodsSold() {
        return costOfGoodsSold;
    }

    public void setCostOfGoodsSold(double costOfGoodsSold) {
        this.costOfGoodsSold = costOfGoodsSold;
    }

    public double getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(double grossMargin) {
        this.grossMargin = grossMargin;
    }

    public double getPercentageGrossMargin() {
        return percentageGrossMargin;
    }

    public void setPercentageGrossMargin(double percentageGrossMargin) {
        this.percentageGrossMargin = percentageGrossMargin;
    }

    public double getTotalOperationalExpenses() {
        return totalOperationalExpenses;
    }

    public void setTotalOperationalExpenses(double totalOperationalExpenses) {
        this.totalOperationalExpenses = totalOperationalExpenses;
    }

    public double getPeratingProfit() {
        return peratingProfit;
    }

    public void setPeratingProfit(double peratingProfit) {
        this.peratingProfit = peratingProfit;
    }

    public double getPercentageOperatingProfit() {
        return percentageOperatingProfit;
    }

    public void setPercentageOperatingProfit(double percentageOperatingProfit) {
        this.percentageOperatingProfit = percentageOperatingProfit;
    }

    public double getSubscriberLIfetimeValue() {
        return subscriberLIfetimeValue;
    }

    public void setSubscriberLIfetimeValue(double subscriberLIfetimeValue) {
        this.subscriberLIfetimeValue = subscriberLIfetimeValue;
    }

    public double getSubscriberLifetimePeriod() {
        return subscriberLifetimePeriod;
    }

    public void setSubscriberLifetimePeriod(double subscriberLifetimePeriod) {
        this.subscriberLifetimePeriod = subscriberLifetimePeriod;
    }

    public double getCostOfAcquiringASubscriber() {
        return costOfAcquiringASubscriber;
    }

    public void setCostOfAcquiringASubscriber(double costOfAcquiringASubscriber) {
        this.costOfAcquiringASubscriber = costOfAcquiringASubscriber;
    }

    public double getSLVToCASRatio() {
        return SLVToCASRatio;
    }

    public void setSLVToCASRatio(double SLVToCASRatio) {
        this.SLVToCASRatio = SLVToCASRatio;
    }

    public double getMonthsToRecoverCAS() {
        return monthsToRecoverCAS;
    }

    public void setMonthsToRecoverCAS(double monthsToRecoverCAS) {
        this.monthsToRecoverCAS = monthsToRecoverCAS;
    }

    public double getSalesAndMarketingExpenses() {
        return salesAndMarketingExpenses;
    }

    public void setSalesAndMarketingExpenses(double salesAndMarketingExpenses) {
        this.salesAndMarketingExpenses = salesAndMarketingExpenses;
    }
}
