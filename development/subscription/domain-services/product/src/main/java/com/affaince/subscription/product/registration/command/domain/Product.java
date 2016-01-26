package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.product.registration.command.AddForecastParametersCommand;
import com.affaince.subscription.product.registration.command.SetProductConfigurationCommand;
import com.affaince.subscription.product.registration.command.UpdateProductStatusCommand;
import com.affaince.subscription.product.registration.command.event.*;
import com.affaince.subscription.product.registration.vo.DemandWiseProfitSharingRule;
import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.DateTimeFieldType;
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
    private long netQuantity;
    private QuantityUnit quantityUnit;
    private List<String> substitutes;
    private List<String> complements;
    private ProductConfiguration productConfiguration;
    private ProductAccount forecastedProductAccount = new ProductAccount();
    private ProductAccount actualProdctAccount = new ProductAccount();
    private double offeredPrice;
    private double latestOfferedPriceActuals;
    private Map<SensitivityCharacteristic, Double> sensitiveTo;
    private transient CalculationBasis calculationBasis = CalculationBasis.ACTUAL;

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
        if (this.calculationBasis == CalculationBasis.FORECAST)
            return this.forecastedProductAccount;
        else
            return this.actualProdctAccount;
    }

    public PriceBucket getLatestPriceBucket() {
        if (this.calculationBasis == CalculationBasis.FORECAST)
            return forecastedProductAccount.getLatestPriceBucket();
        else
            return actualProdctAccount.getLatestPriceBucket();
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
        forecastedProductAccount = new ProductAccount();
    }

    //Currently assuming it to be for actual price
    @EventSourcingHandler
    public void on(OfferedPriceUpdatedEvent event) {
        this.productId = event.getProductId();
        this.calculationBasis = CalculationBasis.ACTUAL;
        PriceBucket lastPriceBucket = this.getLatestPriceBucket();
        PriceBucket newPriceBucket = new PriceBucket(lastPriceBucket);
        newPriceBucket.setFromDate(event.getCurrentPriceDate());
        newPriceBucket.setOfferedPricePerUnit(event.getOfferedPrice());
        lastPriceBucket.setToDate(LocalDate.now());
        this.getProductAccount().addNewPriceBucket(event.getCurrentPriceDate(), newPriceBucket);
    }

    //Currently assuming it to be for foreast
    @EventSourcingHandler
    public void on(ForecastParametersAddedEvent event) {
        this.productId = event.getProductId();
        this.calculationBasis = CalculationBasis.FORECAST;
        final ForecastedPriceParameter priceParameter = event.getForecastedPriceParamter();
        final LocalDate fromDate = new LocalDate(priceParameter.getMonthOfYear().get(DateTimeFieldType.year()), priceParameter.getMonthOfYear().get(DateTimeFieldType.monthOfYear()), 1);
        final LocalDate toDate = new LocalDate(fromDate.getYear(), fromDate.getMonthOfYear(), fromDate.dayOfMonth().getMaximumValue());

        PriceBucket priceBucket = new PriceBucket(
                priceParameter.getPurchasePricePerUnit(),
                priceParameter.getMRP(),
                fromDate,
                toDate,
                priceParameter.getNumberOfNewCustomersAssociatedWithAPrice(),
                priceParameter.getNumberOfChurnedCustomersAssociatedWithAPrice()
        );
        getProductAccount().addNewPriceBucket(new LocalDate(priceParameter.getMonthOfYear().get(DateTimeFieldType.year()), priceParameter.getMonthOfYear().get(DateTimeFieldType.monthOfYear()), 1), priceBucket);

        ProductPerformanceTracker productPerformanceTracker = new ProductPerformanceTracker();
        productPerformanceTracker.setMonthOfYear(priceParameter.getMonthOfYear());
        productPerformanceTracker.setDemandDensity(event.getDemandDensity());
        productPerformanceTracker.setTotalDeliveriesPerPeriod(event.getTotalDeliveriesPerPeriod());
        productPerformanceTracker.setAverageWeightPerDelivery(event.getAverageWeightPerDelivery());
        getProductAccount().addPerformanceTracker(priceParameter.getMonthOfYear(), productPerformanceTracker);
    }

    //Only for actuals
    @EventSourcingHandler
    public void on(ProductStatusReceivedEvent event) {
        this.productId = event.getProductId();
        this.calculationBasis = CalculationBasis.ACTUAL;
        PriceBucket latestPriceBucket = getProductAccount().getLatestPriceBucket();
        if (latestPriceBucket.getLatestPurchasePricePerUnitVersion() != event.getCurrentPurchasePrice()) {
            Map<LocalDate, PriceBucket> activeActualPriceBuckets = this.actualProdctAccount.getActivePriceBuckets();
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


    public double getLatestOperatingExpensesPerUnit() {
        return getProductAccount().getLatestPerformanceTracker().getTotalOperationalExpenses();
    }

    public double getLatestMRP() {
        return getProductAccount().getLatestPerformanceTracker().getLatestMRP();
    }


    public void setProductConfiguration(SetProductConfigurationCommand command) {
        apply(new ProductConfigurationSetEvent(this.productId, command.getDemandCurvePeriod(),
                command.getRevenueChangeThresholdForPriceChange(),
                command.isCrossPriceElasticityConsidered(),
                command.isAdvertisingExpensesConsidered(),
                command.getDemandWiseProfitSharingRules()));
    }


    public double getLatestPurchasePrice() {
        return getProductAccount().getLatestPerformanceTracker().getLatestPurchasePrice();
    }

    public double getLatestMerchantProfit() {
        return getProductAccount().getLatestPerformanceTracker().getExpectedMerchantProfitPercentage();
    }

    public ProductConfiguration getProductConfiguration() {
        return productConfiguration;
    }

    public DemandWiseProfitSharingRule findProfitSharingRuleByDemandDensity(double demandDensityPercentage) {
        return getProductConfiguration().findDemandWiseProfitSharingRuleByDemandDensity(demandDensityPercentage);
    }

    public double getLatestDemandDensity() {
        return getProductAccount().getLatestDemandDensity();
    }

    public void setLatestOfferedPrice(double latestOfferedPriceActuals) {
        this.getProductAccount().getLatestPerformanceTracker().getLatestInstantaneousPerformanceTracker().setOfferedPrice(latestOfferedPriceActuals);
    }

    public CalculationBasis getCalculationBasis() {
        return calculationBasis;
    }

    public void setCalculationBasis(CalculationBasis calculationBasis) {
        this.calculationBasis = calculationBasis;
    }
}
