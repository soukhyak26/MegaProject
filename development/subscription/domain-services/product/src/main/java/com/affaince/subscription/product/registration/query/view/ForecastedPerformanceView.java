package com.affaince.subscription.product.registration.query.view;

import com.affaince.subscription.product.registration.command.domain.PriceBucket;
import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by mandark on 28-01-2016.
 */
public class ForecastedPerformanceView {
    private String productId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double demandDensity;
    private double expectedMerchantProfitPercentage;
    private double averageDemandPerSubscriber;
    private long totalDeliveriesPerPeriod;
    private double averageWeightPerDelivery;
    private long newCustomers;
    private long totalNumberOfChurnedCustomers;
    private long totalNumberOfExistingCustomers;
    private double percentageCustomerChurn;
    private double startingMRR;
    private Map<PriceBucket, Double> newMRRPerPriceBucket;
    private Map<PriceBucket, Double> chrunedMRRPerPriceBucket;
    private double grossMargin;
    private double percentageGrossMargin;
    private double totalOperationalExpenses;
    private double operatingProfit;
    private double percentageOperatingProfit;
    private double totalChurnedMRR;
    private double percentageMRRChurn;
    private double endingMRR;
    private double netNewMRR;
    private double averageRevenuePerNewSubscriber;
    private double averageRevenuePerSubscriber;
    private double subscriberLIfetimeValue;
    private double subscriberLifetimePeriod;
    private double costOfAcquiringASubscriber;
    private double SLVToCASRatio;
    private double monthsToRecoverCAS;
    private double salesAndMarketingExpenses;

}
