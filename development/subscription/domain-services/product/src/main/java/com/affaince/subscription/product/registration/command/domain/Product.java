package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.product.registration.command.AddForecastParametersCommand;
import com.affaince.subscription.product.registration.command.UpdatePriceAndStockParametersCommand;
import com.affaince.subscription.product.registration.command.event.*;
import com.affaince.subscription.product.registration.services.UtilityService;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class Product extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String productId;
    private String batchId;
    private String categoryId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryName;
    private double currentPurchasePricePerUnit;
    private double currentMRP;
    private double currentOfferedPrice;
    private int currentStockInUnits;
    private LocalDate currentPriceDate;
    private ProductAccount productAccount = new ProductAccount();

    public Product() {

    }

    public Product(String productId, String batchId, String categoryId, String categoryName, String subCategoryId, String subCategoryName, double currentPurchasePricePerUnit, double currentMRP, double currentOfferedPrice, int currentStockInUnits, LocalDate currentPriceDate) {
        apply(new CreateProductEvent(productId, batchId, categoryId, categoryName, subCategoryId, subCategoryName, currentPurchasePricePerUnit, currentMRP, currentOfferedPrice, currentStockInUnits, currentPriceDate));
    }

    @EventSourcingHandler
    public void on(CreateProductEvent event) {
        this.productId = event.getProductId();
        this.batchId = event.getBatchId();
        this.categoryId = event.getCategoryId();
        this.categoryName = event.getCategoryName();
        this.subCategoryId = event.getSubCategoryId();
        this.subCategoryName = event.getSubCategoryName();
        this.currentMRP = event.getCurrentMRP();
        this.currentOfferedPrice = event.getCurrentOfferedPrice();
        this.currentPriceDate = event.getCurrentPriceDate();
        this.currentStockInUnits = event.getCurrentStockInUnits();
        productAccount = new ProductAccount();
    }

    @EventSourcingHandler
    public void on(UpdatePriceAndStockParametersEvent event) {
        this.productId = event.getProductId();
        this.currentMRP = event.getCurrentMRP();
        this.currentPriceDate = event.getCurrentPrizeDate();
        this.currentStockInUnits = event.getCurrentStockInUnits();
    }

    @EventSourcingHandler
    public void on(ForecastParametersAddedEvent event) {
        this.productId = event.getProductId();
        String forecastDate = UtilityService.getFromToDateString(event.getFromDate(), event.getToDate());
        ProductPerformanceTracker productPerformanceTracker = productAccount.getForecast(forecastDate);
        if (productPerformanceTracker == null) {
            productPerformanceTracker = new ProductPerformanceTracker();
        }
        productPerformanceTracker = new ProductPerformanceTracker();
        productPerformanceTracker.setFromDate(event.getFromDate());
        productPerformanceTracker.setToDate(event.getToDate());
        productPerformanceTracker.addTimeWisePriceBuckets(event.getPriceBucket(),
                UtilityService.getFromToDateString(event.getPriceBucket().getFromDate(),
                        event.getPriceBucket().getToDate()));
        productAccount.addForecast(productPerformanceTracker, forecastDate);
    }

    @EventSourcingHandler
    public void on(CurrentOfferedPriceAddedEvent event) {
        this.productId = event.getProductId();
        this.currentOfferedPrice = event.getCurrentOfferedPrice();
    }

    public void updatePriceAndStockParemeters(UpdatePriceAndStockParametersCommand command) {
        apply(new UpdatePriceAndStockParametersEvent(this.productId, command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    public void addForecastParameters(AddForecastParametersCommand command) {
        PriceBucket priceBucket = new PriceBucket(
                command.getPurchasePricePerUnit(),
                command.getSalePricePerUnit(),
                command.getMRP(),
                command.getFromDate(),
                command.getToDate(),
                command.getNumberOfNewCustomersAssociatedWithAPrice(),
                command.getNumberOfChurnedCustomersAssociatedWithAPrice()
        );
        apply(new ForecastParametersAddedEvent(
                this.productId,
                command.getFromDate (),
                command.getToDate(),
                priceBucket
        ));
    }

    public void addCurrentOfferedPrice(double currentOfferedPrice) {
        apply(new CurrentOfferedPriceAddedEvent(this.productId, currentOfferedPrice));
    }
}
