package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.*;
import com.affaince.subscription.product.command.event.*;
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
import java.util.stream.Collectors;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductAccount extends AbstractAnnotatedEntity {
    private SortedSet<PriceTaggedWithProduct> taggedPriceVersions;
    private SortedSet<FixedExpensePerProduct> fixedExpenseVersions;
    private SortedSet<VariableExpensePerProduct> variableExpenseVersions;
    private Map<LocalDateTime, PriceBucket> recommendedPriceBuckets;
    private Map<LocalDateTime, PriceBucket> activePriceBuckets;
    private long currentStockInUnits;
    private ProductPricingCategory productPricingCategory;
    private double creditPoints;
    private double variableExpenseSlope;

    public ProductAccount(String productId, ProductPricingCategory productPricingCategory) {
        activePriceBuckets = new TreeMap<>();
        taggedPriceVersions = new TreeSet<>();
        fixedExpenseVersions = new TreeSet<>();
        variableExpenseVersions = new TreeSet<>();
        this.productPricingCategory = productPricingCategory;
        if (productPricingCategory == ProductPricingCategory.NO_COMMITMENT) {
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            LocalDateTime currentDate = SysDateTime.now();
            final String priceBucketId = productId + currentDate.toString(format);
            PriceBucket nonCommittedPriceBucket = new PriceBucket(productId, priceBucketId);
            activePriceBuckets.put(currentDate, nonCommittedPriceBucket);
        }
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
        if (this.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT && getLatestActivePriceBucket().getOfferedPriceOrPercentDiscountPerUnit() != offeredPriceOrPercent) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MMddyyyyHHmmsss");
            String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);
            //assumption is that for non commitment a single price bucket will exist in map.
            PriceBucket priceBucket = this.activePriceBuckets.entrySet().iterator().next().getValue();
            priceBucket.setTaggedPriceVersion(taggedPriceVersion);
            priceBucket.setOfferedPriceOrPercentDiscountPerUnit(offeredPriceOrPercent);
            priceBucket.setEntityStatus(entityStatus);
            priceBucket.setFromDate(fromDate);
            //activePriceBuckets.put(LocalDate.now(),newPriceBucket);
            return priceBucket;
        } else if (this.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT && getLatestActivePriceBucket().getOfferedPriceOrPercentDiscountPerUnit() != offeredPriceOrPercent) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MMddyyyyHHmmsss");
            String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);
            PriceBucket newPriceBucket = new PriceBucket(productId, priceBucketId, taggedPriceVersion, offeredPriceOrPercent, entityStatus, fromDate);
            //activePriceBuckets.put(LocalDate.now(),newPriceBucket);
            return newPriceBucket;

        } else if (this.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT && getLatestActivePriceBucket().getOfferedPriceOrPercentDiscountPerUnit() != offeredPriceOrPercent) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MMddyyyyHHmmsss");
            String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);
            PriceBucket newPriceBucket = new PriceBucket(productId, priceBucketId, taggedPriceVersion, offeredPriceOrPercent, entityStatus, fromDate);
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

    public void addNewPriceBucket(LocalDateTime date, PriceBucket priceBucket) {
        PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        closePriceBucketForSubscription(latestPriceBucket, date.minusMillis(1));
        activePriceBuckets.put(date, priceBucket);
    }

    public void addNewPriceRecommendation(LocalDateTime date, PriceBucket forecastedPriceBucket) {
        recommendedPriceBuckets.put(date, forecastedPriceBucket);
    }

    public PriceBucket getLatestActivePriceBucket() {
        Set<LocalDateTime> timeBasedKeys = activePriceBuckets.keySet();
        LocalDateTime max = null;
        for (LocalDateTime time : timeBasedKeys) {
            if (time.compareTo(max) > 0) {
                max = time;
            }
        }
        return activePriceBuckets.get(max);
    }

    public PriceBucket getLatestRecommendedPriceBucket() {
        Set<LocalDateTime> timeBasedKeys = recommendedPriceBuckets.keySet();
        LocalDateTime max = null;
        for (LocalDateTime time : timeBasedKeys) {
            if (time.compareTo(max) > 0) {
                max = time;
            }
        }
        return recommendedPriceBuckets.get(max);
    }

    public void addNewTaggedPriceVersion(PriceTaggedWithProduct newTaggedPrice) {
        this.taggedPriceVersions.add(newTaggedPrice);
    }

    public void addNewFixedExpense(FixedExpensePerProduct newFixedExpense) {
        this.fixedExpenseVersions.add(newFixedExpense);
    }

    public void addNewVariableExpense(VariableExpensePerProduct newVariableExpense) {
        this.variableExpenseVersions.add(newVariableExpense);
    }

    public PriceTaggedWithProduct getLatestTaggedPriceVersion() {
        return taggedPriceVersions.first();
    }

    public FixedExpensePerProduct getLatestFixedExpenseVersion() {
        return fixedExpenseVersions.first();
    }

    public VariableExpensePerProduct getLatestVariableExpenseVersion() {
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
        apply(new DeliveryExpenseUpdatedToProductEvent(command.getProductId(), SysDateTime.now(), command.getOperationExpense()));
    }

    public void updateFixedExpenses(UpdateFixedExpenseToProductCommand command) {
        apply(new FixedExpenseUpdatedToProductEvent(command.getProductId(), SysDate.now(), command.getOperationExpense()));
    }

    public double findLatestFixedExpensePerUnitInDateRange(LocalDateTime fromDate, LocalDateTime toDate) {
        FixedExpensePerProduct latestFixedExpense = null;
        for (FixedExpensePerProduct fixedExp : fixedExpenseVersions) {
            if (fixedExp.getStartDate().isAfter(fromDate) && (fixedExp.getStartDate().isBefore(toDate))) {
                if (null == latestFixedExpense || fixedExp.getStartDate().isAfter(latestFixedExpense.getStartDate())) {
                    latestFixedExpense = fixedExp;
                }
            }
        }
        return latestFixedExpense.getFixedOperatingExpPerUnit();
    }

    public double findLatestVariableExpensePerUnitInDateRange(LocalDateTime fromDate, LocalDateTime toDate) {
        VariableExpensePerProduct latestVariableExpense = null;
        for (VariableExpensePerProduct variableExp : variableExpenseVersions) {
            if (variableExp.getStartDate().isAfter(fromDate) && (variableExp.getStartDate().isBefore(toDate))) {
                if (null == latestVariableExpense || variableExp.getStartDate().isAfter(latestVariableExpense.getStartDate())) {
                    latestVariableExpense = variableExp;
                }
            }
        }
        return latestVariableExpense.getVariableOperatingExpPerUnit();

    }

    public List<PriceBucket> findBucketsWithSamePurchasePrice(PriceBucket priceBucket) {
        //final PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
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

    public double calculateExpectedProfitForPriceBucket(PriceBucket priceBucket) {
        LocalDateTime fromDate = priceBucket.getFromDate();
        LocalDateTime toDate = priceBucket.getToDate();
        double fixedExpensePerUnit = this.findLatestFixedExpensePerUnitInDateRange(fromDate, toDate);
        double variableExpensePerUnit = this.findLatestVariableExpensePerUnitInDateRange(fromDate, toDate);
        double profit = priceBucket.getNumberOfExistingSubscriptions() * priceBucket.getOfferedPriceOrPercentDiscountPerUnit()
                - (priceBucket.getNumberOfExistingSubscriptions() * (priceBucket.getTaggedPriceVersion().getPurchasePricePerUnit())
                + fixedExpensePerUnit + variableExpensePerUnit);
        return profit;
    }

    public void registerOpeningPrice(String productId, double openingPriceOrPercent) {
        apply(new OpeningPriceOrPercentRegisteredEvent(productId, openingPriceOrPercent));
    }


    public void updateProductSubscription(UpdateProductSubscriptionCommand command) {
        apply(new ProductSubscriptionUpdatedEvent(command.getProductId(), command.getProductSubscriptionCount(), command.getSubscriptionActivateDate()));
    }

    //Product status should be received from main application
    public void updateProductStatus(UpdateProductStatusCommand command) {
        apply(new ProductStatusUpdatedEvent(command.getProductId(), command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    //for receipt from main application thru integration
    public void receiveProductStatus(ReceiveProductStatusCommand command) {
        apply(new ProductStatusUpdatedEvent(command.getProductId(), command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
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

    //remove recommended price bucket when recommendation has served its purpose;
    public void removeRecommendedPriceBucket(PriceBucket priceBucket) {
        recommendedPriceBuckets.remove(priceBucket.getFromDate());
    }

    public void closePriceBucketForSubscription(PriceBucket priceBucket, LocalDateTime endDate) {
        Set<Map.Entry<LocalDateTime, PriceBucket>> latestPriceBuckets = activePriceBuckets.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(priceBucket))
                .collect(Collectors.toMap((p) -> p.getKey(), (entry) -> entry.getValue())).entrySet();
        latestPriceBuckets.iterator().next().getValue().setToDate(endDate);
    }

    @EventSourcingHandler
    public void on(DeliveryExpenseUpdatedToProductEvent event) {
        //get latest deliveryExpense
        VariableExpensePerProduct latestVariableExpense = variableExpenseVersions.first();
        if (latestVariableExpense.getVariableOperatingExpPerUnit() != event.getOperationExpense()) {
            VariableExpensePerProduct newVariableExpenseVersion = new VariableExpensePerProduct(event.getOperationExpense(), SysDate.now());
            variableExpenseVersions.add(newVariableExpenseVersion);
        }
    }


    @EventSourcingHandler
    public void on(FixedExpenseUpdatedToProductEvent event) {
        //get latest deliveryExpense
        FixedExpensePerProduct latestFixedExpense = fixedExpenseVersions.first();
        if (latestFixedExpense.getFixedOperatingExpPerUnit() != event.getOperationExpense()) {
            FixedExpensePerProduct newFixedExpenseVersion = new FixedExpensePerProduct(event.getOperationExpense(), SysDate.now());
            fixedExpenseVersions.add(newFixedExpenseVersion);
        }
    }

    @EventSourcingHandler
    public void on(ProductSubscriptionUpdatedEvent event) {
        PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        latestPriceBucket.setNumberOfNewSubscriptions(latestPriceBucket.getNumberOfNewSubscriptions() + event.getSubscriptionCount());
        double expectedProfitPerPriceBucket = this.calculateExpectedProfitForPriceBucket(latestPriceBucket);
        latestPriceBucket.setExpectedProfit(expectedProfitPerPriceBucket);

    }

    //Only for actuals
    //Product status should be received from main application.
    //when the purchase price is changed --it should update new taggedPriceVersion and make all price buckets point to it.
    @EventSourcingHandler
    public void on(ProductStatusReceivedEvent event) {
        final String productId = event.getProductId();
        final PriceTaggedWithProduct currentTaggedPriceVersion = this.getLatestTaggedPriceVersion();
        if (currentTaggedPriceVersion.getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            currentTaggedPriceVersion.setTaggedEndDate(SysDateTime.now());
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            final String taggedPriceVersionId = productId + event.getCurrentPriceDate().toString(format);
            PriceTaggedWithProduct newtaggedPrice = new PriceTaggedWithProduct(taggedPriceVersionId, event.getCurrentPurchasePrice(), event.getCurrentMRP(), event.getCurrentPriceDate());
            this.addNewTaggedPriceVersion(newtaggedPrice);
        }
        this.setCurrentStockInUnits(event.getCurrentStockInUnits());
    }

    //Only for actuals
    //Product status should be received from main application.
    //when the purchase price is changed --it should create new price bucket,by stalling all existing price buckets
    @EventSourcingHandler
    public void on(ProductStatusUpdatedEvent event) {
        String productId = event.getProductId();
        //PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        if (this.getLatestTaggedPriceVersion().getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            final String taggedPriceVersionId = productId + event.getCurrentPriceDate().toString(format);
            PriceTaggedWithProduct newtaggedPrice = new PriceTaggedWithProduct(taggedPriceVersionId, event.getCurrentPurchasePrice(), event.getCurrentMRP(), event.getCurrentPriceDate());
            this.addNewTaggedPriceVersion(newtaggedPrice);
        }
        this.setCurrentStockInUnits(event.getCurrentStockInUnits());
    }

}
