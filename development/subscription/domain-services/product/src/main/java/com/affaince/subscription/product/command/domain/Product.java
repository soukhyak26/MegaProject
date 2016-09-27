package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import com.affaince.subscription.product.command.ReceiveProductStatusCommand;
import com.affaince.subscription.product.command.SetProductPricingConfigurationCommand;
import com.affaince.subscription.product.command.UpdateProductStatusCommand;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.services.forecast.ProductDemandForecastBuilder;
import com.affaince.subscription.product.services.pricing.determinator.DefaultPriceDeterminator;
import com.affaince.subscription.product.vo.DemandGrowthAndChurnForecast;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.ProductPricingCategory;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
    private ProductAccount productAccount;
    private Map<SensitivityCharacteristic, Double> sensitiveTo;

    public Product() {

    }

    public Product(String productId, String productName, String categoryId, String subCategoryId, long netQuantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements, Map<SensitivityCharacteristic, Double> sensitiveTo, ProductPricingCategory productPricingCategory) {
        apply(new ProductRegisteredEvent(productId, productName, categoryId, subCategoryId, netQuantity, quantityUnit, substitutes, complements, sensitiveTo, productPricingCategory));
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


    public ProductConfiguration getProductConfiguration() {
        return productConfiguration;
    }

    //for product administrator to configure product
    public void setProductPricingConfiguration(SetProductPricingConfigurationCommand command) {
        apply(new ProductPricingConfigurationSetEvent(this.productId,
                command.getTargetChangeThresholdForPriceChange(),
                command.isCrossPriceElasticityConsidered(),
                command.isAdvertisingExpensesConsidered()));
    }


    //When product is "first time " registered by product administrator
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
        this.productAccount = new ProductAccount(event.getProductId(), event.getProductPricingCategory());
    }

    //When the new actual offer price is recommended
    //Expire current price bucket and register a new price bucket
    @EventSourcingHandler
    public void on(OfferedPriceChangedEvent event) {
        this.productId = event.getProductId();
        PriceBucket latestPriceBucket = this.getLatestPriceBucket();
        double newOfferedPrice = event.getOfferedPricePerUnit();
        //Change price ONLY if difference between latest price and new price is more than 0.5 money
        if (Math.abs(latestPriceBucket.getOfferedPriceOrPercentDiscountPerUnit() - newOfferedPrice) > 0.5) {
            PriceBucket newPriceBucket = createNewPriceBucket(event.getProductId(), event.getTaggedPriceVersion(), event.getOfferedPricePerUnit(), event.getEntityStatus(), event.getCurrentPriceDate());
            latestPriceBucket.setToDate(event.getCurrentPriceDate());
            this.getProductAccount().addNewPriceBucket(event.getCurrentPriceDate(), newPriceBucket);
        }
    }


    //Only for actuals
    //Product status should be received from main application.
    //when the purchase price is changed --it should create new price bucket,by stalling all existing price buckets
    @EventSourcingHandler
    public void on(ProductStatusReceivedEvent event) {
        this.productId = event.getProductId();

        if (this.getProductAccount().getLatestTaggedPriceVersion().getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            final String taggedPriceVersionId = productId + event.getCurrentPriceDate().toString(format);
            PriceTaggedWithProduct newtaggedPrice = new PriceTaggedWithProduct(taggedPriceVersionId, event.getCurrentPurchasePrice(), event.getCurrentMRP(), event.getCurrentPriceDate());
            this.getProductAccount().addNewTaggedPriceVersion(newtaggedPrice);
        }
        this.getProductAccount().setCurrentStockInUnits(event.getCurrentStockInUnits());
    }

    //Only for actuals
    //Product status should be received from main application.
    //when the purchase price is changed --it should create new price bucket,by stalling all existing price buckets

    @EventHandler
    public void on(ProductStatusUpdatedEvent event) {
        this.productId = event.getProductId();
        PriceBucket latestPriceBucket = getProductAccount().getLatestPriceBucket();
        if (this.getProductAccount().getLatestTaggedPriceVersion().getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            final String taggedPriceVersionId = productId + event.getCurrentPriceDate().toString(format);
            PriceTaggedWithProduct newtaggedPrice = new PriceTaggedWithProduct(taggedPriceVersionId, event.getCurrentPurchasePrice(), event.getCurrentMRP(), event.getCurrentPriceDate());
            this.getProductAccount().addNewTaggedPriceVersion(newtaggedPrice);
        }
        this.getProductAccount().setCurrentStockInUnits(event.getCurrentStockInUnits());

    }

    //Event for setting product configuration
    @EventSourcingHandler
    public void on(ProductPricingConfigurationSetEvent event) {
        this.productId = event.getProductId();
        final ProductConfiguration productConfiguration = new ProductConfiguration();
        // productConfiguration.setDemandCurvePeriod(event.getDemandCurvePeriod());
        productConfiguration.setTargetChangeThresholdForPriceChange(event.getTargetChangeThresholdForPriceChange());
        productConfiguration.setCrossPriceElasticityConsidered(event.isCrossPriceElasticityConsidered());
        productConfiguration.setAdvertisingExpensesConsidered(event.isAdvertisingExpensesConsidered());
        // productConfiguration.setDemandWiseProfitSharingRules(event.getDemandWiseProfitSharingRules());
        this.productConfiguration = productConfiguration;
    }

    //subscription forecast should be updated on the read side. Hence no activity in the event sourcing handler
    @EventSourcingHandler
    public void on(SubscriptionForecastUpdatedEvent event) {
        this.productId = event.getProductId();
        this.getProductConfiguration().setNextForecastDate(event.getForecastEndDate().plusDays(1));
    }

    //Product status should be received from main application
    public void updateProductStatus(UpdateProductStatusCommand command) {
        apply(new ProductStatusUpdatedEvent(this.productId, command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    //for receipt from main application thru integration
    public void receiveProductStatus(ReceiveProductStatusCommand command) {
        apply(new ProductStatusUpdatedEvent(this.productId, command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    public List<PriceBucket> findBucketsWithSamePurchasePrice(PriceBucket priceBucket) {
        return getProductAccount().findBucketsWithSamePurchasePrice(priceBucket);
    }

    public PriceBucket findEarlierPriceBucketInActivePriceBuckets(PriceBucket priceBucket) {
        return getProductAccount().findEarlierPriceBucketInActivePriceBuckets(priceBucket);
    }

    public PriceBucket findEarlierPriceBucketTo(PriceBucket priceBucket, List<PriceBucket> priceBuckets) {
        return getProductAccount().findEarlierPriceBucketTo(priceBucket, priceBuckets);
    }

    //When current offered price are set/overriden by product administrator.
    //Ideally it should update latest price bucket with merchant overriden price.
    //Some work is needed to be done on this API
    public void addCurrentOfferedPrice(double currentOfferedPrice) {
        apply(new CurrentOfferedPriceAddedEvent(this.productId, currentOfferedPrice));
    }

    public double getLatestMRP() {
        return getProductAccount().getLatestTaggedPriceVersion().getMRP();
    }

    public void setLatestOfferedPrice(double offeredPrice) {
        getProductAccount().getLatestPriceBucket().setOfferedPriceOrPercentDiscountPerUnit(offeredPrice);
    }

    public double getRevenueChangeThresholdForPriceChange() {
        return getProductConfiguration().getTargetChangeThresholdForPriceChange();
    }

    public PriceBucket createNewPriceBucket(String productId, PriceTaggedWithProduct taggedPriceVersion, double offeredPriceOrPercent, EntityStatus entityStatus, LocalDateTime fromDate) {
        return getProductAccount().createNewPriceBucket(productId, taggedPriceVersion, offeredPriceOrPercent, entityStatus, fromDate);
    }


    //business logic to create forecast for a product and store it on read side.
    //There are two forecasts to be created- one is daily forecast based on historical data until last day
    //periodic (monthly,weekly,quarterly forecast) where forecast is created for current period( month,week,quarter) using historical data until last period.

    public void updateForecastFromActuals(LocalDate forecastDate, ProductDemandForecastBuilder builder) {
        //Whole bunch of logic to add forecast in Product aggregate - NOT NEEDED AS WE ARE NOT KEEPING FORECASTS IN AGGREGATE
        DemandGrowthAndChurnForecast forecast = builder.buildForecast(productId, getProductConfiguration().getActualsAggregationPeriodForTargetForecast());
        apply(new SubscriptionForecastUpdatedEvent(productId,
                forecast.getForecastStartDate(),
                forecast.getForecastEndDate(),
                forecast.getForecastedNewSubscriptionCount(),
                forecast.getForecastedChurnedSubscriptionCount(),
                forecast.getForecastedTotalSubscriptionCount()));
    }

    public void updatePseudoActualsFromActuals(LocalDate forecastDate, ProductDemandForecastBuilder builder) {
        //Whole bunch of logic to add forecast in Product aggregate - NOT NEEDED AS WE ARE NOT KEEPING FORECASTS IN AGGREGATE
        DemandGrowthAndChurnForecast forecast = builder.buildForecast(productId, 1);
        apply(new SubscriptionPseudoActualsUpdatedEvent(
                productId,
                forecast.getForecastStartDate(),
                forecast.getForecastEndDate(),
                forecast.getForecastedNewSubscriptionCount(),
                forecast.getForecastedChurnedSubscriptionCount(),
                forecast.getForecastedTotalSubscriptionCount()));
    }

    public Map<LocalDateTime, PriceBucket> getActivePriceBuckets() {
        return this.getProductAccount().getActivePriceBuckets();
    }

    @EventSourcingHandler
    public void on(ProductSubscriptionRegisteredEvent productSubscriptionRegisteredEvent) {

    }

    @EventSourcingHandler
    public void on(ProductChurnRegisteredEvent productChurnRegisteredEvent) {

    }

    public void calculatePrice(DefaultPriceDeterminator defaultPriceDeterminator, ProductDemandTrend productDemandTrend) {
        PriceBucket priceBucket = defaultPriceDeterminator.calculateOfferedPrice(this, productDemandTrend);
        apply(new OfferedPriceChangedEvent(productId, priceBucket.getTaggedPriceVersion(), priceBucket.getOfferedPriceOrPercentDiscountPerUnit(), priceBucket.getEntityStatus(), priceBucket.getFromDate()));

    }


}
