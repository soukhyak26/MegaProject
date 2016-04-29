package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.product.registration.command.AddForecastParametersCommand;
import com.affaince.subscription.product.registration.command.ReceiveProductStatusCommand;
import com.affaince.subscription.product.registration.command.SetProductConfigurationCommand;
import com.affaince.subscription.product.registration.command.UpdateProductStatusCommand;
import com.affaince.subscription.product.registration.command.event.*;
import com.affaince.subscription.product.registration.vo.DemandWiseProfitSharingRule;
import com.affaince.subscription.product.registration.vo.ForecastedPriceParameter;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

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
        return getCurrentProductAccount();
    }

    public PriceBucket getLatestPriceBucket() {
        return getCurrentProductAccount().getLatestPriceBucket();
    }

    private ProductAccount getCurrentProductAccount() {
        return this.calculationBasis == CalculationBasis.FORECAST ?
                this.forecastedProductAccount :
                this.actualProdctAccount;
    }

    private ProductAccount getForecastedProductAccount() {
        return this.forecastedProductAccount;
    }

    private ProductAccount getActualProdctAccount() {
        return this.actualProdctAccount;
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
        return getProductAccount().getLatestPriceBucket().getPurchasePricePerUnit();
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

    public DemandWiseProfitSharingRule findProfitSharingRuleByDemandDensity(double demandDensityPercentage) {
        return getProductConfiguration().findDemandWiseProfitSharingRuleByDemandDensity(demandDensityPercentage);
    }

    public double getLatestDemandDensity() {
        return getProductAccount().getLatestPerformanceTracker().getDemandDensity();
    }

    public CalculationBasis getCalculationBasis() {
        return calculationBasis;
    }

    public void setCalculationBasis(CalculationBasis calculationBasis) {
        this.calculationBasis = calculationBasis;
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

    //Currently assuming it to be for forecast
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
                priceParameter.getNumberOfChurnedSubscritptions()
        );
        getProductAccount().addNewPriceBucket(new LocalDate(priceParameter.getMonthOfYear().get(DateTimeFieldType.year()), priceParameter.getMonthOfYear().get(DateTimeFieldType.monthOfYear()), 1), priceBucket);

        ProductPerformanceTracker productPerformanceTracker = new ProductPerformanceTracker();
        productPerformanceTracker.setFromDate(fromDate);
        productPerformanceTracker.setDemandDensity(priceParameter.getDemandDensity());
        getProductAccount().addPerformanceTracker(fromDate, productPerformanceTracker);
    }

    //Only for actuals
    @EventSourcingHandler
    public void on(ProductStatusReceivedEvent event) {
        this.productId = event.getProductId();
        this.calculationBasis = CalculationBasis.ACTUAL;
        PriceBucket latestPriceBucket = getProductAccount().getLatestPriceBucket();
        if (latestPriceBucket.getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            PriceBucket newPriceBucket = new PriceBucket();
            newPriceBucket.setFromDate(event.getCurrentPriceDate());
            newPriceBucket.setToDate(new LocalDate(9999, 12, 31));
            newPriceBucket.setPurchasePricePerUnit(event.getCurrentPurchasePrice());
            newPriceBucket.setMRP(event.getCurrentMRP());
            this.getProductAccount().addNewPriceBucket(event.getCurrentPriceDate(), newPriceBucket);
            //PriceDeterminator priceDeterminator = new DefaultPriceDeterminator(null);
            //priceDeterminator.calculateOfferedPrice(this);
        }
        this.getProductAccount().setCurrentStockInUnits(event.getCurrentStockInUnits());
    }

    @EventHandler
    public void on(ProductStatusUpdatedEvent event) {
        this.productId = event.getProductId();
        this.calculationBasis = CalculationBasis.ACTUAL;
        PriceBucket latestPriceBucket = getProductAccount().getLatestPriceBucket();
        if (latestPriceBucket.getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            PriceBucket newPriceBucket = new PriceBucket();
            newPriceBucket.setFromDate(event.getCurrentPriceDate());
            newPriceBucket.setToDate(new LocalDate(9999, 12, 31));
            newPriceBucket.setPurchasePricePerUnit(event.getCurrentPurchasePrice());
            newPriceBucket.setMRP(event.getCurrentMRP());
            this.getProductAccount().addNewPriceBucket(event.getCurrentPriceDate(), newPriceBucket);
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
        apply(new ProductStatusUpdatedEvent(this.productId, command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    //for receipt from main application thru integration
    public void receiveProductStatus(ReceiveProductStatusCommand command) {
        apply(new ProductStatusUpdatedEvent(this.productId, command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    public void addForecastParameters(AddForecastParametersCommand command) {
        apply(new ForecastParametersAddedEvent(
                this.productId, command.getForecastedPriceParameter()));

    }

    public void addCurrentOfferedPrice(double currentOfferedPrice) {
        apply(new CurrentOfferedPriceAddedEvent(this.productId, currentOfferedPrice));
    }

    public double getLatestMRP() {
        return getProductAccount().getLatestPriceBucket().getMRP();
    }

    public void setLatestOfferedPrice(double offeredPrice) {
        getProductAccount().getLatestPriceBucket().setOfferedPricePerUnit(offeredPrice);
    }


    public void updateForecastFromActuals() {
        ProductAccount actualAccount=this.getActualProdctAccount();


    }
}
