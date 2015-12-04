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
    private Map <PriceBucket,Long> numberOfNewCustomersAssociatedWithAPrice;
    private Map <PriceBucket,Long> numberOfChurnedCustomersAssociatedWithAPrice;
    private Map <PriceBucket,Long> numberOfExistingCustomersAssociatedWithAPrice;
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




}
