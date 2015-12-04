package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.*;
import com.affaince.subscription.product.registration.command.AddProjectionParametersCommand;
import com.affaince.subscription.product.registration.command.AddProductRuleParametersCommand;
import com.affaince.subscription.product.registration.command.UpdatePriceAndStockParametersCommand;
import com.affaince.subscription.product.registration.command.event.*;
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
    private String shoppingSiteProductId;
    private double currentPurchasePricePerUnit;
    private double currentMRP;
    private double currentOfferedPrice;
    private int currentStockInUnits;
    private LocalDate currentPriceDate;
    private ProjectionParameters projectionParameters;
    private RuleParameters ruleParameters;
    private ProductAccount productAccount = new ProductAccount();

    public Product() {

    }

    public Product(String productId, String batchId, String categoryId, String categoryName, String subCategoryId, String subCategoryName, String shoppingSiteProductId, double currentPurchasePricePerUnit, double currentMRP, double currentOfferedPrice, int currentStockInUnits, LocalDate currentPriceDate) {
        apply(new CreateProductEvent(productId, batchId, categoryId, categoryName, subCategoryId, subCategoryName, shoppingSiteProductId, currentPurchasePricePerUnit, currentMRP, currentOfferedPrice, currentStockInUnits, currentPriceDate));
    }

    @EventSourcingHandler
    public void on(CreateProductEvent event) {
        this.productId = event.getItemId();
        this.batchId = event.getBatchId();
        this.categoryId = event.getCategoryId();
        this.categoryName = event.getCategoryName();
        this.subCategoryId = event.getSubCategoryId();
        this.subCategoryName = event.getSubCategoryName();
        this.shoppingSiteProductId = event.getProductId();
        this.currentMRP = event.getCurrentMRP();
        this.currentOfferedPrice = event.getCurrentOfferedPrice();
        this.currentPriceDate = event.getCurrentPriceDate();
        this.currentStockInUnits = event.getCurrentStockInUnits();
    }

    @EventSourcingHandler
    public void on(UpdatePriceAndStockParametersEvent event) {
        this.productId = event.getItemId();
        this.currentMRP = event.getCurrentMRP();
        this.currentPriceDate = event.getCurrentPrizeDate();
        this.currentStockInUnits = event.getCurrentStockInUnits();
    }

    @EventSourcingHandler
    public void on(AddProjectionParametersEvent event) {
        this.productId = event.getItemId();
        ProjectionParameters projectionParameters = new ProjectionParameters(
                event.getTargetConsumptionPeriod(),
                event.getTargetSalePerConsumptionPeriod(),
                event.getMinimumProfitMargin(),
                event.getMaximumProfitMargin(),
                event.getDemandToSupplyRatio(),
                event.getConsumptionFrequency()
        );
        this.projectionParameters = projectionParameters;
    }

    @EventSourcingHandler
    public void on(AddProductRuleParametersEvent event) {
        RuleParameters ruleParameters = new RuleParameters(
                event.getMinPermissibleDiscount(),
                event.getMaxPermissibleDiscount(),
                event.getMaxPermissibleUnits(),
                event.getMaxPermissibleSubscriptionPeriod()
        );
        this.ruleParameters = ruleParameters;
    }

    @EventSourcingHandler
    public void on(CurrentOfferedPriceAddedEvent event) {
        this.productId = event.getItemId();
        this.currentOfferedPrice = event.getCurrentOfferedPrice();
    }

    public void updatePriceAndStockParemeters(UpdatePriceAndStockParametersCommand command) {
        apply(new UpdatePriceAndStockParametersEvent(this.productId, command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    public void addProjectionParameters(AddProjectionParametersCommand command) {
        final Period targetConsumptionPeriod = new Period(
                command.getTargetConsumptionPeriod(), PeriodUnit.valueOf(command.getTargetConsumptionPeriodUnit())
        );
        final Frequency consumptionFrequency = new Frequency(
                command.getConsumptionFrequency(),
                new Period(
                        command.getTargetConsumptionPeriod(),
                        PeriodUnit.valueOf(command.getConsumptionFrequencyPeriodUnitCode())
                )
        );
        apply(new AddProjectionParametersEvent(
                this.productId,
                targetConsumptionPeriod,
                command.getTargetSalePerConsumptionPeriod(),
                command.getMinimumProfitMargin(),
                command.getMaximumProfitMargin(),
                command.getDemandToSupplyRatio(),
                consumptionFrequency
        ));
    }

    public void addSubscriptionableItemRuleParameters(AddProductRuleParametersCommand command) {
        final Discount minDiscount = new Discount(
                command.getMinPermissibleDiscount(), DiscountUnit.valueOf(command.getDiscountUnitCode())
        );
        final Discount maxDiscount = new Discount(
                command.getMaxPermissibleDiscount(), DiscountUnit.valueOf(command.getDiscountUnitCode())
        );
        final Period maxSubscriptionPeriod = new Period(
                command.getMaxPermissibleSubscriptionPeriod(),
                PeriodUnit.valueOf(command.getMaxPermissibleSubscriptionPeriodUnitCode())
        );
        apply(new AddProductRuleParametersEvent(
                this.productId, minDiscount, maxDiscount, command.getMaxPermissibleUnits(), maxSubscriptionPeriod
        ));
    }

    public void addCurrentOfferedPrice(double currentOfferedPrice) {
        apply(new CurrentOfferedPriceAddedEvent(this.productId, currentOfferedPrice));
    }
}
