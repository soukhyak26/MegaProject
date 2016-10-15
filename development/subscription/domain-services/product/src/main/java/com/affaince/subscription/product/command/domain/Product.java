package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.*;
import com.affaince.subscription.product.command.ReceiveProductStatusCommand;
import com.affaince.subscription.product.command.SetProductPricingConfigurationCommand;
import com.affaince.subscription.product.command.UpdateProductStatusCommand;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.services.forecast.ProductDemandForecastBuilder;
import com.affaince.subscription.product.services.pricing.determinator.DefaultPriceDeterminator;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.PricingOptions;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

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
    public void on(OfferedPriceChangedEvent event) {
        getProductAccount().addNewPriceBucket(event.getNewPriceBucket().getFromDate(), event.getNewPriceBucket());
        if (productConfiguration.getPricingOptions() != PricingOptions.ACCEPT_AUTOMATED_PRICE_GENERATION) {
            getProductAccount().removeRecommendedPriceBucket(event.getNewPriceBucket());
        }

    }


    @EventSourcingHandler
    public void on(OfferedPriceRecommendedEvent event) {
        getProductAccount().addNewPriceRecommendation(event.getNewPriceBucket().getFromDate(), event.getNewPriceBucket());

    }


    //subscription forecast should be updated on the read side. Hence no activity in the event sourcing handler
    @EventSourcingHandler
    public void on(SubscriptionForecastUpdatedEvent event) {
        this.productId = event.getProductId();
        this.getProductConfiguration().setNextForecastDate(event.getForecastEndDate().plusDays(1));
    }


    @EventSourcingHandler
    public void on(ManualForecastAddedEvent manualForecastAddedEvent) {
        this.productActivationStatusList.add(ProductStatus.PRODUCT_FORECASTED);
    }

    @EventSourcingHandler
    public void on(ManualStepForecastAddedEvent manualStepForecastAddedEvent) {
        this.productActivationStatusList.add(ProductStatus.PRODUCT_STEPFORECAST_CREATED);
    }


    @EventSourcingHandler
    public void handle(CurrentPriceContinuedEvent event) {
        PriceBucket latestRecommendedPriceBucket = this.getLatestRecommendedPriceBucket();
        getProductAccount().removeRecommendedPriceBucket(latestRecommendedPriceBucket);
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


    //for receipt from main application thru integration
    public void receiveProductStatus(ReceiveProductStatusCommand command) {
        this.getProductAccount().receiveProductStatus(command);
    }

    //Product status should be received from main application
    public void updateProductStatus(UpdateProductStatusCommand command) {
        this.getProductAccount().updateProductStatus(command);
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


    public void calculatePrice(DefaultPriceDeterminator defaultPriceDeterminator, ProductDemandTrend productDemandTrend) {
        PriceBucket priceBucket = defaultPriceDeterminator.calculateOfferedPrice(this, productDemandTrend);
        PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        double newOfferedPriceOrPercent = priceBucket.getOfferedPriceOrPercentDiscountPerUnit();
        //Change price ONLY if difference between latest price and new price is more than 0.5 money
        if (Math.abs(latestPriceBucket.getOfferedPriceOrPercentDiscountPerUnit() - newOfferedPriceOrPercent) > 0.5) {
            PriceBucket newPriceBucket = createNewPriceBucket(productId, priceBucket.getTaggedPriceVersion(), newOfferedPriceOrPercent, priceBucket.getEntityStatus(), priceBucket.getFromDate());
            //cannot do expiration of latest pricebucket as this is just a recommended price bucket.
            //latestPriceBucket.setToDate(event.getCurrentPriceDate());
            if (productConfiguration.getPricingOptions() == PricingOptions.ACCEPT_AUTOMATED_PRICE_GENERATION) {
                //shall we keep the same date as mentioned in recommended bucket?? for now....yes
                apply(new OfferedPriceChangedEvent(this.productId, newPriceBucket));
            } else {
                //this.getProductAccount().addNewPriceRecommendation(event.getCurrentPriceDate(), newPriceBucket);
                apply(new OfferedPriceRecommendedEvent(this.productId, newPriceBucket));
            }
        }


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
        apply(new OfferedPriceChangedEvent(productId, getLatestRecommendedPriceBucket()));
    }

    public void overrideRecommendedPrice(double overriddenPriceOrPercent) {
        PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        PriceBucket latestRecommendedPriceBucket = this.getLatestRecommendedPriceBucket();

        //Change price ONLY if difference between latest price and new price is more than 0.5 money? BUT WHAT ABOUT PERCENT DICOUNT.. NEEDS CORRECTION
        if (Math.abs(latestPriceBucket.getOfferedPriceOrPercentDiscountPerUnit() - overriddenPriceOrPercent) > 0.5) {
            PriceBucket newPriceBucket = createNewPriceBucket(productId, latestRecommendedPriceBucket.getTaggedPriceVersion(), overriddenPriceOrPercent, latestRecommendedPriceBucket.getEntityStatus(), latestRecommendedPriceBucket.getFromDate());
            //cannot do expiration of latest pricebucket as this is just a recommended price bucket.
            //latestPriceBucket.setToDate(event.getCurrentPriceDate());
            //  this.getProductAccount().addNewPriceBucket(event.getFromDate(), newPriceBucket);
            //getProductAccount().removeRecommendedPriceBucket(latestRecommendedPriceBucket);
            apply(new OfferedPriceChangedEvent(productId, newPriceBucket));
        }
    }

    public void continueCurrentPrice() {
        apply(new CurrentPriceContinuedEvent(productId));
    }

}
