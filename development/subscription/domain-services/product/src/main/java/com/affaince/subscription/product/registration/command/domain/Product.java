package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.product.registration.command.AddForecastParametersCommand;
import com.affaince.subscription.product.registration.command.UpdateProductStatusCommand;
import com.affaince.subscription.product.registration.command.event.*;
import com.affaince.subscription.product.registration.services.UtilityService;
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
    private double demandDensity;
    private double averageDemandPerYearPerSubscriber;
    private ProductAccount productAccount = new ProductAccount();

    public Product() {

    }

    public Product(String productId, String productName, String categoryId, String subCategoryId, long quantity, QuantityUnit quantityUnit) {
        apply(new ProductRegisteredEvent(productId, productName, categoryId, subCategoryId, quantity, quantityUnit));
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
        productAccount = new ProductAccount();
    }

    @EventSourcingHandler
    public void on(OfferedPriceUpdatedEvent event) {
        this.productId = event.getProductId();
        PriceBucket lastPriceBucket = this.getLatestActualPriceBucket();
        PriceBucket newPriceBucket = new PriceBucket(lastPriceBucket);
        lastPriceBucket.setToDate(LocalDate.now());
    }

    @EventSourcingHandler
    public void on(ForecastParametersAddedEvent event) {
        this.productId = event.getProductId();
        String forecastDate = UtilityService.getFromToDateString(event.getFromDate(), event.getToDate());

        ProductPerformanceTracker productPerformanceTracker = new ProductPerformanceTracker();
        productPerformanceTracker.setFromDate(event.getFromDate());
        productPerformanceTracker.setToDate(event.getToDate());
        productAccount.addNewForecastedPriceBucket(event.getFromDate(), event.getPriceBuckets());
        productAccount.addPerformanceTrackerToForecast(event.getFromDate(), productPerformanceTracker);
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

    public void updateProductStatus(UpdateProductStatusCommand command) {
        apply(new ProductStatusReceivedEvent(this.productId, command.getCurrentPurchasePrice(), command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    public void addForecastParameters(AddForecastParametersCommand command) {
        List<ForecastedPriceParameter> forecastedPriceParameters = command.getForecastedPriceParamters();

        Map<LocalDate, ProductPerformanceTracker> forecastPerUnitPeriod = getProductAccount().getForecastPerUnitPeriod();
        ProductPerformanceTracker productPerformanceTracker = new ProductPerformanceTracker();
        productPerformanceTracker.setFromDate(command.getFromDate());
        productPerformanceTracker.setToDate(command.getToDate());
        ProductAccount productAccount = getProductAccount();

        for (ForecastedPriceParameter priceParameter : forecastedPriceParameters) {
            PriceBucket priceBucket = new PriceBucket(
                    priceParameter.getPurchasePricePerUnit(),
                    priceParameter.getAverageOfferedPricePerUnit(),
                    priceParameter.getMRP(),
                    command.getFromDate(),
                    command.getToDate(),
                    priceParameter.getNumberOfNewCustomersAssociatedWithAPrice(),
                    priceParameter.getNumberOfChurnedCustomersAssociatedWithAPrice()
            );
            productAccount.addNewForecastedPriceBucket(command.getFromDate(), priceBucket);
            apply(new ForecastParametersAddedEvent(
                    this.productId,
                    command.getFromDate(),
                    command.getToDate(),
                    priceBucket
            ));

        }
        productAccount.addPerformanceTrackerToForecast(command.getToDate(), productPerformanceTracker);
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

    public double getLatestOperatingExpensesPerUnit() {
        return getProductAccount().getLatestActuals().getTotalOperationalExpenses();
    }
}
