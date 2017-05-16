package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.*;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.factory.PriceBucketFactory;
import com.affaince.subscription.product.services.operatingexpense.OperatingExpenseService;
import com.affaince.subscription.product.services.pricing.calculator.breakevenprice.BreakEvenPriceCalculator;
import com.affaince.subscription.product.vo.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
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
    private SortedSet<PriceTaggedWithProduct> taggedPriceVersions;// a version should go away when all pricebuckets associated with it are expired.??
    private SortedSet<FixedExpensePerProduct> fixedExpenseVersions;//should be reset annually. All but the latest one should vanish
    private SortedSet<VariableExpensePerProduct> variableExpenseVersions;//should be reset annually. All but the latest one should vanish
    @EventSourcedMember
    private Map<LocalDateTime, PriceBucket> recommendedPriceBuckets;// taken care of creation and expiration automatically
    @EventSourcedMember
    private Map<LocalDateTime, PriceBucket> activePriceBuckets;// taken care of creation and expiration automatically
    private long currentStockInUnits;// should be adjusted on each delivery made
    private ProductPricingCategory productPricingCategory;
    private double creditPoints;
    private double variableExpenseSlope;
    private double expectedPurchaseCost;
    private double expectedRevenue;
    private double expectedProfit;

    private double registeredPurchaseCost; //should be reset annually=pc of remaining stock + new purchase cost
    private double registeredRevenue;// should be reset annually =0;
    private double registeredProfit; // should be reset annually =0;

    // No event publishing as the constructor is invoked in event sourcing handler(ProductRegisteredEvent) of product
    public ProductAccount(String productId, ProductPricingCategory productPricingCategory) {
        activePriceBuckets = new TreeMap<>();
        taggedPriceVersions = new TreeSet<>();
        fixedExpenseVersions = new TreeSet<>();
        fixedExpenseVersions.add(new FixedExpensePerProduct(3.0, SysDate.now()));
        variableExpenseVersions = new TreeSet<>();
        variableExpenseVersions.add(new VariableExpensePerProduct(3.0, SysDate.now()));
        this.productPricingCategory = productPricingCategory;
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

    public SortedSet<PriceTaggedWithProduct> getTaggedPriceVersions() {
        return taggedPriceVersions;
    }

    public SortedSet<FixedExpensePerProduct> getFixedExpenseVersions() {
        return fixedExpenseVersions;
    }

    public SortedSet<VariableExpensePerProduct> getVariableExpenseVersions() {
        return variableExpenseVersions;
    }

    public Map<LocalDateTime, PriceBucket> getRecommendedPriceBuckets() {
        return recommendedPriceBuckets;
    }

    public double getRegisteredPurchaseCost() {
        return registeredPurchaseCost;
    }

    public double getRegisteredRevenue() {
        return registeredRevenue;
    }

    public double getRegisteredProfit() {
        return registeredProfit;
    }

    public PriceBucket findActivePriceBucketByDate(LocalDate dateIdentifier) {
        return this.activePriceBuckets.get(dateIdentifier);
    }

    //This is just a utility method being used by Price Calculators for creating a price bucket.. Hence it should not emit events.
    public PriceBucket createNewPriceBucket(String productId, PriceTaggedWithProduct taggedPriceVersion, double offeredPriceOrPercent, EntityStatus entityStatus, LocalDateTime fromDate) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MMddyyyyHHmmsss");
        String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);
        if (this.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT && findLatestActivePriceBucket().getFixedOfferedPriceOrPercentDiscountPerUnit() != offeredPriceOrPercent) {
            //assumption is that for non commitment a single price bucket will exist in map.
            if (null != this.activePriceBuckets && this.activePriceBuckets.size() > 0) {
                PriceBucket onlyPriceBucket= this.activePriceBuckets.entrySet().iterator().next().getValue();
                onlyPriceBucket.setOfferedPriceOrPercentDiscountPerUnit(offeredPriceOrPercent);
                return onlyPriceBucket;
            } else {
                return PriceBucketFactory.createPriceBucket(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercent, entityStatus, fromDate);
            }
        } else {
            return PriceBucketFactory.createPriceBucket(productId, priceBucketId, productPricingCategory, taggedPriceVersion, offeredPriceOrPercent, entityStatus, fromDate);
        }
    }

    public Map<LocalDateTime, PriceBucket> getActivePriceBuckets() {
        return activePriceBuckets;
    }

    public void setActivePriceBuckets(Map<LocalDateTime, PriceBucket> activePriceBuckets) {
        this.activePriceBuckets = activePriceBuckets;
    }

    public void addNewPriceBucket(LocalDateTime date, PriceBucket priceBucket) {
        PriceBucket latestPriceBucket = this.findLatestActivePriceBucket();
        if (latestPriceBucket != null) {
            closePriceBucketForSubscription(latestPriceBucket, date.minusMillis(1));
        }
        activePriceBuckets.put(date, priceBucket);
    }

    public void addNewPriceRecommendation(LocalDateTime date, PriceBucket forecastedPriceBucket) {
        recommendedPriceBuckets.put(date, forecastedPriceBucket);
    }

    public PriceBucket findLatestActivePriceBucket() {
        Set<LocalDateTime> timeBasedKeys = activePriceBuckets.keySet();
        if (timeBasedKeys.size() <= 0) {
            return null;
        }
        LocalDateTime max = timeBasedKeys.iterator().next();
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

    // This method is called from event sourcing handlers of ProductRegisteredEvent and ProductStatusUpdatedEvent
    public void addNewTaggedPriceVersion(PriceTaggedWithProduct newTaggedPrice) {
        this.taggedPriceVersions.add(newTaggedPrice);
    }

    //TODO: An event from BusinessAccount should give per product fixed expense and the same should get added to product account
    public void addNewFixedExpense(FixedExpensePerProduct newFixedExpense) {
        this.fixedExpenseVersions.add(newFixedExpense);
    }

    //TODO: An event from Subscriber should give per product variable expense and the same should get added to product account
    public void addNewVariableExpense(VariableExpensePerProduct newVariableExpense) {
        this.variableExpenseVersions.add(newVariableExpense);
    }

    public PriceTaggedWithProduct getLatestTaggedPriceVersion() {
        return taggedPriceVersions.first();
    }

    public FixedExpensePerProduct getLatestFixedExpenseVersion() {
        if (fixedExpenseVersions.isEmpty()) {
            return null;
        }
        return fixedExpenseVersions.first();
    }

    public VariableExpensePerProduct getLatestVariableExpenseVersion() {
        if (variableExpenseVersions.isEmpty()) {
            return null;
        }
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

    public void updateSubscriptionSpecificExpenses(UpdateDeliveryExpenseToProductCommand command, OperatingExpenseService operatingExpenseService) {
        VariableExpensePerProduct latestVariableExpense = variableExpenseVersions.first();
        if (null == latestVariableExpense || (null != latestVariableExpense && latestVariableExpense.getVariableOperatingExpPerUnit() != command.getOperationExpense())) {
            final LocalDate startDate = SysDate.now();
            VariableExpensePerProduct newVariableExpenseVersion = new VariableExpensePerProduct(command.getOperationExpense(), startDate);
            Set<PriceTaggedWithProduct> taggedPriceVersions = updateBreakEvenPriceUponChangeOfPriceOrExpenses(operatingExpenseService);
            apply(new VariableExpenseChangedEvent(command.getProductId(), startDate, newVariableExpenseVersion, taggedPriceVersions));
        }
    }

    public void updateFixedExpenses(UpdateFixedExpenseToProductCommand command, OperatingExpenseService operatingExpenseService) {
        //get latest deliveryExpense
        FixedExpensePerProduct latestFixedExpense = fixedExpenseVersions.first();
        if (latestFixedExpense.getFixedOperatingExpPerUnit() != command.getOperationExpense()) {
            FixedExpensePerProduct newFixedExpenseVersion = new FixedExpensePerProduct(command.getOperationExpense(), SysDate.now());
            Set<PriceTaggedWithProduct> taggedPriceVersions = updateBreakEvenPriceUponChangeOfPriceOrExpenses(operatingExpenseService);
            //fixedExpenseVersions.add(newFixedExpenseVersion);
/*
            PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
            latestTaggedPriceVersion.setBreakEvenPrice(updateBreakEvenPriceUponChangeOfPriceOrExpenses());
*/
            apply(new FixedExpenseChangedEvent(command.getProductId(), SysDate.now(), newFixedExpenseVersion, taggedPriceVersions));
        }


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

    public PriceBucket findActivePriceBucketByPriceBucketId(String priceBucketId) {
        return activePriceBuckets.values().stream().filter(item -> item.getPriceBucketId().equals(priceBucketId)).collect(Collectors.toList()).get(0);
    }

    public PriceBucket findRecommendedPriceBucketByPriceBucketId(String priceBucketId) {
        return recommendedPriceBuckets.values().stream().filter(item -> item.getPriceBucketId().equals(priceBucketId)).collect(Collectors.toList()).get(0);
    }

    public List<PriceBucket> findBucketsWithSamePurchasePrice(PriceBucket priceBucket) {
        //final PriceBucket latestPriceBucket = this.findLatestActivePriceBucket();
        List<PriceBucket> bucketsWithSamePurchasePrice = new ArrayList<PriceBucket>();
        Iterator<Map.Entry<LocalDateTime, PriceBucket>> priceBucketIterator = activePriceBuckets.entrySet().iterator();
        while (priceBucketIterator.hasNext()) {
            PriceBucket currentBucket = priceBucketIterator.next().getValue();
            if (currentBucket.getLatestTaggedPriceVersion().getPurchasePricePerUnit() == priceBucket.getLatestTaggedPriceVersion().getPurchasePricePerUnit()) {
                bucketsWithSamePurchasePrice.add(currentBucket);
            }
        }
        return bucketsWithSamePurchasePrice;
    }


    /*
        public void calculateExpectedPurchaseExpenseRevenueAndProfitForPriceBucket(String productId, String priceBucketId){
            final PriceBucket priceBucket=this.findActivePriceBucketByPriceBucketId(priceBucketId);
            LocalDateTime fromDate = priceBucket.getFromDate();
            LocalDateTime toDate = priceBucket.getToDate();
            double fixedExpensePerUnit = this.findLatestFixedExpensePerUnitInDateRange(fromDate, toDate);
            double variableExpensePerUnit = this.findLatestVariableExpensePerUnitInDateRange(fromDate, toDate);
            priceBucket.calculateExpectedPurchaseExpenseRevenueAndProfitForPriceBucket(productId,fixedExpensePerUnit,variableExpensePerUnit);
        }
    */
    public void calculateExpectedPurchaseExpenseRevenueAndProfitOnSubscriptionUpdate(String productId, Map<String, Integer> priceBucketWiseSubscriptionCount, LocalDate subscriptionChangeDate) {
        priceBucketWiseSubscriptionCount.keySet().stream().forEach(priceBucketId -> {
            final PriceBucket activePriceBucket = this.findActivePriceBucketByPriceBucketId(priceBucketId);
            final int subscriptionCount = priceBucketWiseSubscriptionCount.get(priceBucketId);
            double fixedExpensePerUnit = this.getLatestFixedExpenseVersion().getFixedOperatingExpPerUnit();
            double variableExpensePerUnit = this.getLatestVariableExpenseVersion().getVariableOperatingExpPerUnit();
            activePriceBucket.calculateExpectedPurchaseExpenseRevenueAndProfitForPriceBucket(productId, subscriptionCount, fixedExpensePerUnit, variableExpensePerUnit);
        });
    }

    public void calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(String productId, String priceBucketId, long deliveredSubscriptionCount, LocalDate dispatchDate) {
        final PriceBucket priceBucket = this.findActivePriceBucketByPriceBucketId(priceBucketId);

        //this.calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(productId,fixedExpensePerUnit,variableExpensePerUnit);
/*
        if(totalDeliveredSubscriptionCount==priceBucket.getNumberOfExistingSubscriptions()){
            apply(new PriceBucketExpiredEvent(productId,priceBucketId, SysDateTime.now()));
        }
*/
        // apply( new DeliveredSubscriptionCountAddedToPriceBucketEvent(productId,
        //                                                        priceBucketId,
        //                                                        priceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(),
        //                                                        priceBucket.getTaggedPriceVersion().getMrp(),
        //                                                        priceBucket.getFixedOfferedPriceOrPercentDiscountPerUnit(),
        //                                                        this.getProductPricingCategory(),
        //                                                        deliveredSubscriptionCount,
        //                                                        totalDeliveredSubscriptionCount) );

        LocalDateTime fromDate = priceBucket.getFromDate();
        LocalDateTime toDate = priceBucket.getToDate();
        double fixedExpensePerUnit = this.findLatestFixedExpensePerUnitInDateRange(fromDate, toDate);
        double variableExpensePerUnit = this.findLatestVariableExpensePerUnitInDateRange(fromDate, toDate);
        priceBucket.calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(productId, this.getProductPricingCategory(), fixedExpensePerUnit, variableExpensePerUnit, deliveredSubscriptionCount, dispatchDate);
    }

    public Set<PriceTaggedWithProduct> updateBreakEvenPriceUponChangeOfPriceOrExpenses(OperatingExpenseService operatingExpenseService) {
        List<CostHeader> costHeaders = new ArrayList<>(3);
        //PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
        // we will need to calculate revised breakeven price for active tagged price versions as differnt tagged price versions are embedded in different price buckets
        Set<PriceTaggedWithProduct> taggedPriceVersions = this.getTaggedPriceVersions();
        for (PriceTaggedWithProduct taggedPriceVersion : taggedPriceVersions) {
            CostHeader purchasePriceHeader = new CostHeader(CostHeaderType.PURCHASE_PRICE_PER_UNIT, "purchase price per unit", taggedPriceVersion.getPurchasePricePerUnit(), CostHeaderApplicability.ABSOLUTE);
            costHeaders.add(purchasePriceHeader);

            FixedExpensePerProduct latestFixedOperatingExpensePerUnit = getLatestFixedExpenseVersion();
            CostHeader fixedExpenseHeader;
            if (latestFixedOperatingExpensePerUnit == null) {
                fixedExpenseHeader = new CostHeader(CostHeaderType.FIXED_EXPENSE_PER_UNIT,
                        "fixed expense per unit",
                        operatingExpenseService.fetchDefaultPercentFixedExpensePerUnitPrice() * taggedPriceVersion.getMRP(), CostHeaderApplicability.ABSOLUTE);
            } else {
                fixedExpenseHeader = new CostHeader(CostHeaderType.FIXED_EXPENSE_PER_UNIT, "fixed expense per unit", latestFixedOperatingExpensePerUnit.getFixedOperatingExpPerUnit(), CostHeaderApplicability.ABSOLUTE);
            }
            costHeaders.add(fixedExpenseHeader);
            VariableExpensePerProduct latestVariableExpensePerProduct = getLatestVariableExpenseVersion();
            CostHeader variableExpenseHeader;
            if (latestVariableExpensePerProduct == null) {
                variableExpenseHeader = new CostHeader(CostHeaderType.VARIABLE_EXPENSE_PER_UNIT,
                        "variable expense per unit",
                        operatingExpenseService.fetchDefaultPercentVariableExpensePerUnitPrice() * taggedPriceVersion.getMRP(), CostHeaderApplicability.ABSOLUTE);
            } else {
                variableExpenseHeader = new CostHeader(CostHeaderType.VARIABLE_EXPENSE_PER_UNIT, "variable expense per unit", latestVariableExpensePerProduct.getVariableOperatingExpPerUnit(), CostHeaderApplicability.ABSOLUTE);
            }
            costHeaders.add(variableExpenseHeader);

            BreakEvenPriceCalculator breakEvenPriceCalculator = new BreakEvenPriceCalculator();
            final double breakEvenPriceForATaggedPriceVersion = breakEvenPriceCalculator.calculateBreakEvenPrice(costHeaders);
            taggedPriceVersion.setBreakEvenPrice(breakEvenPriceForATaggedPriceVersion);
            //latestTaggedPriceVersion.setBreakEvenPrice(breakEvenrPrice);
        }
        return taggedPriceVersions;
    }

    public void registerOpeningPrice(String productId, double openingPriceOrPercent, LocalDateTime fromDate) {
        PriceTaggedWithProduct latestTaggedPriceVersion = this.getLatestTaggedPriceVersion();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MMddyyyyHHmmsss");
        String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);

        //PriceBucket newPriceBucket = createNewPriceBucket(productId, latestTaggedPriceVersion, openingPriceOrPercent, EntityStatus.ACTIVE, currentDate,priceBucketFactory);

        //apply(new OpeningPriceOrPercentRegisteredEvent(productId, newPriceBucket));
        apply(new OpeningPriceOrPercentRegisteredEvent(
                productId,
                priceBucketId,
                this.productPricingCategory,
                latestTaggedPriceVersion,
                0L,
                0L,
                0L,
                fromDate,
                new LocalDateTime(9999, 12, 31, 0, 0, 0),
                EntityStatus.ACTIVE,
                openingPriceOrPercent
        ));
    }

    //should fire productsubscription updated event OR shuld fire two events add/deduct
    public void updateProductSubscription(UpdateProductSubscriptionCommand command) {
        final Map<String, Integer> priceBucketWiseSubscriptionCount = command.getPriceBucketWiseSubscriptionCount();
        priceBucketWiseSubscriptionCount.keySet().stream().forEach(priceBucketId -> {
            final PriceBucket activePriceBucket = this.findActivePriceBucketByPriceBucketId(priceBucketId);
            final int subscriptionCount = priceBucketWiseSubscriptionCount.get(priceBucketId);
            if (subscriptionCount > 0) {
                activePriceBucket.addSubscriptionToPriceBucket(subscriptionCount, command.getSubscriptionChangedDate());
            } else {
                activePriceBucket.deductSubscriptionFromPriceBucket(Math.abs(subscriptionCount),command.getSubscriptionChangedDate());
            }
        });
    }


    //for receipt from main application thru integration
    public void receiveProductStatus(ReceiveProductStatusCommand command, OperatingExpenseService operatingExpenseService) {
        String productId = command.getProductId();
        //PriceBucket latestPriceBucket = this.findLatestActivePriceBucket();
        PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
        if (latestTaggedPriceVersion.getPurchasePricePerUnit() != command.getCurrentPurchasePrice()) {
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            final String taggedPriceVersionId = productId + command.getCurrentPriceDate().toString(format);
            PriceTaggedWithProduct newTaggedPrice = new PriceTaggedWithProduct(taggedPriceVersionId, command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentPriceDate());
            //NO need to updated breakeven price here as the same is getting done in updateBreakEvenPriceUponChangeOfPriceOrExpenses
            //newTaggedPrice.setBreakEvenPrice(updateBreakEvenPriceUponChangeOfPriceOrExpenses(operatingExpenseService));
            ///latestTaggedPriceVersion.setTaggedEndDate(SysDateTime.now());
            //this.addNewTaggedPriceVersion(newtaggedPrice);
            apply(new ProductStatusUpdatedEvent(command.getProductId(), newTaggedPrice, command.getCurrentStockInUnits()));
        }
        //this.setCurrentStockInUnits(event.getCurrentStockInUnits());


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
        //Though price bucket is updated with end date its status will still remain active until any active subscriptions are associated with it.
    }

    @EventSourcingHandler
    public void on(VariableExpenseChangedEvent event) {
        variableExpenseVersions.add(event.getVariableExpensePerProduct());
/*
        PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
        latestTaggedPriceVersion.setBreakEvenPrice(event.getRevisedBreakEvenPrice());
*/
        Set<PriceTaggedWithProduct> taggedPriceVersionsWithRevisedBEPrice = event.getUpdatedTaggedPriceVersions();
        for (PriceTaggedWithProduct taggedPriceVersionWithRevisedBEPrice : taggedPriceVersionsWithRevisedBEPrice) {
            if (taggedPriceVersions.contains(taggedPriceVersionWithRevisedBEPrice)) {
                PriceTaggedWithProduct matchedTaggedPriceVersion = taggedPriceVersions.stream().filter(taggedPriceVersionWithRevisedBEPrice::equals).findAny().get();
                matchedTaggedPriceVersion.setBreakEvenPrice(taggedPriceVersionWithRevisedBEPrice.getBreakEvenPrice());
            }
            //Need to update delivered subscriptions against tagged prices too
        }
    }


    @EventSourcingHandler
    public void on(FixedExpenseChangedEvent event) {
        //get latest deliveryExpense
        fixedExpenseVersions.add(event.getFixedExpensePerProduct());
/*
        PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
        latestTaggedPriceVersion.setBreakEvenPrice(event.   getRevisedBreakEvenPrice());
*/

        Set<PriceTaggedWithProduct> taggedPriceVersionsWithRevisedBEPrice = event.getUpdatedTaggedPriceVersions();
        for (PriceTaggedWithProduct taggedPriceVersionWithRevisedBEPrice : taggedPriceVersionsWithRevisedBEPrice) {
            if (taggedPriceVersions.contains(taggedPriceVersionWithRevisedBEPrice)) {
                PriceTaggedWithProduct matchedTaggedPriceVersion = taggedPriceVersions.stream().filter(taggedPriceVersionWithRevisedBEPrice::equals).findAny().get();
                matchedTaggedPriceVersion.setBreakEvenPrice(taggedPriceVersionWithRevisedBEPrice.getBreakEvenPrice());
            }
            //Need to update delivered subscriptions against tagged prices too
        }

    }


    @EventSourcingHandler
    public void on(PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent event) {
        PriceBucket activePriceBucket = this.findActivePriceBucketByPriceBucketId(event.getPriceBucketId());
        activePriceBucket.setExpectedProfit(event.getProfitAmountPerPriceBucket());
    }

    @EventSourcingHandler
    public void on(PriceBucketExpiredEvent event) {
        PriceBucket activePriceBucket = this.findActivePriceBucketByPriceBucketId(event.getPriceBucketId());
        activePriceBucket.expirePriceBucket();
        this.removeExpiredPriceBucket(activePriceBucket);
    }

    private void removeExpiredPriceBucket(PriceBucket activePriceBucket) {
        this.activePriceBuckets.remove(activePriceBucket.getFromDate());
    }

    //Only for actuals
    //Product status should be received from main application.
    //when the purchase price is changed --it should create new price bucket,by stalling all existing price buckets
    @EventSourcingHandler
    public void on(ProductStatusUpdatedEvent event) {
        PriceTaggedWithProduct latestTaggedPriceVersion = this.getLatestTaggedPriceVersion();
        latestTaggedPriceVersion.setTaggedEndDate(SysDate.now());
        this.addNewTaggedPriceVersion(event.getNewTaggedPrice());
        this.setCurrentStockInUnits(event.getCurrentStockInUnits());
    }

    @EventSourcingHandler
    public void on(OpeningPriceOrPercentRegisteredEvent event) {
        //PriceBucket newPriceBucket = new PriceBucket(event.getProductId(), event.getPriceBucketId(), event.getProductPricingCategory(),event.getTaggedPriceVersion(),event.getOfferedPriceOrPercentDiscountPerUnit(),event.getEntityStatus(),event.getFromDate());
        PriceBucket newPriceBucket = PriceBucketFactory.createPriceBucket(event.getProductId(), event.getPriceBucketId(), event.getProductPricingCategory(), event.getTaggedPriceVersion(), event.getOfferedPriceOrPercentDiscountPerUnit(), event.getEntityStatus(), event.getFromDate());
        this.addNewPriceBucket(newPriceBucket.getFromDate(), newPriceBucket);
        //this.productActivationStatusList.add(ProductStatus.PRODUCT_PRICE_ASSIGNED);
    }


    public void calculateExpectedRevenueAndProfit(String productId) {
        Map<LocalDateTime, PriceBucket> datewiseActivePriceBucketsMap = this.getActivePriceBuckets();
        Collection<PriceBucket> activePriceBuckets = datewiseActivePriceBucketsMap.values();
        double revisedExpectedPurchaseCost = 0;
        double revisedExpectedRevenue = 0;
        double revisedExpectedProfit = 0;
        for (PriceBucket activePriceBucket : activePriceBuckets) {
            revisedExpectedPurchaseCost += activePriceBucket.getExpectedPurchaseCostOfDeliveredUnits();
            revisedExpectedRevenue += activePriceBucket.getExpectedRevenue();
            revisedExpectedProfit += activePriceBucket.getExpectedProfit();
        }
        apply(new ExpectedProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent(productId, (revisedExpectedPurchaseCost - this.expectedPurchaseCost), (revisedExpectedRevenue - this.expectedRevenue), (revisedExpectedProfit - this.expectedProfit)));
    }

    @EventSourcingHandler
    public void on(ExpectedProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent event ){
        this.expectedPurchaseCost +=event.getPurchaseCostContribution();
        this.expectedRevenue +=event.getRevenueContribution();
        this.expectedProfit +=event.getProfitContribution();
    }
    public void calculateRegisteredRevenueAndProfit(String productId) {
        Map<LocalDateTime, PriceBucket> datewiseActivePriceBucketsMap = this.getActivePriceBuckets();
        Collection<PriceBucket> activePriceBuckets = datewiseActivePriceBucketsMap.values();
        double revisedRegisteredPurchaseCost = 0;
        double revisedRegisteredRevenue = 0;
        double revisedRegisteredProfit = 0;
        for (PriceBucket activePriceBucket : activePriceBuckets) {
            revisedRegisteredPurchaseCost += activePriceBucket.getTotalRegisteredPurchaseCost();
            revisedRegisteredRevenue += activePriceBucket.getTotalRegisteredRevenue();
            revisedRegisteredProfit += activePriceBucket.getTotalRegisteredProfit();
        }
        apply(new ProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent(productId, (revisedRegisteredPurchaseCost - this.registeredPurchaseCost), (revisedRegisteredRevenue - this.registeredRevenue), (revisedRegisteredProfit - this.registeredProfit)));
    }

    @EventSourcingHandler
    public void on(ProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent event) {

        this.registeredPurchaseCost += event.getPurchaseCostContribution();
        this.registeredRevenue += event.getRevenueContribution();
        this.registeredProfit += event.getProfitContribution();
    }

    //When the new actual offer price is recommended
    //Expire current price bucket and register a new price bucket
    @EventSourcingHandler
    public void on(OfferedPriceChangedEvent event) {
        PriceBucket newPriceBucket = PriceBucketFactory.createPriceBucket(event.getProductId(), event.getPriceBucketId(), event.getProductPricingCategory(), event.getTaggedPriceVersion(), event.getOfferedPriceOrPercentDiscountPerUnit(), event.getEntityStatus(), event.getFromDate());
        this.addNewPriceBucket(event.getFromDate(), newPriceBucket);
        if (event.getPricingOptions() != PricingOptions.ACCEPT_AUTOMATED_PRICE_GENERATION) {
            this.removeRecommendedPriceBucket(newPriceBucket);
        }

    }

    @EventSourcingHandler
    public void on(OfferedPriceRecommendedEvent event) {
        //this.productDemandTrend = event.getProductDemandTrend();
        PriceBucket newPriceBucket = PriceBucketFactory.createPriceBucket(event.getProductId(), event.getPriceBucketId(), event.getProductPricingCategory(), event.getTaggedPriceVersion(), event.getOfferedPriceOrPercentDiscountPerUnit(), event.getEntityStatus(), event.getFromDate());
        this.addNewPriceRecommendation(event.getFromDate(), newPriceBucket);
    }

    public void calculateTotalActiveSubscriptions() {
        Map<LocalDateTime, PriceBucket> datewiseActivePriceBucketsMap = this.getActivePriceBuckets();
        Collection<PriceBucket> activePriceBuckets = datewiseActivePriceBucketsMap.values();
        long activeSubscriptions = 0;
        for (PriceBucket activePriceBucket : activePriceBuckets) {
            activeSubscriptions += activePriceBucket.getNumberOfExistingSubscriptions();
        }
    }


    public void calculateTotalDeliveredSubscriptions() {
        Map<LocalDateTime, PriceBucket> datewiseActivePriceBucketsMap = this.getActivePriceBuckets();
        Collection<PriceBucket> activePriceBuckets = datewiseActivePriceBucketsMap.values();
        long deliveredSubscriptions = 0;
        for (PriceBucket activePriceBucket : activePriceBuckets) {
            deliveredSubscriptions += activePriceBucket.getTotalDeliveredSubscriptions();
        }
    }

    public void calculateTotalChurnedSubscriptions() {
        Map<LocalDateTime, PriceBucket> datewiseActivePriceBucketsMap = this.getActivePriceBuckets();
        Collection<PriceBucket> activePriceBuckets = datewiseActivePriceBucketsMap.values();
        long churnedSubscriptions = 0;
        for (PriceBucket activePriceBucket : activePriceBuckets) {
            churnedSubscriptions += activePriceBucket.getNumberOfChurnedSubscriptions();
        }
    }

    public void addDeliveredSubscriptionCountToRespectivePriceBucket(String productId, String priceBucketId, LocalDate dispatchDate) {
        PriceBucket priceBucket = this.findActivePriceBucketByPriceBucketId(priceBucketId);
        priceBucket.addDeliveredSubscriptionsAssociatedWithAPriceBucket(productId, 1);
    }
}
