package com.affaince.subscription.product.query.performance;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.query.repository.FixedExpensePerProductViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.repository.VariableExpensePerProductViewRepository;
import com.affaince.subscription.product.query.view.FixedExpensePerProductView;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.VariableExpensePerProductView;
import org.apache.tomcat.jni.Local;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
    private ProductActualsViewRepository productActualsViewRepository;
    @Autowired
    private FixedExpensePerProductViewRepository fixedExpensePerProductViewRepository;
    @Autowired
    private VariableExpensePerProductViewRepository variableExpensePerProductViewRepository;
    @Autowired
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;

    public AggregationPerformanceTracker(String productId,LocalDate executionDate){
        this.executionDate=executionDate;
        this.earlierMonthOfYear=new YearMonth(executionDate.getYear(),executionDate.getMonthOfYear()).minusMonths(1);
        this.productId=productId;
    }

    public void calculateMonthlyMetrics(){
        LocalDate datetEarlierMonth = new LocalDate(earlierMonthOfYear.get(DateTimeFieldType.year()), earlierMonthOfYear.get(DateTimeFieldType.monthOfYear()),1);
        LocalDate lastDayOfEarlierMonth=datetEarlierMonth.dayOfMonth().withMaximumValue();
        LocalDate firstDayOfCurrentMonth=lastDayOfEarlierMonth.plusDays(1);
        List<ProductActualsView> monthlyRangeOfActualViews= productActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(this.productId,lastDayOfEarlierMonth,this.executionDate);
        long lastMonthTotalSubscriptions=0;
        long totalMonthlySubscriptions=0;

        long totalNewSubscriptionsInAMonth=0;
        long totalChurnedSubscriptionsInAMonth=0;
            for(ProductActualsView dailyActualsView:monthlyRangeOfActualViews ){
                    if(dailyActualsView.getEndDate().equals(lastDayOfEarlierMonth)){
                        lastMonthTotalSubscriptions=dailyActualsView.getTotalNumberOfExistingSubscriptions();
                    }else {
                        if (dailyActualsView.getEndDate().equals(executionDate)) {
                            long currentTotalSubscriptions = dailyActualsView.getTotalNumberOfExistingSubscriptions();
                            totalMonthlySubscriptions = currentTotalSubscriptions - lastMonthTotalSubscriptions;
                        }
                        totalNewSubscriptionsInAMonth+=dailyActualsView.getNewSubscriptions();
                        totalChurnedSubscriptionsInAMonth +=dailyActualsView.getChurnedSubscriptions();
                    }
            }

            long netNewSubscriptions=totalNewSubscriptionsInAMonth - totalChurnedSubscriptionsInAMonth;
            ProductActualMetricsView productActualMetricsView = new ProductActualMetricsView(new ProductVersionId(productId,firstDayOfCurrentMonth),executionDate);
            productActualMetricsView.setNewSubscriptions(totalNewSubscriptionsInAMonth);
            productActualMetricsView.setChurnedSubscriptions(totalChurnedSubscriptionsInAMonth);
            productActualMetricsView.setTotalNumberOfExistingSubscriptions(totalMonthlySubscriptions);
            productActualMetricsView.setNetNewSubscriptions(netNewSubscriptions);
            double totalMonthlyFixedExpense=calculateTotalMonthlyFixedExpenses(productId,firstDayOfCurrentMonth,executionDate,monthlyRangeOfActualViews);
            double totalMonthlyVariableExpense=calculateMonthlyVariableExpenses(productId,firstDayOfCurrentMonth,executionDate,monthlyRangeOfActualViews);
            productActualMetricsView.setFixedOperatingExpense(totalMonthlyFixedExpense);
            productActualMetricsView.setVariableOperatingExpense(totalMonthlyVariableExpense);

    }

    private double calculateTotalMonthlyFixedExpenses(String productId,LocalDate firstDayOfMonth,LocalDate executionDate,List<ProductActualsView> monthlyRangeOfActualViews){
        //total fixed expenses
        List<FixedExpensePerProductView> fixedExpensePerProductVersions= fixedExpensePerProductViewRepository.findByProductwiseFixedExpenseId_ProductIdAndProductwiseFixedExpenseId_FromDateBetween(productId,firstDayOfMonth,executionDate);
        double totalMonthlyFixedExpenses=0;
        for(FixedExpensePerProductView fixedExpenseVersion:fixedExpensePerProductVersions ){
            LocalDate endDateOffixedExpense=fixedExpenseVersion.getEndDate();
            List<ProductActualsView> productActualsViewSubset=findMonthlyActualsViewsInDatesBetween(monthlyRangeOfActualViews,firstDayOfMonth,endDateOffixedExpense);
            ProductActualsView latestActualsView=findLatestProductActualsView(productActualsViewSubset);
            totalMonthlyFixedExpenses +=latestActualsView.getTotalNumberOfExistingSubscriptions()*fixedExpenseVersion.getFixedExpensePerProductPerUnit();
        }
        return totalMonthlyFixedExpenses;
    }

    private double calculateMonthlyVariableExpenses(String productId,LocalDate firstDayOfMonth,LocalDate executionDate,List<ProductActualsView> monthlyRangeOfActualViews){
        //total variable expense
        List<VariableExpensePerProductView> variableExpensePerProductVersions = variableExpensePerProductViewRepository.findByProductwiseVariableExpenseId_ProductIdAndProductwiseVariableExpenseId_FromDateBetween(productId,firstDayOfMonth,executionDate);
        double totalMonthlyVariableExpenses=0;
        for(VariableExpensePerProductView variableExpenseVersion :variableExpensePerProductVersions ){
            LocalDate endDateOfVariableExpense=variableExpenseVersion.getEndDate();
            List<ProductActualsView> productActualsViewSubset=findMonthlyActualsViewsInDatesBetween(monthlyRangeOfActualViews,firstDayOfMonth,endDateOfVariableExpense);
            ProductActualsView latestActualsView=findLatestProductActualsView(productActualsViewSubset);
            totalMonthlyVariableExpenses +=latestActualsView.getTotalNumberOfExistingSubscriptions()*variableExpenseVersion.getVariableExpensePerProductPerUnit();
        }
        return totalMonthlyVariableExpenses;

    }

    private List<ProductActualsView> findMonthlyActualsViewsInDatesBetween(List<ProductActualsView> monthlyActualsViews, LocalDate startDate, LocalDate endDate ){
            List<ProductActualsView> monthlyActualsViewInDateRange = new ArrayList<>(30);
            for(ProductActualsView productActualsView:monthlyActualsViews){
                LocalDate fromDate= productActualsView.getProductVersionId().getFromDate();
                LocalDate toDate= productActualsView.getEndDate();
                if((fromDate.isEqual(startDate)||fromDate.isAfter(startDate)) &&(toDate.isBefore(endDate)|| toDate.isEqual(endDate)) ){
                    monthlyActualsViewInDateRange.add(productActualsView);
                }
            }
            return monthlyActualsViewInDateRange;
    }

    private ProductActualsView findLatestProductActualsView(List<ProductActualsView> productActualsViews){
        ProductActualsView latestActualsView=productActualsViews.get(0);
        for(ProductActualsView actualsView:productActualsViews){
            if(actualsView.getEndDate().isAfter(latestActualsView.getEndDate())){
                latestActualsView=actualsView;
            }
        }
        return latestActualsView;
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
