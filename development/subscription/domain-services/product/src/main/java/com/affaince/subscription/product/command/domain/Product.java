package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.*;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.ReceiveProductStatusCommand;
import com.affaince.subscription.product.command.SetProductPricingConfigurationCommand;
import com.affaince.subscription.product.command.UpdateProductStatusCommand;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.services.forecast.ProductDemandForecastBuilder;
import com.affaince.subscription.product.services.pricing.determinator.DefaultPriceDeterminator;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.PricingOptions;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
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
    private List<ProductStatus> productActivationStatusList;

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

    public PriceBucket getLatestActivePriceBucket() {
        return getProductAccount().getLatestActivePriceBucket();
    }

    public PriceBucket getLatestRecommendedPriceBucket() {
        return getProductAccount().getLatestRecommendedPriceBucket();
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
        apply(new ProductPricingConfigurationSetEvent(command.getProductId(), command.getActualsAggregationPeriodForTargetForecast(), command.getTargetChangeThresholdForPriceChange(), command.isCrossPriceElasticityConsidered(), command.isAdvertisingExpensesConsidered(), command.getPricingOptions(), command.getPricingStrategyType(), command.getDemandCurvePeriod()));
    }


    //When product is "first time " registered by product administrator
    @EventSourcingHandler
    public void on(ProductRegisteredEvent event) {
        this.productId = event.getProductId();
        this.productName = event.getProductName();
        this.categoryId = event.getCategoryId();
        this.subCategoryId = event.getSubCategoryId();
        this.netQuantity = event.getQuantity();
        this.quantityUnit = event.getQuantityUnit();
        this.substitutes = event.getSubstitutes();
        this.complements = event.getComplements();
        this.sensitiveTo = event.getSensitiveTo();
        this.productActivationStatusList = new ArrayList<>();
        this.productActivationStatusList.add(ProductStatus.PRODUCT_REGISTERED);
        this.productAccount = new ProductAccount(event.getProductId(), event.getProductPricingCategory());
    }

    //Event for setting product configuration
    @EventSourcingHandler
    public void on(ProductPricingConfigurationSetEvent event) {
        if (!this.productActivationStatusList.contains(ProductStatus.PRODUCT_CONFIGURED)) {
            this.productConfiguration = new ProductConfiguration(event.getProductId(), event.getActualsAggregationPeriodForTargetForecast(), event.getDemandCurvePeriod(), event.getTargetChangeThresholdForPriceChange(), event.isCrossPriceElasticityConsidered(), event.isAdvertisingExpensesConsidered(), event.getPricingOptions(), event.getPricingStrategyType());
            this.productActivationStatusList.add(ProductStatus.PRODUCT_CONFIGURED);
        }

    }


    //When the new actual offer price is recommended
    //Expire current price bucket and register a new price bucket
    @EventSourcingHandler
    public void on(OfferedPriceRecommendedEvent event) {
        this.productId = event.getProductId();
        PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        double newOfferedPrice = event.getOfferedPricePerUnit();
        //Change price ONLY if difference between latest price and new price is more than 0.5 money
        if (Math.abs(latestPriceBucket.getOfferedPriceOrPercentDiscountPerUnit() - newOfferedPrice) > 0.5) {
            PriceBucket newPriceBucket = createNewPriceBucket(event.getProductId(), event.getTaggedPriceVersion(), event.getOfferedPricePerUnit(), event.getEntityStatus(), event.getCurrentPriceDate());
            //cannot do expiration of latest pricebucket as this is just a recommended price bucket.
            //latestPriceBucket.setToDate(event.getCurrentPriceDate());
            if (productConfiguration.getPricingOptions() == PricingOptions.ACCEPT_AUTOMATED_PRICE_GENERATION) {
                //shall we keep the same date as mentioned in recommended bucket?? for now....yes
                getProductAccount().addNewPriceBucket(newPriceBucket.getFromDate(), newPriceBucket);
            } else {
                this.getProductAccount().addNewPriceRecommendation(event.getCurrentPriceDate(), newPriceBucket);
            }
        }
    }


    //Only for actuals
    //Product status should be received from main application.
    //when the purchase price is changed --it should update new taggedPriceVersion and make all price buckets point to it.
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
        PriceBucket latestPriceBucket = getProductAccount().getLatestActivePriceBucket();
        if (this.getProductAccount().getLatestTaggedPriceVersion().getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            final String taggedPriceVersionId = productId + event.getCurrentPriceDate().toString(format);
            PriceTaggedWithProduct newtaggedPrice = new PriceTaggedWithProduct(taggedPriceVersionId, event.getCurrentPurchasePrice(), event.getCurrentMRP(), event.getCurrentPriceDate());
            this.getProductAccount().addNewTaggedPriceVersion(newtaggedPrice);
        }
        this.getProductAccount().setCurrentStockInUnits(event.getCurrentStockInUnits());

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

    public PriceTaggedWithProduct getLatestTaggedPriceVersion() {
        return getProductAccount().getLatestTaggedPriceVersion();
    }

    public void setLatestOfferedPrice(double offeredPrice) {
        getProductAccount().getLatestActivePriceBucket().setOfferedPriceOrPercentDiscountPerUnit(offeredPrice);
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
        List<ProductForecastView> forecasts = builder.buildForecast(productId, forecastDate, getProductConfiguration().getActualsAggregationPeriodForTargetForecast(), getProductConfiguration().getDemandCurvePeriodInDays());

        for (int i = 0; i < forecasts.size(); i++) {
            //why same start/end dates to all events??
            apply(new SubscriptionForecastUpdatedEvent(productId,
                    forecasts.get(i).getProductVersionId().getFromDate(),
                    forecasts.get(i).getEndDate(),
                    forecasts.get(i).getNewSubscriptions(),
                    forecasts.get(i).getChurnedSubscriptions(),
                    forecasts.get(i).getTotalNumberOfExistingSubscriptions()));
        }


    }

    public void updatePseudoActualsFromActuals(LocalDate forecastDate, ProductDemandForecastBuilder builder) {
        //Whole bunch of logic to add forecast in Product aggregate - NOT NEEDED AS WE ARE NOT KEEPING FORECASTS IN AGGREGATE
        List<ProductForecastView> forecasts = builder.buildForecast(productId, forecastDate, 1, getProductConfiguration().getDemandCurvePeriodInDays());
        //THIS LOOP SHOULD ITERATE SINGLE TIME
        for (int i = 0; i < forecasts.size(); i++) {
            //why same start/end dates to all events??
            apply(new SubscriptionForecastUpdatedEvent(productId,
                    forecasts.get(i).getProductVersionId().getFromDate(),
                    forecasts.get(i).getEndDate(),
                    forecasts.get(i).getNewSubscriptions(),
                    forecasts.get(i).getChurnedSubscriptions(),
                    forecasts.get(i).getTotalNumberOfExistingSubscriptions()));
        }
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

    @EventSourcingHandler
    public void on(ManualForecastAddedEvent manualForecastAddedEvent) {
        this.productActivationStatusList.add(ProductStatus.PRODUCT_FORECASTED);
    }

    @EventSourcingHandler
    public void on(ManualStepForecastAddedEvent manualStepForecastAddedEvent) {
        this.productActivationStatusList.add(ProductStatus.PRODUCT_STEPFORECAST_CREATED);
    }

    public void calculatePrice(DefaultPriceDeterminator defaultPriceDeterminator, ProductDemandTrend productDemandTrend) {
        PriceBucket priceBucket = defaultPriceDeterminator.calculateOfferedPrice(this, productDemandTrend);
        apply(new OfferedPriceRecommendedEvent(productId, priceBucket.getTaggedPriceVersion(), priceBucket.getOfferedPriceOrPercentDiscountPerUnit(), priceBucket.getEntityStatus(), priceBucket.getFromDate()));

    }

    public void registerManualForecast() {
        if (!this.productActivationStatusList.contains(ProductStatus.PRODUCT_FORECASTED)) {
            apply(new ManualForecastAddedEvent(productId));
        }
    }

    public void registerManualStepForecast() {
        if (!this.productActivationStatusList.contains(ProductStatus.PRODUCT_STEPFORECAST_CREATED)) {
            apply(new ManualStepForecastAddedEvent(productId));
        }
    }

    public void acceptRecommendedPrice() {
        apply(new RecommendedPriceAcceptedEvent(productId));
    }

    public void overrideRecommendedPrice(double overriddenPrice) {
        apply(new RecommendedPriceOverriddenEvent(productId, getLatestTaggedPriceVersion(), overriddenPrice, EntityStatus.CREATED, SysDateTime.now()));
    }

    @EventSourcingHandler
    public void on(RecommendedPriceAcceptedEvent event) {
        PriceBucket latestRecommendedPriceBucket = getLatestRecommendedPriceBucket();
        //shall we keep the same date as mentioned in recommended bucket?? for now....yes
        getProductAccount().addNewPriceBucket(latestRecommendedPriceBucket.getFromDate(), latestRecommendedPriceBucket);
        getProductAccount().removeRecommendedPriceBucket(latestRecommendedPriceBucket);
    }

    @EventSourcingHandler
    public void on(RecommendedPriceOverriddenEvent event) {
        this.productId = event.getProductId();
        PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        PriceBucket latestRecommendedPriceBucket = this.getLatestRecommendedPriceBucket();
        double overriddenPriceOrPercent = event.getOverriddenPriceOrPercent();

        //Change price ONLY if difference between latest price and new price is more than 0.5 money? BUT WHAT ABOUT PERCENT DICOUNT.. NEEDS CORRECTION
        if (Math.abs(latestPriceBucket.getOfferedPriceOrPercentDiscountPerUnit() - overriddenPriceOrPercent) > 0.5) {
            PriceBucket newPriceBucket = createNewPriceBucket(event.getProductId(), event.getTaggedPriceVersion(), event.getOverriddenPriceOrPercent(), event.getEntityStatus(), event.getFromDate());
            //cannot do expiration of latest pricebucket as this is just a recommended price bucket.
            //latestPriceBucket.setToDate(event.getCurrentPriceDate());
            this.getProductAccount().addNewPriceBucket(event.getFromDate(), newPriceBucket);
            getProductAccount().removeRecommendedPriceBucket(latestRecommendedPriceBucket);
        }
    }

    public void continueCurrentPrice() {
        apply(new CurrentPriceContinuedEvent(productId));
    }

    @EventSourcingHandler
    public void handle(CurrentPriceContinuedEvent event) {
        PriceBucket latestRecommendedPriceBucket = this.getLatestRecommendedPriceBucket();
        getProductAccount().removeRecommendedPriceBucket(latestRecommendedPriceBucket);
    }
}
