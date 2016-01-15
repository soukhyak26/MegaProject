package com.affaince.subscription.product.registration.command.domain;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 02-01-2016.
 */
public class InstantaneousPerformanceTracker {
    private LocalDate fromDate;
    private LocalDate toDate;
    private double purchasePrice;
    private double MRP;
    private double expectedMerchantProfitPercentage;
    private double grossMargin;
    private double percentageGrossMargin;
    private double totalOperationalExpenses;
    private double operatingProfit;
    private double percentageOperatingProfit;
    private double offeredPrice;

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

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(double grossMargin) {
        this.grossMargin = grossMargin;
    }

    public double getPercentageGrossMargin() {
        return percentageGrossMargin;
    }

    public void setPercentageGrossMargin(double percentageGrossMargin) {
        this.percentageGrossMargin = percentageGrossMargin;
    }

    public double getTotalOperationalExpenses() {
        return totalOperationalExpenses;
    }

    public void setTotalOperationalExpenses(double totalOperationalExpenses) {
        this.totalOperationalExpenses = totalOperationalExpenses;
    }

    public double getOperatingProfit() {
        return operatingProfit;
    }

    public void setPeratingProfit(double operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public double getPercentageOperatingProfit() {
        return percentageOperatingProfit;
    }

    public void setPercentageOperatingProfit(double percentageOperatingProfit) {
        this.percentageOperatingProfit = percentageOperatingProfit;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public void setOperatingProfit(double operatingProfit) {
        this.operatingProfit = operatingProfit;
    }

    public double getExpectedMerchantProfitPercentage() {
        return expectedMerchantProfitPercentage;
    }

    public void setExpectedMerchantProfitPercentage(double expectedMerchantProfitPercentage) {
        this.expectedMerchantProfitPercentage = expectedMerchantProfitPercentage;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }
}
