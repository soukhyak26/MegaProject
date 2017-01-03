package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.*;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.*;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.command.exception.ProductDeactivatedException;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.services.forecast.ProductDemandForecastBuilder;
import com.affaince.subscription.product.services.pricing.determinator.DefaultPriceDeterminator;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.PricingOptions;
import com.affaince.subscription.common.vo.ProductForecastParameter;
import com.affaince.subscription.product.web.exception.InvalidProductStatusException;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class Product extends AbstractAnnotatedAggregateRoot<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);
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
    //private List<ProductStatus> productActivationStatusList;
    private boolean isProductActivated = false;
    private ProductDemandTrend productDemandTrend;

    public Product() {

    }

    public Product(String productId, String productName, String categoryId, String subCategoryId, long netQuantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements, Map<SensitivityCharacteristic, Double> sensitiveTo, ProductPricingCategory productPricingCategory, double purchasePrice, double MRP) {
        apply(new ProductRegisteredEvent(productId, productName, categoryId, subCategoryId, netQuantity, quantityUnit, substitutes, complements, sensitiveTo, productPricingCategory, purchasePrice, MRP));
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
        /*this.productActivationStatusList = new ArrayList<>();
        this.productActivationStatusList.add(ProductStatus.PRODUCT_REGISTERED);
        */
        this.productAccount = new ProductAccount(event.getProductId(), event.getProductPricingCategory());
        DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
        final LocalDateTime currentDate = SysDateTime.now();
        final String taggedPriceVersionId = productId + currentDate.toString(format);
        PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(taggedPriceVersionId, event.getPurchasePrice(), event.getMRP(), currentDate);
        this.productAccount.addNewTaggedPriceVersion(taggedPriceVersion);
    }

    //Event for setting product configuration
    /*@EventSourcingHandler
    public void on(ProductPricingConfigurationSetEvent event) {
        if (!this.productActivationStatusList.contains(ProductStatus.PRODUCT_CONFIGURED)) {
            this.productConfiguration = new ProductConfiguration(event.getProductId(), event.getActualsAggregationPeriodForTargetForecast(), event.getDemandCurvePeriod(), event.getTargetChangeThresholdForPriceChange(), event.isCrossPriceElasticityConsidered(), event.isAdvertisingExpensesConsidered(), event.getPricingOptions(), event.getPricingStrategyType());
            this.productActivationStatusList.add(ProductStatus.PRODUCT_CONFIGURED);
        }

    }*/

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
        this.productDemandTrend=event.getProductDemandTrend();
        getProductAccount().addNewPriceRecommendation(event.getNewPriceBucket().getFromDate(), event.getNewPriceBucket());

    }

    //subscription forecast should be updated on the read side. Hence no activity in the event sourcing handler
    @EventSourcingHandler
    public void on(SubscriptionForecastUpdatedEvent event) {
        this.productId = event.getProductId();
        this.getProductConfiguration().setNextForecastDate(event.getForecastEndDate().plusDays(1));
    }


    /*@EventSourcingHandler
    public void on(ManualForecastAddedEvent manualForecastAddedEvent) {
        this.productActivationStatusList.add(ProductStatus.PRODUCT_FORECASTED);
    }

    @EventSourcingHandler
    public void on(ManualStepForecastAddedEvent manualStepForecastAddedEvent) {
        this.productActivationStatusList.add(ProductStatus.PRODUCT_STEPFORECAST_CREATED);
    }*/

    @EventSourcingHandler
    public void on(CurrentPriceContinuedEvent event) {
        PriceBucket latestRecommendedPriceBucket = this.getLatestRecommendedPriceBucket();
        getProductAccount().removeRecommendedPriceBucket(latestRecommendedPriceBucket);
    }

    @EventSourcingHandler
    public void on(ProductActivatedEvent event) {
        this.productId = event.getProductId();
        this.isProductActivated = true;
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

    public boolean isProductActivated() {
        return isProductActivated;
    }

    public ProductDemandTrend getProductDemandTrend() {
        return productDemandTrend;
    }

    public double getLatestPurchasePrice() {
        return getProductAccount().getLatestTaggedPriceVersion().getPurchasePricePerUnit();
    }


    public ProductConfiguration getProductConfiguration() {
        return productConfiguration;
    }

    //for product administrator to configure product
    public void setProductPricingConfiguration(SetProductPricingConfigurationCommand command) throws InvalidProductStatusException, ProductDeactivatedException {
        apply(new ProductPricingConfigurationSetEvent(command.getProductId(), command.getActualsAggregationPeriodForTargetForecast(), command.getTargetChangeThresholdForPriceChange(), command.isCrossPriceElasticityConsidered(), command.isAdvertisingExpensesConsidered(), command.getPricingOptions(), command.getPricingStrategyType(), command.getDemandCurvePeriod(),command.getTentativePercentageChangeInProductDemand()));
        /*final ProductConfigurationValidator validator = new ProductConfigurationValidator();
        try {
            if (validator.isProductReadyForActivation(this.productId, this.productActivationStatusList)) {
                apply(new ProductActivatedEvent(this.productId, this.productName, this.categoryId, this.subCategoryId, this.substitutes, this.complements, this.sensitiveTo, this.netQuantity, quantityUnit));
            }
        } catch (InvalidProductStatusException | ProductDeactivatedException ex) {
            LOGGER.error("Product Activation Status is Invalid or deactivated", ex);
        }*/
    }


    //for receipt from main application thru integration
    public void receiveProductStatus(ReceiveProductStatusCommand command) {
        this.getProductAccount().receiveProductStatus(command);
    }

    public void updateFixedExpenses(UpdateFixedExpenseToProductCommand command) {
        getProductAccount().updateFixedExpenses(command);
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

        if(this.getActivePriceBuckets().size()>=1 && this.getLatestActivePriceBucket().getNumberOfExistingSubscriptions()>=1) {
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
                apply(new OfferedPriceChangedEvent(this.productId, newPriceBucket,productDemandTrend));
            } else {
                //this.getProductAccount().addNewPriceRecommendation(event.getCurrentPriceDate(), newPriceBucket);
                apply(new OfferedPriceRecommendedEvent(this.productId, newPriceBucket,productDemandTrend));
            }
        }
    }

    public void registerManualForecast(ProductForecastParameter[] productForecastParameters) {
        //for (ProductForecastParameter forecastParameter : productForecastParameters) {
            apply(new ManualForecastAddedEvent(productId, productForecastParameters));
        //}
        /*if (!this.productActivationStatusList.contains(ProductStatus.PRODUCT_FORECASTED)) {
            for (ProductForecastParameter forecastParameter : productForecastParameters) {
                apply(new ManualForecastAddedEvent(productId, forecastParameter.getPurchasePricePerUnit(), forecastParameter.getMRP(), forecastParameter.getNumberOfNewSubscriptions(), forecastParameter.getNumberOfChurnedSubscriptions(), forecastParameter.getNumberOfTotalSubscriptions(), forecastParameter.getStartDate(), forecastParameter.getEndDate()));
            }
        }
        final ProductConfigurationValidator validator = new ProductConfigurationValidator();
        try {
            if (validator.isProductReadyForActivation(this.productId, this.productActivationStatusList)) {
                apply(new ProductActivatedEvent(this.productId, this.productName, this.categoryId, this.subCategoryId, this.substitutes, this.complements, this.sensitiveTo, this.netQuantity, quantityUnit));
            }
        } catch (InvalidProductStatusException | ProductDeactivatedException ex) {
            LOGGER.error("Product Activation Status is Invalid or deactivated", ex);
        }*/

    }

    //MOSTLY NOTION OF PSEUDOACTUALS IS NOT NEEDED
    public void registerManualStepForecast() {
        apply(new ManualStepForecastAddedEvent(productId));

/*
        final ProductConfigurationValidator validator = new ProductConfigurationValidator();
        try {
            if (validator.isProductReadyForActivation(this.productId, this.productActivationStatusList)) {
                apply(new ProductActivatedEvent(this.productId, this.productName, this.categoryId, this.subCategoryId, this.substitutes, this.complements, this.sensitiveTo, this.netQuantity, quantityUnit));
            }
        } catch (InvalidProductStatusException | ProductDeactivatedException ex) {
            LOGGER.error("Product Activation Status is Invalid or deactivated", ex);
        }
*/
    }

    public void acceptRecommendedPrice() {
        apply(new OfferedPriceChangedEvent(productId, getLatestRecommendedPriceBucket(),this.productDemandTrend));
    }

    public void overrideRecommendedPrice(double overriddenPriceOrPercent) {
        PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        PriceBucket latestRecommendedPriceBucket = this.getLatestRecommendedPriceBucket();

        //Change price ONLY if difference between latest price and new price is more than 0.5 money? BUT WHAT ABOUT PERCENT DICOUNT.. NEEDS CORRECTION
        if (Math.abs(latestPriceBucket.getOfferedPriceOrPercentDiscountPerUnit() - overriddenPriceOrPercent) > 0.5) {
            PriceBucket newPriceBucket = createNewPriceBucket(productId, latestRecommendedPriceBucket.getTaggedPriceVersion(), overriddenPriceOrPercent, latestRecommendedPriceBucket.getEntityStatus(), latestRecommendedPriceBucket.getFromDate());
            apply(new OfferedPriceChangedEvent(productId, newPriceBucket,this.productDemandTrend));
        }
    }

    public void continueCurrentPrice() {
        apply(new CurrentPriceContinuedEvent(productId));
    }

    public void registerOpeningPrice(double openingPriceOrPercent) {
        this.getProductAccount().registerOpeningPrice(productId, openingPriceOrPercent);
    }

    public void activateProduct() {
        apply(new ProductActivatedEvent(this.productId, this.productName, this.categoryId,
                this.subCategoryId, this.substitutes, this.complements,
                this.sensitiveTo, this.netQuantity, quantityUnit, this.productAccount.getProductPricingCategory()));
    }

    public void calculateExpectedProfitPerPriceBucket(CalculateExpectedProfitPerPriceBucketCommand command) {
        this.getProductAccount().calculateExpectedProfitPerPriceBucket(command);
    }

    public PriceBucket findPriceBucketByPriceBucketId (final String priceBucketId) {
        return this.getProductAccount().findActivePriceBucketByPriceBucketId(priceBucketId);
    }
}
