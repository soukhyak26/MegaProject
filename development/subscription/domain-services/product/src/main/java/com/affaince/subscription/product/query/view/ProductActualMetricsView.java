package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by mandark on 28-01-2016.
 */
//MUST be handled at monthly level
@Document(collection = "ProductActualMetricsView")
public class ProductActualMetricsView {
    @Id
    private final ProductVersionId productVersionId;
    private LocalDate endDate;
    private SortedSet<PriceTaggedWithProduct> taggedPriceVersions;
    private double fixedOperatingExpense;
    private double variableOperatingExpense;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long netNewSubscriptions;
    private long totalNumberOfExistingSubscriptions;
    private double percentageSubscriptionChurn;
    private double startingMRR;
    private double grossMargin;
    private double percentageGrossMargin;
    private double totalOperationalExpenses;
    private double operatingProfit;
    private double percentageOperatingProfit;
    private double totalNewMRR;
    private double totalChurnedMRR;
    private double percentageMRRChurn;
    private double endingMRR;
    private double netNewMRR;
    private double arr;
    private double purchaseCost;
    private double revenue;
    private double averageRevenuePerNewSubscriber;
    private double averageRevenuePerSubscriber;
    private double subscriberLifetimeValue;
    private double subscriberLifetimePeriod;
    private double costOfAcquiringASubscriber;
    private double SLVToCASRatio;
    private double monthsToRecoverCAS;
    private double salesAndMarketingExpenses;

    public ProductActualMetricsView(ProductVersionId productVersionId, LocalDate endDate) {
        this.productVersionId= productVersionId;
        this.endDate=endDate;
        taggedPriceVersions= new TreeSet<>();
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

    public double getPercentageSubscriptionChurn() {
        return percentageSubscriptionChurn;
    }

    public void setPercentageSubscriptionChurn(double percentageSubscriptionChurn) {
        this.percentageSubscriptionChurn = percentageSubscriptionChurn;
    }

    public double getStartingMRR() {
        return startingMRR;
    }

    public void setStartingMRR(double startingMRR) {
        this.startingMRR = startingMRR;
    }

    public double getTotalNewMRR() {
        return totalNewMRR;
    }

    public void setTotalNewMRR(double totalNewMRR) {
        this.totalNewMRR = totalNewMRR;
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

    public double getSubscriberLifetimeValue() {
        return subscriberLifetimeValue;
    }

    public void setSubscriberLifetimeValue(double subscriberLifetimeValue) {
        this.subscriberLifetimeValue = subscriberLifetimeValue;
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

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public ProductVersionId getProductVersionId() {
        return productVersionId;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public long getNetNewSubscriptions() {
        return netNewSubscriptions;
    }

    public void setNetNewSubscriptions(long netNewSubscriptions) {
        this.netNewSubscriptions = netNewSubscriptions;
    }


    public double getArr() {
        return arr;
    }

    public void setArr(double arr) {
        this.arr = arr;
    }

    public PriceTaggedWithProduct getLatestTaggedPriceVersion(){
        PriceTaggedWithProduct latestTaggedPriceVersion=null;
        for ( PriceTaggedWithProduct tempTaggedPrice: this.taggedPriceVersions){
            if(null== latestTaggedPriceVersion || tempTaggedPrice.getTaggedStartDate().isAfter(latestTaggedPriceVersion.getTaggedStartDate())){
                latestTaggedPriceVersion=tempTaggedPrice;
            }
        }
        return latestTaggedPriceVersion;
    }

    public void addToNewSubscriptions(int subscriptionCount){
        this.newSubscriptions=this.newSubscriptions+subscriptionCount;
        this.totalNumberOfExistingSubscriptions +=subscriptionCount;
    }

    public void addToChurnedSubscriptions( int subscriptionCount){
        this.churnedSubscriptions=this.churnedSubscriptions + subscriptionCount;
        this.totalNumberOfExistingSubscriptions -=subscriptionCount;
    }

}
