package com.affaince.subscription.business.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDateTime;

/**
 * Created by anayonkar on 29/4/16.
 */
public class CreateProvisionCommand {
    @TargetAggregateIdentifier
    private Integer businessAccountId;
    private Double provisionForPurchaseCost;
    private Double provisionForLosses;
    private Double provisionForBenefits;
    private Double provisionForTaxes;
    private Double provisionForOthers;
    private Double provisionForCommonExpenses;
    private Double provisionForSubscriptionSpecificExpenses;
    private double defaultPercentFixedExpensePerUnitPrice;
    private double defaultPercentVariableExpensePerUnitPrice;
    private LocalDateTime provisionDate;

    public CreateProvisionCommand(Integer businessAccountId, Double provisionForPurchaseCost, Double provisionForLosses, Double provisionForBenefits, Double provisionForTaxes, Double provisionForOthers, Double provisionForCommonExpenses, Double provisionForSubscriptionSpecificExpenses, double defaultPercentFixedExpensePerUnitPrice, double defaultPercentVariableExpensePerUnitPrice, LocalDateTime provisionDate) {
        this.businessAccountId = businessAccountId;
        this.provisionForPurchaseCost = provisionForPurchaseCost;
        this.provisionForLosses = provisionForLosses;
        this.provisionForBenefits = provisionForBenefits;
        this.provisionForTaxes = provisionForTaxes;
        this.provisionForOthers = provisionForOthers;
        this.provisionForCommonExpenses = provisionForCommonExpenses;
        this.provisionForSubscriptionSpecificExpenses = provisionForSubscriptionSpecificExpenses;
        this.defaultPercentFixedExpensePerUnitPrice = defaultPercentFixedExpensePerUnitPrice;
        this.defaultPercentVariableExpensePerUnitPrice = defaultPercentVariableExpensePerUnitPrice;
        this.provisionDate = provisionDate;
    }

    public CreateProvisionCommand() {
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public Double getProvisionForPurchaseCost() {
        return provisionForPurchaseCost;
    }

    public Double getProvisionForLosses() {
        return provisionForLosses;
    }

    public Double getProvisionForBenefits() {
        return provisionForBenefits;
    }

    public Double getProvisionForTaxes() {
        return provisionForTaxes;
    }

    public Double getProvisionForOthers() {
        return provisionForOthers;
    }

    public Double getProvisionForCommonExpenses() {
        return provisionForCommonExpenses;
    }

    public Double getProvisionForSubscriptionSpecificExpenses() {
        return provisionForSubscriptionSpecificExpenses;
    }

    public double getDefaultPercentFixedExpensePerUnitPrice() {
        return defaultPercentFixedExpensePerUnitPrice;
    }

    public double getDefaultPercentVariableExpensePerUnitPrice() {
        return defaultPercentVariableExpensePerUnitPrice;
    }

    public LocalDateTime getProvisionDate() {
        return provisionDate;
    }
}
