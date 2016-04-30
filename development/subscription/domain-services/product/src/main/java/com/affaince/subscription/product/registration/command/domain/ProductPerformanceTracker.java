package com.affaince.subscription.product.registration.command.domain;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 28-11-2015.
 */
public class ProductPerformanceTracker extends AbstractAnnotatedEntity{
    private LocalDate fromDate;
    private LocalDate toDate;
    private double expectedMerchantProfitPercentage;
    private double demandDensity;
    private double averageDemandPerSubscriber;
    private double quantityPerPeriod;
    private double grossMargin;
    private double percentageGrossMargin;
    private double fixedOperatingExpensePerUnit;
    private double variableOperatingExpensePerUnit;
    private double operatingProfit;
    private double percentageOperatingProfit;
    private double revenuePerPeriod;

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public double getDemandDensity() {
        return this.demandDensity;
    }

    public void setDemandDensity(double demandDensity) {
        this.demandDensity = demandDensity;
    }

    public double getAverageDemandPerSubscriber() {
        return this.averageDemandPerSubscriber;
    }

    public void setAverageDemandPerSubscriber(double averageDemandPerSubscriber) {
        this.averageDemandPerSubscriber = averageDemandPerSubscriber;
    }

    public double getGrossMargin() {
        return this.grossMargin;
    }

    public void setGrossMargin(double grossMargin) {
        this.grossMargin = grossMargin;
    }

    public double getPercentageGrossMargin() {
        return this.percentageGrossMargin;
    }

    public void setPercentageGrossMargin(double percentageGrossMargin) {
        this.percentageGrossMargin = percentageGrossMargin;
    }

    public double getOperatingProfit() {
        return this.operatingProfit;
    }

    public void setOperatingProfit(double operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public double getPercentageOperatingProfit() {
        return this.percentageOperatingProfit;
    }

    public void setPercentageOperatingProfit(double percentageOperatingProfit) {
        this.percentageOperatingProfit = percentageOperatingProfit;
    }

    public double getRevenuePerPeriod() {
        return this.revenuePerPeriod;
    }

    public void setRevenuePerPeriod(double revenuePerPeriod) {
        this.revenuePerPeriod = revenuePerPeriod;
    }

    public double getExpectedMerchantProfitPercentage() {
        return expectedMerchantProfitPercentage;
    }

    public void setExpectedMerchantProfitPercentage(double expectedMerchantProfitPercentage) {
        this.expectedMerchantProfitPercentage = expectedMerchantProfitPercentage;
    }

    public double getFixedOperatingExpensePerUnit() {
        return this.fixedOperatingExpensePerUnit;
    }

    public void setFixedOperatingExpensePerUnit(double fixedOperatingExpensePerUnit) {
        this.fixedOperatingExpensePerUnit = fixedOperatingExpensePerUnit;
    }

    public double getVariableOperatingExpensePerUnit() {
        return this.variableOperatingExpensePerUnit;
    }

    public void setVariableOperatingExpensePerUnit(double variableOperatingExpensePerUnit) {
        this.variableOperatingExpensePerUnit = variableOperatingExpensePerUnit;
    }

    public double getQuantityPerPeriod() {
        return this.quantityPerPeriod;
    }

    public void setQuantityPerPeriod(double quantityPerPeriod) {
        this.quantityPerPeriod = quantityPerPeriod;
    }
}
