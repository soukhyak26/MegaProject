package com.affaince.subscription.product.query.view;

import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.ProductMonthlyVersionId;
import com.affaince.subscription.product.vo.ProductPeriodVersionId;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.YearMonth;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by mandark on 28-01-2016.
 */
@Document(collection = "ProductForecastMetricsView")
public class ProductForecastMetricsView {
    @Id
    //private final ProductMonthlyVersionId productMonthlyVersionId;
    private ProductPeriodVersionId productPeriodVersionId;
    private SortedSet<PriceTaggedWithProduct> taggedPriceVersions;
    private double fixedOperatingExpense;
    private double variableOperatingExpense;

    /*
    private double demandDensity;
    private double averageDemandPerSubscriber;
*/
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalNumberOfExistingSubscriptions;
    private double percentageCustomerChurn;
    private double startingMRR;
    private Map<PriceBucket, Double> newMRRPerPriceBucket;
    private Map<PriceBucket, Double> churnedMRRPerPriceBucket;
    private double grossMargin;
    private double percentageGrossMargin;
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

    public ProductForecastMetricsView(String productId, Interval period) {
        this.productPeriodVersionId = new ProductPeriodVersionId(productId, period);
        taggedPriceVersions = new TreeSet<>();
        newMRRPerPriceBucket = new TreeMap<>();
        churnedMRRPerPriceBucket = new TreeMap<>();
    }

    public ProductPeriodVersionId getProductPeriodVersionId() {
        return productPeriodVersionId;
    }

    public SortedSet<PriceTaggedWithProduct> getTaggedPriceVersions() {
        return taggedPriceVersions;
    }

    public void setTaggedPriceVersions(SortedSet<PriceTaggedWithProduct> taggedPriceVersions) {
        this.taggedPriceVersions = taggedPriceVersions;
    }

    public double getFixedOperatingExpense() {
        return fixedOperatingExpense;
    }

    public void setFixedOperatingExpense(double fixedOperatingExpense) {
        this.fixedOperatingExpense = fixedOperatingExpense;
    }

    public double getVariableOperatingExpense() {
        return variableOperatingExpense;
    }

    public void setVariableOperatingExpense(double variableOperatingExpense) {
        this.variableOperatingExpense = variableOperatingExpense;
    }

    public long getNewSubscriptions() {
        return newSubscriptions;
    }

    public void setNewSubscriptions(long newSubscriptions) {
        this.newSubscriptions = newSubscriptions;
    }

    public long getChurnedSubscriptions() {
        return churnedSubscriptions;
    }

    public void setChurnedSubscriptions(long churnedSubscriptions) {
        this.churnedSubscriptions = churnedSubscriptions;
    }

    public long getTotalNumberOfExistingSubscriptions() {
        return totalNumberOfExistingSubscriptions;
    }

    public void setTotalNumberOfExistingSubscriptions(long totalNumberOfExistingSubscriptions) {
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
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

    public Map<PriceBucket, Double> getChurnedMRRPerPriceBucket() {
        return churnedMRRPerPriceBucket;
    }

    public void setChurnedMRRPerPriceBucket(Map<PriceBucket, Double> churnedMRRPerPriceBucket) {
        this.churnedMRRPerPriceBucket = churnedMRRPerPriceBucket;
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

    public double getOperatingProfit() {
        return operatingProfit;
    }

    public void setOperatingProfit(double operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public double getPercentageOperatingProfit() {
        return percentageOperatingProfit;
    }

    public void setPercentageOperatingProfit(double percentageOperatingProfit) {
        this.percentageOperatingProfit = percentageOperatingProfit;
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
