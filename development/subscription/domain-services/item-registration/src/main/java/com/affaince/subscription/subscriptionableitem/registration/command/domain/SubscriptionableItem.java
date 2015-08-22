package com.affaince.subscription.subscriptionableitem.registration.command.domain;

import com.affaince.subscription.subscriptionableitem.registration.command.AddProjectionParametersCommand;
import com.affaince.subscription.subscriptionableitem.registration.command.AddSubscriptionableItemRuleParametersCommand;
import com.affaince.subscription.subscriptionableitem.registration.command.UpdatePriceAndStockParametersCommand;
import com.affaince.subscription.subscriptionableitem.registration.command.event.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class SubscriptionableItem extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String itemId;
    private String batchId;
    private String categoryId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryNmae;
    private String productId;
    private double currentPurchasePricePerUnit;
    private double currentMRP;
    private double currentOfferedPrice;
    private int currentStockInUnits;
    private LocalDate currentPriceDate;
    private ProjectionParameters projectionParameters;
    private RuleParameters ruleParameters;

    public SubscriptionableItem() {

    }

    public SubscriptionableItem(String itemId, String batchId, String categoryId, String categoryName, String subCategoryId, String subCategoryNmae, String productId, double currentPurchasePricePerUnit, double currentMRP, double currentOfferedPrice, int currentStockInUnits, LocalDate currentPriceDate) {
        apply(new CreateSubscriptionableItemEvent(itemId, batchId, categoryId, categoryName, subCategoryId, subCategoryNmae, productId, currentPurchasePricePerUnit, currentMRP, currentOfferedPrice, currentStockInUnits, currentPriceDate));
    }

    @EventSourcingHandler
    public void on(CreateSubscriptionableItemEvent event) {
        this.itemId = event.getItemId();
        this.batchId = event.getBatchId();
        this.categoryId = event.getCategoryId();
        this.categoryName = event.getCategoryName();
        this.subCategoryId = event.getSubCategoryId();
        this.subCategoryNmae = event.getSubCategoryNmae();
        this.productId = event.getProductId();
        this.currentMRP = event.getCurrentMRP();
        this.currentOfferedPrice = currentOfferedPrice;
        this.currentPriceDate = event.getCurrentPriceDate();
        this.currentStockInUnits = currentStockInUnits;
    }

    @EventSourcingHandler
    public void on(UpdatePriceAndStockParametersEvent event) {
        this.itemId = event.getItemId();
        this.currentMRP = event.getCurrentMRP();
        this.currentPriceDate = event.getCurrentPrizeDate();
        this.currentStockInUnits = currentStockInUnits;
    }

    @EventSourcingHandler
    public void on(AddProjectionParametersEvent event) {
        this.itemId = event.getItemId();
        ProjectionParameters projectionParameters = new ProjectionParameters(
                event.getTargetConsumptionPeriod(),
                event.getTargetConsumptionPeriodUnit(),
                event.getTargetSalePerConsumptionPeriod(),
                event.getMinimumProfitMargin(),
                event.getMaximumProfitMargin(),
                event.getDemandToSupplyRatio(),
                event.getConsumptionFrequency()
        );
        this.projectionParameters = projectionParameters;
    }

    @EventSourcingHandler
    public void on(AddSubscriptionableItemRuleParametersEvent event) {
        this.itemId = itemId;
        RuleParameters ruleParameters = new RuleParameters(
                event.getMinPermissibleDiscount(),
                event.getMaxPermissibleDiscount(),
                event.getMaxPermissibleUnits(),
                event.getMaxPermissibleSubscriptionPeriod(),
                event.getMaxPermissibleSubscriptionPeriodUnit()
        );
        this.ruleParameters = ruleParameters;
    }

    @EventSourcingHandler
    public void on(CurrentOfferedPriceAddedEvent event) {
        this.itemId = event.getItemId();
        this.currentOfferedPrice = event.getCurrentOfferedPrice();
    }

    public void updatePriceAndStockParemeters(UpdatePriceAndStockParametersCommand command) {
        apply(new UpdatePriceAndStockParametersEvent(this.itemId, command.getCurrentMRP(), command.getCurrentStockInUnits(), command.getCurrentPrizeDate()));
    }

    public void addProjectionParameters(AddProjectionParametersCommand command) {
        apply(new AddProjectionParametersEvent(
                this.itemId,
                command.getTargetConsumptionPeriod(),
                command.getTargetConsumptionPeriodUnit(),
                command.getTargetSalePerConsumptionPeriod(),
                command.getMinimumProfitMargin(),
                command.getMaximumProfitMargin(),
                command.getDemandToSupplyRatio(),
                command.getConsumptionFrequency()
        ));
    }

    public void addSubscriptionableItemRuleParameters(AddSubscriptionableItemRuleParametersCommand command) {
        apply(new AddSubscriptionableItemRuleParametersEvent(
                this.itemId,
                command.getMinPermissibleDiscount(),
                command.getMaxPermissibleDiscount(),
                command.getMaxPermissibleUnits(),
                command.getMaxPermissibleSubscriptionPeriod(),
                command.getMaxPermissibleSubscriptionPeriodUnit()
        ));
    }

    public void addCurrentOfferedPrice(double currentOfferedPrice) {
        apply(new CurrentOfferedPriceAddedEvent(this.itemId, currentOfferedPrice));
    }
}
