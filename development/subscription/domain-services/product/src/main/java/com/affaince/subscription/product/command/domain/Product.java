package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.*;
import com.affaince.subscription.common.vo.ProductForecastParameter;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.ReceiveProductStatusCommand;
import com.affaince.subscription.product.command.SetProductPricingConfigurationCommand;
import com.affaince.subscription.product.command.UpdateFixedExpenseToProductCommand;
import com.affaince.subscription.product.command.event.*;
import com.affaince.subscription.product.command.exception.ProductDeactivatedException;
import com.affaince.subscription.product.factory.PriceBucketFactory;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.services.forecast.ForecastFinderService;
import com.affaince.subscription.product.services.forecast.ProductDemandForecastBuilder;
import com.affaince.subscription.product.services.operatingexpense.OperatingExpenseService;
import com.affaince.subscription.product.services.pricing.determinator.DefaultPriceDeterminator;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.PricingOptions;
import com.affaince.subscription.product.web.exception.InvalidProductStatusException;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.YearMonth;
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
        final LocalDate currentDate = SysDate.now();
        final String taggedPriceVersionId = productId + currentDate.toString(format);
        PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(taggedPriceVersionId, event.getPurchasePrice(), event.getMRP(), currentDate);
        this.productAccount.addNewTaggedPriceVersion(taggedPriceVersion);
    }

    //Event for setting product configuration
    @EventSourcingHandler
    public void on(ProductPricingConfigurationSetEvent event) {
            this.productConfiguration = new ProductConfiguration(event.getProductId(), event.getActualsAggregationPeriodForTargetForecast(), event.getDemandCurvePeriod(), event.getTargetChangeThresholdForPriceChange(), event.isCrossPriceElasticityConsidered(), event.isAdvertisingExpensesConsidered(), event.getPricingOptions(), event.getPricingStrategyType(),event.getTentativePercentageChangeInProductDemand());
    }




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
        apply(new ProductPricingConfigurationSetEvent(command.getProductId(), command.getActualsAggregationPeriodForTargetForecast(), command.getTargetChangeThresholdForPriceChange(), command.isCrossPriceElasticityConsidered(), command.isAdvertisingExpensesConsidered(), command.getPricingOptions(), command.getPricingStrategyType(), command.getDemandCurvePeriod(), command.getTentativePercentageChangeInProductDemand()));
    }


    //for receipt from main application thru integration
    public void receiveProductStatus(ReceiveProductStatusCommand command, OperatingExpenseService operatingExpenseService) {
        this.getProductAccount().receiveProductStatus(command, operatingExpenseService);
    }

    public void updateFixedExpenses(UpdateFixedExpenseToProductCommand command, OperatingExpenseService operatingExpenseService) {
        getProductAccount().updateFixedExpenses(command, operatingExpenseService);
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
        LocalDate earmarkedAnnualEndDate = new LocalDate(YearMonth.now().getYear(), 12, 31);
        for (int i = 0; i < forecasts.size(); i++) {
            //why same start/end dates to all events??
            apply(new SubscriptionForecastUpdatedEvent(productId,
                    forecasts.get(i).getProductVersionId().getFromDate(),
                    forecasts.get(i).getEndDate(),
                    forecasts.get(i).getNewSubscriptions(),
                    forecasts.get(i).getChurnedSubscriptions(),
                    forecasts.get(i).getTotalNumberOfExistingSubscriptions()));
            if (forecasts.get(i).getEndDate().equals(earmarkedAnnualEndDate) ||
                    (forecasts.get(i).getProductVersionId().getFromDate().isBefore(earmarkedAnnualEndDate) && forecasts.get(i).getEndDate().isAfter(earmarkedAnnualEndDate))) {
                apply(new AnnualForecastCreatedEvent(productId, forecasts.get(i).getProductVersionId().getFromDate(), forecasts.get(i).getEndDate(), this.getLatestTaggedPriceVersion().getPurchasePricePerUnit(), this.getLatestTaggedPriceVersion().getMRP(), forecasts.get(i).getNewSubscriptions(), forecasts.get(i).getChurnedSubscriptions(), forecasts.get(i).getTotalNumberOfExistingSubscriptions()));
            }
        }
    }

    public void updatePseudoActualsFromActuals(LocalDate forecastDate, ProductDemandForecastBuilder builder) {

        if (this.getActivePriceBuckets().size() >= 1 && this.getLatestActivePriceBucket().getNumberOfExistingSubscriptions() >= 1) {
            //Whole bunch of logic to add forecast in Product aggregate - NOT NEEDED AS WE ARE NOT KEEPING FORECASTS IN AGGREGATE
            List<ProductForecastView> forecasts = builder.buildForecast(productId, forecastDate, 1, getProductConfiguration().getDemandCurvePeriodInDays());

            for (int i = 0; i < forecasts.size(); i++) {
                //why same start/end dates to all events??
                apply(new SubscriptionPseudoActualsUpdatedEvent(productId,
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



/*
    public void registerSingularManualForecast(LocalDate startDate, LocalDate endDate, double purchasePricePerUnit, double mrp, long numberOfNewSubscriptions, long numberOfChurnedSubscriptions, ProductForecastStatus productForecastStatus) {
        apply(new ManualSingularForecastAddedEvent(productId, startDate, endDate, purchasePricePerUnit, mrp, numberOfNewSubscriptions, numberOfChurnedSubscriptions, productForecastStatus));
    }
*/

    public void registerManualForecast(ProductForecastParameter[] productForecastParameters, ForecastFinderService forecastFinderService) {
        //for (ProductForecastParameter forecastParameter : productForecastParameters) {
        apply(new ManualForecastAddedEvent(productId, productForecastParameters));
        //Annual forecast has to be captured for financial year. So lets have earmerked date for end of current financial year.
        LocalDate earmarkedAnnualEndDate = new LocalDate(YearMonth.now().getYear(), 12, 31);
        // Forecast can be fed starting from mid month of an year or from January . The same  needs to be checked
        for (ProductForecastParameter periodWiseForecast : productForecastParameters) {
            //if forecast is entered for December/last quarter OR period wise forecast is falling across financial year
            if (periodWiseForecast.getEndDate().equals(earmarkedAnnualEndDate) ||
                    (periodWiseForecast.getStartDate().isBefore(earmarkedAnnualEndDate) && periodWiseForecast.getEndDate().isAfter(earmarkedAnnualEndDate))) {
               //obtain total subscription count in the forecast earlier than this forecast
                List<ProductForecastView> earlierForecasts=forecastFinderService.findForecastsEarlierThan(productId, periodWiseForecast.getEndDate());
                long earlierTotalSubscriptionCount=0;
                if(null != earlierForecasts && earlierForecasts.size()>0) {
                    earlierTotalSubscriptionCount = earlierForecasts.get(0).getTotalNumberOfExistingSubscriptions();
                }
                //Add current new subscriptions and deduct churned subscription in the current forecast from earlier total forecast so as to obtain last period forecast.
                long revisedTotalSubscriptionCount = earlierTotalSubscriptionCount + periodWiseForecast.getNumberOfNewSubscriptions() - periodWiseForecast.getNumberOfChurnedSubscriptions();
                //Send a single AnnualForecast event for the last month/period of a finanical year as the same should be consumed by business account for finding prchase provision
                apply(new AnnualForecastCreatedEvent(productId, periodWiseForecast.getStartDate(), periodWiseForecast.getEndDate(), periodWiseForecast.getPurchasePricePerUnit(), periodWiseForecast.getMRP(), periodWiseForecast.getNumberOfNewSubscriptions(), periodWiseForecast.getNumberOfChurnedSubscriptions(), revisedTotalSubscriptionCount));
            }
        }
    }

    //MOSTLY NOTION OF PSEUDOACTUALS IS NOT NEEDED
    public void registerManualStepForecast() {
        apply(new ManualStepForecastAddedEvent(productId));
    }

    public void calculatePrice(String productId,LocalDateTime fromDate,DefaultPriceDeterminator defaultPriceDeterminator, PricingOptions pricingOptions,ProductDemandTrend productDemandTrend) {
        PriceBucket priceBucket = defaultPriceDeterminator.calculateOfferedPrice(this, productDemandTrend);
        PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        double newOfferedPriceOrPercent = priceBucket.getFixedOfferedPriceOrPercentDiscountPerUnit();
        //Change price ONLY if difference between latest price and new price is more than 0.5 money
        if (Math.abs(latestPriceBucket.getFixedOfferedPriceOrPercentDiscountPerUnit() - newOfferedPriceOrPercent) > 0.5) {

            //PriceBucket newPriceBucket = createNewPriceBucket(productId, priceBucket.getLatestTaggedPriceVersion(), newOfferedPriceOrPercent, priceBucket.getEntityStatus(), priceBucket.getFromDate(),priceBucketFactory);
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MMddyyyyHHmmsss");
            String priceBucketId = "" + productId + "_" + fromDate.toString(fmt);

            //cannot do expiration of latest pricebucket as this is just a recommended price bucket.
            //latestPriceBucket.setToDate(event.getCurrentPriceDate());
            if (pricingOptions == PricingOptions.ACCEPT_AUTOMATED_PRICE_GENERATION) {
                //shall we keep the same date as mentioned in recommended bucket?? for now....yes
                apply(new OfferedPriceChangedEvent(this.productId, priceBucket.getPriceBucketId(), priceBucket.getProductPricingCategory(), priceBucket.getLatestTaggedPriceVersion(), priceBucket.getNumberOfNewSubscriptions(),priceBucket.getNumberOfChurnedSubscriptions(), priceBucket.getNumberOfExistingSubscriptions(), priceBucket.getFromDate(), priceBucket.getToDate(),priceBucket.getEntityStatus(), priceBucket.getFixedOfferedPriceOrPercentDiscountPerUnit(),productDemandTrend,pricingOptions));
            } else {
                //this.getProductAccount().addNewPriceRecommendation(event.getCurrentPriceDate(), newPriceBucket);
                apply(new OfferedPriceRecommendedEvent(this.productId, priceBucket.getPriceBucketId(), priceBucket.getProductPricingCategory(), priceBucket.getLatestTaggedPriceVersion(), priceBucket.getNumberOfNewSubscriptions(), priceBucket.getNumberOfChurnedSubscriptions(), priceBucket.getNumberOfExistingSubscriptions(), priceBucket.getFromDate(), priceBucket.getToDate(), priceBucket.getEntityStatus(), priceBucket.getFixedOfferedPriceOrPercentDiscountPerUnit(), productDemandTrend));
            }
        }
    }
    public void acceptRecommendedPrice() {
        PriceBucket newPriceBucket=this.getLatestRecommendedPriceBucket();
        apply(new OfferedPriceChangedEvent(productId,newPriceBucket.getPriceBucketId(), newPriceBucket.getProductPricingCategory(), newPriceBucket.getLatestTaggedPriceVersion(), newPriceBucket.getNumberOfNewSubscriptions(), newPriceBucket.getNumberOfChurnedSubscriptions(), newPriceBucket.getNumberOfExistingSubscriptions(), newPriceBucket.getFromDate(), newPriceBucket.getToDate(), newPriceBucket.getEntityStatus(), newPriceBucket.getFixedOfferedPriceOrPercentDiscountPerUnit(), this.productDemandTrend,this.getProductConfiguration().getPricingOptions()));
    }

    public void overrideRecommendedPrice(double overriddenPriceOrPercent) {
        PriceBucket latestPriceBucket = this.getLatestActivePriceBucket();
        PriceBucket latestRecommendedPriceBucket = this.getLatestRecommendedPriceBucket();

        //Change price ONLY if difference between latest price and new price is more than 0.5 money? BUT WHAT ABOUT PERCENT DICOUNT.. NEEDS CORRECTION
        if (Math.abs(latestPriceBucket.getFixedOfferedPriceOrPercentDiscountPerUnit() - overriddenPriceOrPercent) > 0.5) {
            //PriceBucket newPriceBucket = createNewPriceBucket(productId, latestRecommendedPriceBucket.getLatestTaggedPriceVersion(), overriddenPriceOrPercent, latestRecommendedPriceBucket.getEntityStatus(), latestRecommendedPriceBucket.getFromDate());
            apply(new OfferedPriceChangedEvent(productId, latestRecommendedPriceBucket.getPriceBucketId(), latestRecommendedPriceBucket.getProductPricingCategory(), latestRecommendedPriceBucket.getLatestTaggedPriceVersion(), latestRecommendedPriceBucket.getNumberOfNewSubscriptions(), latestRecommendedPriceBucket.getNumberOfChurnedSubscriptions(), latestRecommendedPriceBucket.getNumberOfExistingSubscriptions(), latestRecommendedPriceBucket.getFromDate(), latestRecommendedPriceBucket.getToDate(), latestRecommendedPriceBucket.getEntityStatus(), latestRecommendedPriceBucket.getFixedOfferedPriceOrPercentDiscountPerUnit(), this.productDemandTrend,this.getProductConfiguration().getPricingOptions()));
        }
    }

    public void continueCurrentPrice() {
        apply(new CurrentPriceContinuedEvent(productId));
    }


    public void activateProduct() {
        apply(new ProductActivatedEvent(this.productId, this.productName, this.categoryId,
                this.subCategoryId, this.substitutes, this.complements,
                this.sensitiveTo, this.netQuantity, quantityUnit, this.productAccount.getProductPricingCategory()));
    }

    public PriceBucket findPriceBucketByPriceBucketId(final String priceBucketId) {
        return this.getProductAccount().findActivePriceBucketByPriceBucketId(priceBucketId);
    }

    public void donateToNodalAccount(double weight) {
        if (weight > 0) {
            final double offeredPriceOrPercent = getLatestActivePriceBucket().getFixedOfferedPriceOrPercentDiscountPerUnit();
            final double MRP = getLatestActivePriceBucket().getLatestTaggedPriceVersion().getMRP();
            final double latestPurchasePrice = getLatestPurchasePrice();
            final double fixedExpensePerUnit = this.getProductAccount().getLatestFixedExpenseVersion().getFixedOperatingExpPerUnit();
            final double variableExpensePerUnit = this.getProductAccount().getLatestVariableExpenseVersion().getVariableOperatingExpPerUnit();
            double excessProfit = 0;
            if (this.getProductAccount().getProductPricingCategory() == ProductPricingCategory.PRICE_COMMITMENT) {
                excessProfit = (weight * offeredPriceOrPercent) - ((weight * latestPurchasePrice) + weight * (fixedExpensePerUnit + variableExpensePerUnit));
                apply(new ExcessProfitDonatedToNodalAccountEvent(this.productId, excessProfit));
            } else if (this.getProductAccount().getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                double offeredPrice = MRP - (MRP * offeredPriceOrPercent);
                excessProfit = (weight * offeredPrice) - ((weight * latestPurchasePrice) + weight * (fixedExpensePerUnit + variableExpensePerUnit));
                apply(new ExcessProfitDonatedToNodalAccountEvent(this.productId, excessProfit));
            } else {
                excessProfit = (weight * offeredPriceOrPercent) - ((weight * latestPurchasePrice) + weight * (fixedExpensePerUnit + variableExpensePerUnit));
                apply(new ExcessProfitDonatedToNodalAccountEvent(this.productId, excessProfit));
            }

        } else if (weight < 0) {
            //LET'S THINK WHAT TO DO WHEN ACTUAL SUBSCRIPTIONS ARE LESSER THAT ANTICIPATED ONES.
        } else {
            //do nothing
        }
    }


}
