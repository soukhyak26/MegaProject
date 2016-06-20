package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.product.command.UpdateFixedExpenseToProductCommand;
import com.affaince.subscription.product.command.event.DeliveryExpenseUpdatedToProductEvent;
import com.affaince.subscription.product.command.event.FixedExpenseUpdatedToProductEvent;
import com.affaince.subscription.product.command.event.ProductSubscriptionUpdatedEvent;
import com.affaince.subscription.product.command.UpdateDeliveryExpenseToProductCommand;
import com.affaince.subscription.product.command.UpdateProductSubscriptionCommand;
import com.affaince.subscription.product.vo.FixedExpensePerProduct;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.VariableExpensePerProduct;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

import java.util.*;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductAccount extends AbstractAnnotatedEntity {
    private SortedSet<PriceTaggedWithProduct> taggedPriceVersions;
    private SortedSet<FixedExpensePerProduct> fixedExpenseVersions;
    private SortedSet<VariableExpensePerProduct> variableExpenseVersions;
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
        taggedPriceVersions = new TreeSet<>();
        fixedExpenseVersions= new TreeSet<>();
        variableExpenseVersions= new TreeSet<>();
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
    public void addNewTaggedPriceVersion(PriceTaggedWithProduct newTaggedPrice){
        this.taggedPriceVersions.add(newTaggedPrice);
    }
    public void addNewFixedExpense(FixedExpensePerProduct newFixedExpense){
        this.fixedExpenseVersions.add(newFixedExpense);
    }
    public void addNewVariableExpense(VariableExpensePerProduct newVariableExpense){
        this.variableExpenseVersions.add(newVariableExpense);
    }
    public PriceTaggedWithProduct getLatestTaggedPriceVersion(){
       return taggedPriceVersions.first();
    }

    public FixedExpensePerProduct getLatestFixedExpenseVersion(){
        return fixedExpenseVersions.first();
    }

    public VariableExpensePerProduct getLatestVariableExpenseVersion(){
        return variableExpenseVersions.first();
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

    public void updateSubscriptionSpecificExpenses(UpdateDeliveryExpenseToProductCommand command) {
        apply(new DeliveryExpenseUpdatedToProductEvent(command.getProductId(), LocalDate.now(), command.getOperationExpense()));
    }

    public void updateFixedExpenses(UpdateFixedExpenseToProductCommand command) {
        apply(new FixedExpenseUpdatedToProductEvent(command.getProductId(), LocalDate.now(), command.getOperationExpense()));
    }

    @EventSourcingHandler
    public void on (DeliveryExpenseUpdatedToProductEvent event) {
        //get latest deliveryExpense
        VariableExpensePerProduct latestVariableExpense = variableExpenseVersions.first();
        if(latestVariableExpense.getVariableOperatingExpPerUnit() != event.getOperationExpense()){
            VariableExpensePerProduct newVariableExpenseVersion= new VariableExpensePerProduct(event.getOperationExpense(),LocalDate.now());
            variableExpenseVersions.add(newVariableExpenseVersion);
        }
    }


    @EventSourcingHandler
    public void on (FixedExpenseUpdatedToProductEvent event) {
        //get latest deliveryExpense
        FixedExpensePerProduct latestFixedExpense = fixedExpenseVersions.first();
        if(latestFixedExpense.getFixedOperatingExpPerUnit() != event.getOperationExpense()){
            FixedExpensePerProduct newFixedExpenseVersion= new FixedExpensePerProduct(event.getOperationExpense(),LocalDate.now());
            fixedExpenseVersions.add(newFixedExpenseVersion);
        }
    }

    public double findLatestFixedExpensePerUnitInDateRange(LocalDate fromDate,LocalDate toDate){
        FixedExpensePerProduct latestFixedExpense=null;
        for(FixedExpensePerProduct fixedExp: fixedExpenseVersions){
         if(fixedExp.getStartDate().isAfter(fromDate) &&(fixedExp.getStartDate().isBefore(toDate))){
                if( null== latestFixedExpense || fixedExp.getStartDate().isAfter(latestFixedExpense.getStartDate())){
                    latestFixedExpense=fixedExp;
                }
         }
        }
        return latestFixedExpense.getFixedOperatingExpPerUnit();
    }
    public double findLatestVariableExpensePerUnitInDateRange(LocalDate fromDate,LocalDate toDate){
        VariableExpensePerProduct latestVariableExpense=null;
        for(VariableExpensePerProduct variableExp: variableExpenseVersions){
            if(variableExp.getStartDate().isAfter(fromDate) &&(variableExp.getStartDate().isBefore(toDate))){
                if( null== latestVariableExpense || variableExp.getStartDate().isAfter(latestVariableExpense.getStartDate())){
                    latestVariableExpense=variableExp;
                }
            }
        }
        return latestVariableExpense.getVariableOperatingExpPerUnit();

    }
    public double calculateExpectedProfitForPriceBucket(PriceBucket priceBucket){
        LocalDate fromDate = priceBucket.getFromDate();
        LocalDate toDate= priceBucket.getToDate();
        double fixedExpensePerUnit= this.findLatestFixedExpensePerUnitInDateRange(fromDate,toDate);
        double variableExpensePerUnit= this.findLatestVariableExpensePerUnitInDateRange(fromDate,toDate);
        double profit = priceBucket.getNumberOfExistingSubscriptions()*priceBucket.getOfferedPricePerUnit()
                -(priceBucket.getNumberOfExistingSubscriptions()*(priceBucket.getTaggedPriceVersion().getPurchasePricePerUnit())
                + fixedExpensePerUnit + variableExpensePerUnit );
        return profit;
    }


    public void updateProductSubscription(UpdateProductSubscriptionCommand command) {
        PriceBucket latestPriceBucket= this.getLatestPriceBucket();
        latestPriceBucket.setNumberOfNewSubscriptions(latestPriceBucket.getNumberOfNewSubscriptions()+command.getProductSubscriptionCount());
        double expectedProfitPerPriceBucket=this.calculateExpectedProfitForPriceBucket(latestPriceBucket);
        latestPriceBucket.setExpectedProfit(expectedProfitPerPriceBucket);
        apply(new ProductSubscriptionUpdatedEvent(command.getProductId(),command.getProductSubscriptionCount(),command.getSubscriptionActivateDate(),expectedProfitPerPriceBucket));
    }
}
