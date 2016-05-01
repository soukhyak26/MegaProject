package com.affaince.subscription.product.registration.query.view;

import com.affaince.subscription.product.registration.command.domain.PriceBucket;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Created by mandark on 28-01-2016.
 */
@Document(collection = "ProductActualsView")
public class ProductActualsView {
    private String productId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double demandDensity;
    private double averageDemandPerSubscriber;
    private long newSubscritptions;
    private long churnedSubscriptions;

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

    public ProductActualsView(String productId, LocalDate fromDate, LocalDate toDate, double demandDensity, double averageDemandPerSubscriber, long newSubscritptions, long churnedSubscriptions) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.demandDensity = demandDensity;
        this.averageDemandPerSubscriber = averageDemandPerSubscriber;
        this.newSubscritptions = newSubscritptions;
        this.churnedSubscriptions = churnedSubscriptions;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public double getDemandDensity() {
        return this.demandDensity;
    }

    public double getAverageDemandPerSubscriber() {
        return this.averageDemandPerSubscriber;
    }

    public long getNewSubscritptions() {
        return this.newSubscritptions;
    }

    public long getChurnedSubscriptions() {
        return this.churnedSubscriptions;
    }

    public long getTotalNumberOfExistingCustomers() {
        return this.totalNumberOfExistingCustomers;
    }

    public double getPercentageCustomerChurn() {
        return this.percentageCustomerChurn;
    }

    public double getStartingMRR() {
        return this.startingMRR;
    }

    public Map<PriceBucket, Double> getNewMRRPerPriceBucket() {
        return newMRRPerPriceBucket;
    }

    public Map<PriceBucket, Double> getChrunedMRRPerPriceBucket() {
        return chrunedMRRPerPriceBucket;
    }

    public double getGrossMargin() {
        return this.grossMargin;
    }

    public double getPercentageGrossMargin() {
        return this.percentageGrossMargin;
    }

    public double getTotalOperationalExpenses() {
        return this.totalOperationalExpenses;
    }

    public double getOperatingProfit() {
        return this.operatingProfit;
    }

    public double getPercentageOperatingProfit() {
        return this.percentageOperatingProfit;
    }

    public double getTotalChurnedMRR() {
        return this.totalChurnedMRR;
    }

    public double getPercentageMRRChurn() {
        return this.percentageMRRChurn;
    }

    public double getEndingMRR() {
        return this.endingMRR;
    }

    public double getNetNewMRR() {
        return this.netNewMRR;
    }

    public double getAverageRevenuePerNewSubscriber() {
        return this.averageRevenuePerNewSubscriber;
    }

    public double getAverageRevenuePerSubscriber() {
        return this.averageRevenuePerSubscriber;
    }

    public double getSubscriberLIfetimeValue() {
        return this.subscriberLIfetimeValue;
    }

    public double getSubscriberLifetimePeriod() {
        return this.subscriberLifetimePeriod;
    }

    public double getCostOfAcquiringASubscriber() {
        return this.costOfAcquiringASubscriber;
    }

    public double getSLVToCASRatio() {
        return this.SLVToCASRatio;
    }

    public double getMonthsToRecoverCAS() {
        return this.monthsToRecoverCAS;
    }

    public double getSalesAndMarketingExpenses() {
        return this.salesAndMarketingExpenses;
    }

}
