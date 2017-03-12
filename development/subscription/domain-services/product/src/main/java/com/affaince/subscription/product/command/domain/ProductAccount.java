package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.*;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.services.operatingexpense.OperatingExpenseService;
import com.affaince.subscription.product.services.pricing.calculator.breakevenprice.BreakEvenPriceCalculator;
import com.affaince.subscription.product.vo.*;
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
    private SortedSet<PriceTaggedWithProduct> taggedPriceVersions;// a version should go away when all pricebuckets associated with it are expired.??
    private SortedSet<FixedExpensePerProduct> fixedExpenseVersions;//should be reset annually. All but the latest one should vanish
    private SortedSet<VariableExpensePerProduct> variableExpenseVersions;//should be reset annually. All but the latest one should vanish
    private Map<LocalDateTime, PriceBucket> recommendedPriceBuckets;// taken care of creation and expiration automatically
    private Map<LocalDateTime, PriceBucket> activePriceBuckets;// taken care of creation and expiration automatically
    private long currentStockInUnits;// should be adjusted on each delivery made
    private ProductPricingCategory productPricingCategory;
    private double creditPoints;
    private double variableExpenseSlope;
    private double registeredPurchaseCost; //should be reset annually=pc of remaining stock + new purchase cost
    private double registeredRevenue;// should be reset annually =0;
    private double registeredProfit; // should be reset annually =0;


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
            PriceBucket nonCommittedPriceBucket = new PriceBucket(productId, priceBucketId,ProductPricingCategory.NO_COMMITMENT);
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
            PriceBucket newPriceBucket = new PriceBucket(productId, priceBucketId, ProductPricingCategory.DISCOUNT_COMMITMENT,taggedPriceVersion, offeredPriceOrPercent, entityStatus, fromDate);
            //activePriceBuckets.put(LocalDate.now(),newPriceBucket);
            return newPriceBucket;

        } else if (this.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT && getLatestActivePriceBucket().getOfferedPriceOrPercentDiscountPerUnit() != offeredPriceOrPercent) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MMddyyyyHHmmsss");
            String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);
            PriceBucket newPriceBucket = new PriceBucket(productId, priceBucketId, ProductPricingCategory.DISCOUNT_COMMITMENT,taggedPriceVersion, offeredPriceOrPercent, entityStatus, fromDate);
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
        if (null != latestVariableExpense && latestVariableExpense.getVariableOperatingExpPerUnit() != command.getOperationExpense()) {
            VariableExpensePerProduct newVariableExpenseVersion = new VariableExpensePerProduct(command.getOperationExpense(), SysDate.now());
            //variableExpenseVersions.add(newVariableExpenseVersion);
            double revisedBreakEvenPrice = calculateBreakEvenPriceUponChangeOfPriceOrExpenses(operatingExpenseService);
/*
            PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
            latestTaggedPriceVersion.setBreakEvenPrice();
*/
            apply(new VariableExpenseChangedEvent(command.getProductId(), SysDate.now(), newVariableExpenseVersion, revisedBreakEvenPrice));
        }
    }

    public void updateFixedExpenses(UpdateFixedExpenseToProductCommand command, OperatingExpenseService operatingExpenseService) {
        //get latest deliveryExpense
        FixedExpensePerProduct latestFixedExpense = fixedExpenseVersions.first();
        if (latestFixedExpense.getFixedOperatingExpPerUnit() != command.getOperationExpense()) {
            FixedExpensePerProduct newFixedExpenseVersion = new FixedExpensePerProduct(command.getOperationExpense(), SysDate.now());
            double revisedBreakEvenPrice = calculateBreakEvenPriceUponChangeOfPriceOrExpenses(operatingExpenseService);
            //fixedExpenseVersions.add(newFixedExpenseVersion);
/*
            PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
            latestTaggedPriceVersion.setBreakEvenPrice(calculateBreakEvenPriceUponChangeOfPriceOrExpenses());
*/
            apply(new FixedExpenseChangedEvent(command.getProductId(), SysDate.now(), newFixedExpenseVersion, revisedBreakEvenPrice));
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

    public void calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(String productId, String priceBucketId) {
        final PriceBucket priceBucket=this.findActivePriceBucketByPriceBucketId(priceBucketId);
        LocalDateTime fromDate = priceBucket.getFromDate();
        LocalDateTime toDate = priceBucket.getToDate();
        double fixedExpensePerUnit = this.findLatestFixedExpensePerUnitInDateRange(fromDate, toDate);
        double variableExpensePerUnit = this.findLatestVariableExpensePerUnitInDateRange(fromDate, toDate);
        priceBucket.calculateRegisteredPurchaseExpenseRevenueAndProfitForPriceBucket(productId,fixedExpensePerUnit,variableExpensePerUnit);
    }

    public double calculateBreakEvenPriceUponChangeOfPriceOrExpenses(OperatingExpenseService operatingExpenseService) {
        List<CostHeader> costHeaders = new ArrayList<>(3);
        PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
        CostHeader purchasePriceHeader = new CostHeader(CostHeaderType.PURCHASE_PRICE_PER_UNIT, "purchase price per unit", latestTaggedPriceVersion.getPurchasePricePerUnit(), CostHeaderApplicability.ABSOLUTE);
        costHeaders.add(purchasePriceHeader);

        FixedExpensePerProduct latestFixedOperatingExpensePerUnit = getLatestFixedExpenseVersion();
        CostHeader fixedExpenseHeader;
        if (latestFixedOperatingExpensePerUnit == null) {
            fixedExpenseHeader = new CostHeader(CostHeaderType.FIXED_EXPENSE_PER_UNIT,
                    "fixed expense per unit",
                    operatingExpenseService.fetchDefaultPercentFixedExpensePerUnitPrice()*latestTaggedPriceVersion.getMRP(), CostHeaderApplicability.ABSOLUTE);
        } else {
            fixedExpenseHeader = new CostHeader(CostHeaderType.FIXED_EXPENSE_PER_UNIT, "fixed expense per unit", latestFixedOperatingExpensePerUnit.getFixedOperatingExpPerUnit(), CostHeaderApplicability.ABSOLUTE);
        }
        costHeaders.add(fixedExpenseHeader);
        VariableExpensePerProduct latestVariableExpensePerProduct = getLatestVariableExpenseVersion();
        CostHeader variableExpenseHeader;
        if (latestVariableExpensePerProduct == null) {
            variableExpenseHeader = new CostHeader(CostHeaderType.VARIABLE_EXPENSE_PER_UNIT,
                    "variable expense per unit",
                    operatingExpenseService.fetchDefaultPercentVariableExpensePerUnitPrice()*latestTaggedPriceVersion.getMRP(), CostHeaderApplicability.ABSOLUTE);
        } else {
            variableExpenseHeader = new CostHeader(CostHeaderType.VARIABLE_EXPENSE_PER_UNIT, "variable expense per unit", latestVariableExpensePerProduct.getVariableOperatingExpPerUnit(), CostHeaderApplicability.ABSOLUTE);
        }
        costHeaders.add(variableExpenseHeader);

        BreakEvenPriceCalculator breakEvenPriceCalculator = new BreakEvenPriceCalculator();
        return breakEvenPriceCalculator.calculateBreakEvenPrice(costHeaders);
        //latestTaggedPriceVersion.setBreakEvenPrice(breakEvenrPrice);

    }

    public void registerOpeningPrice(String productId, double openingPriceOrPercent) {
        PriceTaggedWithProduct latestTaggedPriceVersion = this.getLatestTaggedPriceVersion();
        final LocalDateTime currentDate = SysDateTime.now();
        PriceBucket newPriceBucket = createNewPriceBucket(productId, latestTaggedPriceVersion, openingPriceOrPercent, EntityStatus.CREATED, currentDate);

        //apply(new OpeningPriceOrPercentRegisteredEvent(productId, newPriceBucket));
        apply(new OpeningPriceOrPercentRegisteredEvent(
                newPriceBucket.getProductId(),
                newPriceBucket.getPriceBucketId(),
                newPriceBucket.getProductPricingCategory(),
                newPriceBucket.getTaggedPriceVersion(),
                newPriceBucket.getNumberOfNewSubscriptions(),
                newPriceBucket.getNumberOfChurnedSubscriptions(),
                newPriceBucket.getNumberOfExistingSubscriptions(),
                newPriceBucket.getFromDate(),
                newPriceBucket.getToDate(),
                newPriceBucket.getEntityStatus(),
                newPriceBucket.getOfferedPriceOrPercentDiscountPerUnit()
                ));
    }

//should fire productsubscription updated event OR shuld fire two events add/deduct
    public void updateProductSubscription(UpdateProductSubscriptionCommand command) {
        final Map<String, Integer> priceBucketWiseSubscriptionCount = command.getPriceBucketWiseSubscriptionCount();
        priceBucketWiseSubscriptionCount.keySet().stream().forEach(priceBucketId -> {
            final PriceBucket activePriceBucket = this.findActivePriceBucketByPriceBucketId(priceBucketId);
            final int subscriptionCount = priceBucketWiseSubscriptionCount.get(priceBucketId);
            if (subscriptionCount > 0) {
                activePriceBucket.addSubscriptionToPriceBucket(subscriptionCount);
            } else {
                activePriceBucket.deductSubscriptionFromPriceBucket(Math.abs(subscriptionCount));
            }
        });
    }


    //for receipt from main application thru integration
    public void receiveProductStatus(ReceiveProductStatusCommand command, OperatingExpenseService operatingExpenseService) {
        String productId = command.getProductId();
        //PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
        if (latestTaggedPriceVersion.getPurchasePricePerUnit() != command.getCurrentPurchasePrice()) {
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            final String taggedPriceVersionId = productId + command.getCurrentPriceDate().toString(format);
            PriceTaggedWithProduct newTaggedPrice = new PriceTaggedWithProduct(taggedPriceVersionId, command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentPriceDate());
            newTaggedPrice.setBreakEvenPrice(calculateBreakEvenPriceUponChangeOfPriceOrExpenses(operatingExpenseService));
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
    }

    @EventSourcingHandler
    public void on(VariableExpenseChangedEvent event) {
        variableExpenseVersions.add(event.getVariableExpensePerProduct());
        PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
        latestTaggedPriceVersion.setBreakEvenPrice(event.getRevisedBreakEvenPrice());
    }


    @EventSourcingHandler
    public void on(FixedExpenseChangedEvent event) {
        //get latest deliveryExpense
        fixedExpenseVersions.add(event.getFixedExpensePerProduct());
        PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
        latestTaggedPriceVersion.setBreakEvenPrice(event.getRevisedBreakEvenPrice());
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
        String productId = event.getProductId();
        PriceTaggedWithProduct latestTaggedPriceVersion = this.getLatestTaggedPriceVersion();
        latestTaggedPriceVersion.setTaggedEndDate(SysDate.now());
        this.addNewTaggedPriceVersion(event.getNewTaggedPrice());
        this.setCurrentStockInUnits(event.getCurrentStockInUnits());
    }

    @EventSourcingHandler
    public void on(OpeningPriceOrPercentRegisteredEvent event) {
        PriceBucket newPriceBucket = new PriceBucket(event.getProductId(), event.getPriceBucketId(), event.getProductPricingCategory(),event.getTaggedPriceVersion(),event.getOfferedPriceOrPercentDiscountPerUnit(),event.getEntityStatus(),event.getFromDate());
        this.addNewPriceBucket(newPriceBucket.getFromDate(), newPriceBucket);
        //this.productActivationStatusList.add(ProductStatus.PRODUCT_PRICE_ASSIGNED);
    }

    public void calculateRegisteredRevenueAndProfit(String productId) {
        Map<LocalDateTime,PriceBucket> datewiseActivePriceBucketsMap= this.getActivePriceBuckets();
        Collection<PriceBucket> activePriceBuckets = datewiseActivePriceBucketsMap.values();
        double revisedRegisteredPurchaseCost=0;
        double revisedRegisteredRevenue=0;
        double revisedRegisteredProfit=0;
        for(PriceBucket activePriceBucket: activePriceBuckets){
            revisedRegisteredPurchaseCost+= activePriceBucket.getRegisteredPurchaseCostOfDeliveredUnits();
            revisedRegisteredRevenue +=activePriceBucket.getRegisteredRevenue();
            revisedRegisteredProfit +=activePriceBucket.getRegisteredProfit();
        }
        apply(new ProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent(productId,(revisedRegisteredPurchaseCost-this.registeredPurchaseCost),(revisedRegisteredRevenue-this.registeredRevenue),(revisedRegisteredProfit-this.registeredProfit)));
    }

    @EventSourcingHandler
    public void on(ProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent event){

        this.registeredPurchaseCost+=event.getPurchaseCostContribution();
        this.registeredRevenue +=event.getRevenueContribution();
        this.registeredProfit+=event.getProfitContribution();
    }

    public void calculateTotalActiveSubscriptions(){
        Map<LocalDateTime,PriceBucket> datewiseActivePriceBucketsMap= this.getActivePriceBuckets();
        Collection<PriceBucket> activePriceBuckets = datewiseActivePriceBucketsMap.values();
        long activeSubscriptions=0;
        for(PriceBucket activePriceBucket: activePriceBuckets) {
            activeSubscriptions +=activePriceBucket.getNumberOfExistingSubscriptions();
        }
    }


    public void calculateTotalDeliveredSubscriptions(){
        Map<LocalDateTime,PriceBucket> datewiseActivePriceBucketsMap= this.getActivePriceBuckets();
        Collection<PriceBucket> activePriceBuckets = datewiseActivePriceBucketsMap.values();
        long deliveredSubscriptions=0;
        for(PriceBucket activePriceBucket: activePriceBuckets) {
            deliveredSubscriptions +=activePriceBucket.getNumberOfDeliveredSubscriptions();
        }
    }

    public void calculateTotalChurnedSubscriptions(){
        Map<LocalDateTime,PriceBucket> datewiseActivePriceBucketsMap= this.getActivePriceBuckets();
        Collection<PriceBucket> activePriceBuckets = datewiseActivePriceBucketsMap.values();
        long churnedSubscriptions=0;
        for(PriceBucket activePriceBucket: activePriceBuckets) {
            churnedSubscriptions +=activePriceBucket.getNumberOfChurnedSubscriptions();
        }
    }

}
