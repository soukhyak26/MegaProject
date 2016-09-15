package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.UpdateDeliveryExpenseToProductCommand;
import com.affaince.subscription.product.command.UpdateFixedExpenseToProductCommand;
import com.affaince.subscription.product.command.UpdateProductSubscriptionCommand;
import com.affaince.subscription.product.command.event.DeliveryExpenseUpdatedToProductEvent;
import com.affaince.subscription.product.command.event.FixedExpenseUpdatedToProductEvent;
import com.affaince.subscription.product.command.event.ProductSubscriptionUpdatedEvent;
import com.affaince.subscription.product.vo.FixedExpensePerProduct;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.VariableExpensePerProduct;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductAccount extends AbstractAnnotatedEntity {
    private SortedSet<PriceTaggedWithProduct> taggedPriceVersions;
    private SortedSet<FixedExpensePerProduct> fixedExpenseVersions;
    private SortedSet<VariableExpensePerProduct> variableExpenseVersions;
    private Map<LocalDateTime, PriceBucket> activePriceBuckets;
    private long currentStockInUnits;
    private ProductPricingCategory productPricingCategory;
    private double creditPoints;
    private double variableExpenseSlope;

    public ProductAccount() {
        activePriceBuckets = new TreeMap<>();
        taggedPriceVersions = new TreeSet<>();
        fixedExpenseVersions= new TreeSet<>();
        variableExpenseVersions= new TreeSet<>();
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


    public PriceBucket findActivePriceBucketByDate(LocalDate dateIdentifier) {
        return this.activePriceBuckets.get(dateIdentifier);
    }

    public PriceBucket createNewPriceBucket(String productId, PriceTaggedWithProduct taggedPriceVersion, double offeredPriceOrPercent, EntityStatus entityStatus, LocalDateTime fromDate) {
        //LocalDateTime fromDate = LocalDateTime.now();
        if (this.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT && getLatestPriceBucket().getOfferedPricePerUnit() != offeredPriceOrPercent) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MM-dd-yyyy-HH-mm-sss");
            String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);
            PriceBucket newPriceBucket = new PriceBucketForNoneCommitment(productId, priceBucketId, taggedPriceVersion, offeredPriceOrPercent, entityStatus, fromDate);
            //activePriceBuckets.put(LocalDate.now(),newPriceBucket);
            return newPriceBucket;
        } else if (this.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT && getLatestPriceBucket().getOfferedPricePerUnit() != offeredPriceOrPercent) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MM-dd-yyyy-HH-mm-sss");
            String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);
            PriceBucket newPriceBucket = new PriceBucketForPercentDiscountCommitment(productId, priceBucketId, taggedPriceVersion, offeredPriceOrPercent, entityStatus, fromDate);
            //activePriceBuckets.put(LocalDate.now(),newPriceBucket);
            return newPriceBucket;

        } else if (this.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT && getLatestPriceBucket().getOfferedPricePerUnit() != offeredPriceOrPercent) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MM-dd-yyyy-HH-mm-sss");
            String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);
            PriceBucket newPriceBucket = new PriceBucketForPriceCommitment(productId, priceBucketId, taggedPriceVersion, offeredPriceOrPercent, entityStatus, fromDate);
            //activePriceBuckets.put(LocalDate.now(),newPriceBucket);
            return newPriceBucket;
        } else {
            return null;
        }
    }

    public Map<LocalDateTime, PriceBucket> getActivePriceBuckets() {
        return activePriceBuckets;
    }

    public void setActivePriceBuckets(Map<LocalDateTime, PriceBucket> activePriceBuckets) {
        this.activePriceBuckets = activePriceBuckets;
    }

    public void addNewPriceBucket(LocalDateTime date, PriceBucket forecastedPriceBucket) {
        activePriceBuckets.put(date, forecastedPriceBucket);
    }

    public PriceBucket getLatestPriceBucket() {
        Set<LocalDateTime> timeBasedKeys = activePriceBuckets.keySet();
        LocalDateTime max = null;
        for (LocalDateTime time : timeBasedKeys) {
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

    public double getVariableExpenseSlope() {
        return this.variableExpenseSlope;
    }

    public void setVariableExpenseSlope(double variableExpenseSlope) {
        this.variableExpenseSlope = variableExpenseSlope;
    }

    public void updateSubscriptionSpecificExpenses(UpdateDeliveryExpenseToProductCommand command) {
        apply(new DeliveryExpenseUpdatedToProductEvent(command.getProductId(), LocalDateTime.now(), command.getOperationExpense()));
    }

    public void updateFixedExpenses(UpdateFixedExpenseToProductCommand command) {
        apply(new FixedExpenseUpdatedToProductEvent(command.getProductId(), SysDate.now(), command.getOperationExpense()));
    }

    @EventSourcingHandler
    public void on (DeliveryExpenseUpdatedToProductEvent event) {
        //get latest deliveryExpense
        VariableExpensePerProduct latestVariableExpense = variableExpenseVersions.first();
        if(latestVariableExpense.getVariableOperatingExpPerUnit() != event.getOperationExpense()){
            VariableExpensePerProduct newVariableExpenseVersion = new VariableExpensePerProduct(event.getOperationExpense(), SysDate.now());
            variableExpenseVersions.add(newVariableExpenseVersion);
        }
    }


    @EventSourcingHandler
    public void on (FixedExpenseUpdatedToProductEvent event) {
        //get latest deliveryExpense
        FixedExpensePerProduct latestFixedExpense = fixedExpenseVersions.first();
        if(latestFixedExpense.getFixedOperatingExpPerUnit() != event.getOperationExpense()){
            FixedExpensePerProduct newFixedExpenseVersion = new FixedExpensePerProduct(event.getOperationExpense(), SysDate.now());
            fixedExpenseVersions.add(newFixedExpenseVersion);
        }
    }

    public double findLatestFixedExpensePerUnitInDateRange(LocalDateTime fromDate, LocalDateTime toDate) {
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

    public double findLatestVariableExpensePerUnitInDateRange(LocalDateTime fromDate, LocalDateTime toDate) {
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

    public List<PriceBucket> findBucketsWithSamePurchasePrice(PriceBucket priceBucket) {
        //final PriceBucket latestPriceBucket = this.getLatestPriceBucket();
        List<PriceBucket> bucketsWithSamePurchasePrice = new ArrayList<PriceBucket>();
        Iterator<Map.Entry<LocalDateTime, PriceBucket>> priceBucketIterator = activePriceBuckets.entrySet().iterator();
        while (priceBucketIterator.hasNext()) {
            PriceBucket currentBucket = priceBucketIterator.next().getValue();
            if (currentBucket.getTaggedPriceVersion().getPurchasePricePerUnit() == priceBucket.getTaggedPriceVersion().getPurchasePricePerUnit()) {
                bucketsWithSamePurchasePrice.add(currentBucket);
            }
        }
        return bucketsWithSamePurchasePrice;
    }

    public double calculateExpectedProfitForPriceBucket(PriceBucket priceBucket){
        LocalDateTime fromDate = priceBucket.getFromDate();
        LocalDateTime toDate = priceBucket.getToDate();
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

    public PriceBucket findEarlierPriceBucketInActivePriceBuckets(PriceBucket priceBucket) {
        PriceBucket earlierPriceBucket = null;
        List<PriceBucket> earlierPriceBuckets = new ArrayList<PriceBucket>();
        LocalDateTime latestBucketDate = priceBucket.getFromDate();
        Iterator<Map.Entry<LocalDateTime, PriceBucket>> priceBucketIterator = activePriceBuckets.entrySet().iterator();

        while (priceBucketIterator.hasNext()) {
            PriceBucket tempPriceBucket = priceBucketIterator.next().getValue();
            if (tempPriceBucket.getFromDate().isBefore(latestBucketDate)) {
                if (null != earlierPriceBucket) {
                    if (tempPriceBucket.getFromDate().isAfter(earlierPriceBucket.getFromDate())) {
                        earlierPriceBucket = tempPriceBucket;
                    }
                } else {
                    earlierPriceBucket = tempPriceBucket;
                }
            }
        }

        return earlierPriceBucket;
    }

    public PriceBucket findEarlierPriceBucketTo(PriceBucket priceBucket, List<PriceBucket> priceBuckets) {
        PriceBucket earlierPriceBucket = null;
        List<PriceBucket> earlierPriceBuckets = new ArrayList<PriceBucket>();
        LocalDateTime latestBucketDate = priceBucket.getFromDate();
        Iterator<PriceBucket> priceBucketIterator = priceBuckets.iterator();

        while (priceBucketIterator.hasNext()) {
            PriceBucket tempPriceBucket = priceBucketIterator.next();
            if (tempPriceBucket.getFromDate().isBefore(latestBucketDate)) {
                if (null != earlierPriceBucket) {
                    if (tempPriceBucket.getFromDate().isAfter(earlierPriceBucket.getFromDate())) {
                        earlierPriceBucket = tempPriceBucket;
                    }
                } else {
                    earlierPriceBucket = tempPriceBucket;
                }
            }
        }

        return earlierPriceBucket;
    }

}
