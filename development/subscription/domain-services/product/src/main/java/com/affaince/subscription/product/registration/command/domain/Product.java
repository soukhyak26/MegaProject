package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.product.registration.command.AddForecastParametersCommand;
import com.affaince.subscription.product.registration.command.SetProductConfigurationCommand;
import com.affaince.subscription.product.registration.command.UpdateProductStatusCommand;
import com.affaince.subscription.product.registration.command.event.*;
import com.affaince.subscription.product.registration.vo.DemandWiseProfitSharingRule;
import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class Product extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private long quantity;
    private QuantityUnit quantityUnit;
    private List<String> substitutes;
    private List<String> complements;
    private ProductConfiguration productConfiguration;
    private ProductAccount productAccount = new ProductAccount();
    private double offeredPrice;
    private double latestOfferedPriceActuals;

    public Product() {

    }

    public Product(String productId, String productName, String categoryId, String subCategoryId, long quantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements) {
        apply(new ProductRegisteredEvent(productId, productName, categoryId, subCategoryId, quantity, quantityUnit, substitutes, complements));
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

    public PriceBucket getLatestActualPriceBucket() {
        return productAccount.getLatestActualPriceBucket();
    }

    public String getProductName() {
        return this.productName;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public QuantityUnit getQuantityUnit() {
        return this.quantityUnit;
    }

    @EventSourcingHandler
    public void on(ProductRegisteredEvent event) {
        this.productId = event.getProductId();
        this.categoryId = event.getCategoryId();
        this.subCategoryId = event.getSubCategoryId();
        this.quantity = event.getQuantity();
        this.quantityUnit = event.getQuantityUnit();
        this.substitutes = event.getSubstitutes();
        this.complements = event.getComplements();
        productAccount = new ProductAccount();
    }

    @EventSourcingHandler
    public void on(OfferedPriceUpdatedEvent event) {
        this.productId = event.getProductId();
        PriceBucket lastPriceBucket = this.getLatestActualPriceBucket();
        PriceBucket newPriceBucket = new PriceBucket(lastPriceBucket);
        newPriceBucket.setFromDate(event.getCurrentPriceDate());
        newPriceBucket.setOfferedPricePerUnit(event.getOfferedPrice());
        lastPriceBucket.setToDate(LocalDate.now());
        this.getProductAccount().addNewActualPriceBucket(event.getCurrentPriceDate(), newPriceBucket);
    }

    @EventSourcingHandler
    public void on(ForecastParametersAddedEvent event) {
        this.productId = event.getProductId();
        final ForecastedPriceParameter priceParameter = event.getForecastedPriceParamter();
        PriceBucket priceBucket = new PriceBucket(
                priceParameter.getPurchasePricePerUnit(),
                priceParameter.getMRP(),
                priceParameter.getFromDate(),
                priceParameter.getToDate(),
                priceParameter.getNumberOfNewCustomersAssociatedWithAPrice(),
                priceParameter.getNumberOfChurnedCustomersAssociatedWithAPrice()
        );
        this.productAccount.addNewForecastedPriceBucket(priceParameter.getFromDate(), priceBucket);

        ProductPerformanceTracker productPerformanceTracker = new ProductPerformanceTracker();
        productPerformanceTracker.setFromDate(priceParameter.getFromDate());
        productPerformanceTracker.setToDate(priceParameter.getToDate());
        productPerformanceTracker.setDemandDensity(event.getDemandDensity());
        productPerformanceTracker.setTotalDeliveriesPerPeriod(event.getTotalDeliveriesPerPeriod());
        productPerformanceTracker.setAverageWeightPerDelivery(event.getAverageWeightPerDelivery());
        this.productAccount.addPerformanceTrackerToForecast(priceParameter.getFromDate(), productPerformanceTracker);
    }


    @EventSourcingHandler
    public void on(ProductStatusReceivedEvent event) {
        this.productId = event.getProductId();
        PriceBucket latestPriceBucket = this.getProductAccount().getLatestActualPriceBucket();
        if (latestPriceBucket.getLatestPurchasePricePerUnitVersion() != event.getCurrentPurchasePrice()) {
            Map<LocalDate, PriceBucket> activeActualPriceBuckets = this.getProductAccount().getActiveActualPriceBuckets();
            Set<LocalDate> keySet = activeActualPriceBuckets.keySet();
            for (LocalDate date : keySet) {
                PriceBucket priceBucket = activeActualPriceBuckets.get(date);
                priceBucket.addPurchasePricePerUnitVersion(event.getCurrentPriceDate(), event.getCurrentPurchasePrice());
                //I AM MAKING AN ASSUMPTION THAT WHEN PURCHASE PRICE CHANGES ADD A NEW VERSION OF MRP TOO<EVEN IF IT IS NOT CHANGED
                priceBucket.addMRPVersion(event.getCurrentPriceDate(), event.getCurrentMRP());
            }


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
        productConfiguration.setDemandWiseProfitSharingRules(event.getDemandWiseProfitSharingRules());
        this.productConfiguration = productConfiguration;
    }

    public void updateProductStatus(UpdateProductStatusCommand command) {
        apply(new ProductStatusReceivedEvent(this.productId, command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    public void addForecastParameters(AddForecastParametersCommand command) {
        apply(new ForecastParametersAddedEvent(
                this.productId, command.getForecastedPriceParameter(), command.getDemandDensity(),
                command.getAverageDemandPerSubscriber(), command.getTotalDeliveriesPerPeriod(),
                command.getAverageWeightPerDelivery()
        ));

    }

    public void addCurrentOfferedPrice(double currentOfferedPrice) {
        apply(new CurrentOfferedPriceAddedEvent(this.productId, currentOfferedPrice));
    }

    public double getLatestOperatingExpensesPerUnitForActuals() {
        return getProductAccount().getLatestActuals().getTotalOperationalExpenses();
    }

    public PriceBucket getLatestActualsPriceBucket() {
        return getProductAccount().getLatestActualPriceBucket();
    }

    public double getLatestOperatingExpensesPerUnitActuals() {
        return getProductAccount().getLatestActuals().getTotalOperationalExpenses();
    }

    public double getLatestOperatingExpensesPerUnitForecast() {
        return getProductAccount().getLatestForecast().getTotalOperationalExpenses();
    }

    public double getLatestMRPForecast() {
        return getProductAccount().getLatestForecast().getLatestMRP();
    }

    public double getLatestMRPActuals() {
        return getProductAccount().getLatestActuals().getLatestMRP();
    }

    public void setProductConfiguration(SetProductConfigurationCommand command) {
        apply(new ProductConfigurationSetEvent(this.productId, command.getDemandCurvePeriod(),
                command.getRevenueChangeThresholdForPriceChange(),
                command.isCrossPriceElasticityConsidered(),
                command.isAdvertisingExpensesConsidered(),
                command.getDemandWiseProfitSharingRules()));
    }

    public double getLatestPurchasePriceActuals() {
        return getProductAccount().getLatestActuals().getLatestPurchasePrice();
    }

    public double getLatestPurchasePriceForecast() {
        return getProductAccount().getLatestForecast().getLatestPurchasePrice();
    }

    public double getLatestMerchantProfitActuals() {
        return getProductAccount().getLatestActuals().getExpectedMerchantProfitPercentage();
    }

    public ProductConfiguration getProductConfiguration() {
        return productConfiguration;
    }

    public DemandWiseProfitSharingRule findProfitSharingRuleByDemandDensity(double demandDensityPercentage) {
        return getProductConfiguration().findDemandWiseProfitSharingRuleByDemandDensity(demandDensityPercentage);
    }

    public double getLatestDemandDensityActuals() {
        return getProductAccount().getLatestDemandDensityActuals();
    }

    public double getLatestDemandDensityForecast() {
        return getProductAccount().getLatestDemandDensityForecast();
    }

    public void setLatestOfferedPriceActuals(double latestOfferedPriceActuals) {
        this.getProductAccount().getLatestActuals().getLatestInstantaneousPerformanceTracker().setOfferedPrice(latestOfferedPriceActuals);
    }
}
