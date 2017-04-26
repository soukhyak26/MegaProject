package com.affaince.subscription.product.query.performance;

import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by mandark on 02-01-2016.
 */
public class AggregationPerformanceTracker {
    private String productId;
    private LocalDate executionDate;
    private YearMonth earlierMonthOfYear;
    private double expectedMerchantProfitPercentage;
    private double grossMargin;
    private double percentageGrossMargin;
    private double totalOperationalExpenses;
    private double operatingProfit;
    private double percentageOperatingProfit;
    private long netNewSusbcriptions;
    private long totalNumberOfChurnedSubscriptions;
    private long totalNumberOfExistingSubscriptions;
    private double percentageCustomerChurn;
    private double startingMRR;
    private Map<PriceBucket, Double> newMRRPerPriceBucket;
    private Map<PriceBucket, Double> chrunedMRRPerPriceBucket;
    private double totalChurnedMRR;
    private double percentageMRRChurn;
    private double endingMRR;
    private double netNewMRR;
    private double averageRevenuePerNewSubscriber;
    private double averageRevenuePerSubscriber;
    private double subscriberLifetimeValue;
    private double subscriberLifetimePeriod;
    private double costOfAcquiringASubscriber;
    private double SLVToCASRatio;
    private double monthsToRecoverCAS;
    private double salesAndMarketingExpenses;

    @Autowired
    ProductActualsViewRepository productActualsViewRepository;

    public AggregationPerformanceTracker(String productId,LocalDate executionDate){
        this.executionDate=executionDate;
        this.earlierMonthOfYear=new YearMonth(executionDate.getYear(),executionDate.getMonthOfYear()).minusMonths(1);
        this.productId=productId;
    }

    public void calculateMonthlyMetrics(){
        LocalDate datetEarlierMonth = new LocalDate(earlierMonthOfYear.get(DateTimeFieldType.year()), earlierMonthOfYear.get(DateTimeFieldType.monthOfYear()),1);
        LocalDate lastDayOfEarlierMonth=datetEarlierMonth.dayOfMonth().withMaximumValue();

        List<ProductActualsView> monthlyRangeOfActualViews= productActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(this.productId,lastDayOfEarlierMonth,this.executionDate);
        //List<ProductActualsView> currentActualsViews= productActualsViewRepository.findByProductVersionId_ProductIdAndEndDate(this.productId,this.executionDate);
        long lastMonthNewSubscriptions=0;
        long lastMonthChurnedSubscriptions=0;
        long lastMonthTotalSubscriptions=0;
        long totalMonthlySubscriptions=0;
        long totalNewSubscriptions=0;
        long totalChurnedSubscriptions=0;
            for(ProductActualsView dailyActualsView:monthlyRangeOfActualViews ){
                    if(dailyActualsView.getEndDate().equals(lastDayOfEarlierMonth)){
                        lastMonthTotalSubscriptions=dailyActualsView.getTotalNumberOfExistingSubscriptions();
                    }else {
                        if (dailyActualsView.getEndDate().equals(executionDate)) {
                            long currentTotalSubscriptions = dailyActualsView.getTotalNumberOfExistingSubscriptions();
                            totalMonthlySubscriptions = currentTotalSubscriptions - lastMonthTotalSubscriptions;
                        }
                        totalNewSubscriptions+=dailyActualsView.getNewSubscriptions();
                        totalChurnedSubscriptions +=dailyActualsView.getChurnedSubscriptions();
                    }

            }
    }
    public long getNetNewSusbcriptions() {
        return netNewSusbcriptions;
    }

    public void setNetNewSusbcriptions(long netNewSusbcriptions) {
        this.netNewSusbcriptions = netNewSusbcriptions;
    }

    public long getTotalNumberOfChurnedSubscriptions() {
        return totalNumberOfChurnedSubscriptions;
    }

    public void setTotalNumberOfChurnedSubscriptions(long totalNumberOfChurnedSubscriptions) {
        this.totalNumberOfChurnedSubscriptions = totalNumberOfChurnedSubscriptions;
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

    public double getTotalOperationalExpenses() {
        return totalOperationalExpenses;
    }

}
