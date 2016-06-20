package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.product.command.ReceiveProductStatusCommand;
import com.affaince.subscription.product.command.SetProductConfigurationCommand;
import com.affaince.subscription.product.command.UpdateProductStatusCommand;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

public class Product extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private long netQuantity;
    private QuantityUnit quantityUnit;
    private List<String> substitutes;
    private List<String> complements;

    @EventSourcedMember
    private ProductConfiguration productConfiguration;
    @EventSourcedMember
    private ProductAccount productAccount ;
    private Map<SensitivityCharacteristic, Double> sensitiveTo;

    public Product() {

    }

    public Product(String productId, String productName, String categoryId, String subCategoryId, long netQuantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements, Map<SensitivityCharacteristic, Double> sensitiveTo) {
        apply(new ProductRegisteredEvent(productId, productName, categoryId, subCategoryId, netQuantity, quantityUnit, substitutes, complements, sensitiveTo));
    }

    public String getProductId() {
        return this.productId;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getSubCategoryId() {
        return this.subCategoryId;
    }

    public List<String> getSubstitutes() {
        return this.substitutes;
    }

    public List<String> getComplements() {
        return this.complements;
    }

    public ProductAccount getProductAccount() {
        return this.productAccount;
    }
    public PriceBucket getLatestPriceBucket() {
        return getProductAccount().getLatestPriceBucket();
    }

    public Map<SensitivityCharacteristic, Double> getSensitiveTo() {
        return this.sensitiveTo;
    }

    public String getProductName() {
        return this.productName;
    }

    public long getNetQuantity() {
        return this.netQuantity;
    }

    public QuantityUnit getQuantityUnit() {
        return this.quantityUnit;
    }

    public double getLatestPurchasePrice() {
        return getProductAccount().getLatestTaggedPriceVersion().getPurchasePricePerUnit();
    }

    public double getLatestMerchantProfit() {
        return getProductAccount().getLatestPerformanceTracker().getExpectedMerchantProfitPercentage();
    }

    public ProductConfiguration getProductConfiguration() {
        return productConfiguration;
    }

    public void setProductConfiguration(SetProductConfigurationCommand command) {
        apply(new ProductConfigurationSetEvent(this.productId, command.getDemandCurvePeriod(),
                command.getRevenueChangeThresholdForPriceChange(),
                command.isCrossPriceElasticityConsidered(),
                command.isAdvertisingExpensesConsidered(),
                command.getDemandWiseProfitSharingRules()));
    }

/*
    public DemandWiseProfitSharingRule findProfitSharingRuleByDemandDensity(double demandDensityPercentage) {
        return getProductConfiguration().findDemandWiseProfitSharingRuleByDemandDensity(demandDensityPercentage);
    }
*/

    public double getLatestDemandDensity() {
        return getProductAccount().getLatestPerformanceTracker().getDemandDensity();
    }



    @EventSourcingHandler
    public void on(ProductRegisteredEvent event) {
        this.productId = event.getProductId();
        this.categoryId = event.getCategoryId();
        this.subCategoryId = event.getSubCategoryId();
        this.netQuantity = event.getQuantity();
        this.quantityUnit = event.getQuantityUnit();
        this.substitutes = event.getSubstitutes();
        this.complements = event.getComplements();
        this.sensitiveTo = event.getSensitiveTo();
        this.productAccount = new ProductAccount();
    }

    //Currently assuming it to be for actual price
    @EventSourcingHandler
    public void on(OfferedPriceUpdatedEvent event) {
        this.productId = event.getProductId();
        PriceBucket lastPriceBucket = this.getLatestPriceBucket();
        PriceBucket newPriceBucket = createPriceBucketForPriceCategory();
        newPriceBucket.setFromDate(event.getCurrentPriceDate());
        newPriceBucket.setOfferedPricePerUnit(event.getOfferedPrice());
        lastPriceBucket.setToDate(LocalDate.now());
        this.getProductAccount().addNewPriceBucket(event.getCurrentPriceDate(), newPriceBucket);
    }

    //Currently assuming it to be for forecast
/*
    @EventSourcingHandler
    public void on(ForecastParametersAddedEvent event) {
        this.productId = event.getProductId();
        this.calculationBasis = CalculationBasis.FORECAST;
        final ForecastedPriceParameter priceParameter = event.getForecastedPriceParameter();
        final LocalDate fromDate = new LocalDate(priceParameter.getMonthOfYear().get(DateTimeFieldType.year()), priceParameter.getMonthOfYear().get(DateTimeFieldType.monthOfYear()), 1);
        final LocalDate toDate = new LocalDate(fromDate.getYear(), fromDate.getMonthOfYear(), fromDate.dayOfMonth().getMaximumValue());

        PriceBucket priceBucket = new PriceBucket(
                priceParameter.getPurchasePricePerUnit(),
                priceParameter.getMRP(),
                fromDate,
                toDate,
                priceParameter.getNumberofNewSubscriptions(),
                priceParameter.getNumberOfChurnedSubscriptions()
        );
        getProductAccount().addNewPriceBucket(new LocalDate(priceParameter.getMonthOfYear().get(DateTimeFieldType.year()), priceParameter.getMonthOfYear().get(DateTimeFieldType.monthOfYear()), 1), priceBucket);

        ProductPerformanceTracker productPerformanceTracker = new ProductPerformanceTracker();
        productPerformanceTracker.setFromDate(fromDate);
        productPerformanceTracker.setDemandDensity(priceParameter.getDemandDensity());
        getProductAccount().addPerformanceTracker(fromDate, productPerformanceTracker);
    }
*/

    //Only for actuals
    @EventSourcingHandler
    public void on(ProductStatusReceivedEvent event) {
        this.productId = event.getProductId();

        if (this.getProductAccount().getLatestTaggedPriceVersion().getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            PriceTaggedWithProduct newtaggedPrice= new PriceTaggedWithProduct(event.getCurrentPurchasePrice(),event.getCurrentMRP(),event.getCurrentPriceDate());
            this.getProductAccount().addNewTaggedPriceVersion(newtaggedPrice);
        }
        this.getProductAccount().setCurrentStockInUnits(event.getCurrentStockInUnits());
    }

    @EventHandler
    public void on(ProductStatusUpdatedEvent event) {
        this.productId = event.getProductId();
        PriceBucket latestPriceBucket = getProductAccount().getLatestPriceBucket();
        if (this.getProductAccount().getLatestTaggedPriceVersion().getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            PriceTaggedWithProduct newtaggedPrice= new PriceTaggedWithProduct(event.getCurrentPurchasePrice(),event.getCurrentMRP(),event.getCurrentPriceDate());
            this.getProductAccount().addNewTaggedPriceVersion(newtaggedPrice);
        }
        this.getProductAccount().setCurrentStockInUnits(event.getCurrentStockInUnits());

    }

    @EventSourcingHandler
    public void on(ProductConfigurationSetEvent event) {
        this.productId = event.getProductId();
        final ProductConfiguration productConfiguration = new ProductConfiguration();
        productConfiguration.setDemandCurvePeriod(event.getDemandCurvePeriod());
        productConfiguration.setRevenueChangeThresholdForPriceChange(event.getRevenueChangeThresholdForPriceChange());
        productConfiguration.setCrossPriceElasticityConsidered(event.isCrossPriceElasticityConsidered());
        productConfiguration.setAdvertisingExpensesConsidered(event.isAdvertisingExpensesConsidered());
       // productConfiguration.setDemandWiseProfitSharingRules(event.getDemandWiseProfitSharingRules());
        this.productConfiguration = productConfiguration;
    }

    public void updateProductStatus(UpdateProductStatusCommand command) {
        apply(new ProductStatusUpdatedEvent(this.productId, command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    //for receipt from main application thru integration
    public void receiveProductStatus(ReceiveProductStatusCommand command) {
        apply(new ProductStatusUpdatedEvent(this.productId, command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    public void addCurrentOfferedPrice(double currentOfferedPrice) {
        apply(new CurrentOfferedPriceAddedEvent(this.productId, currentOfferedPrice));
    }

    public double getLatestMRP() {
        return getProductAccount().getLatestTaggedPriceVersion().getMRP();
    }

    public void setLatestOfferedPrice(double offeredPrice) {
        getProductAccount().getLatestPriceBucket().setOfferedPricePerUnit(offeredPrice);
    }

    PriceBucket createPriceBucketForPriceCategory(){
        if(this.getProductAccount().getProductPricingCategory()==ProductPricingCategory.DISCOUNT_COMMITMENT){
            return new PriceBucketForPercentDiscountCommitment();
        }else if(this.getProductAccount().getProductPricingCategory()==ProductPricingCategory.PRICE_COMMITMENT){
            return new PriceBucketForPriceCommitment();
        }else{
            return new PriceBucketForNoneCommitment();
        }
    }
}
