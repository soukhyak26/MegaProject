package com.calculate.price;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 12-04-2016.
 */
public class PriceInput {
    private LocalDate valueDate;
    private double purchasePrice;
    private double MRP;
    private double totalFixedExpensePerUnit;
    private double variableExpensePerUnit;
    private double breakEvenPrice;
    private double quantityForecasted;
    private double quantityActual;
    private double slope;
    private double offeredPrice;
    private double costActual;
    private double revenueActual;
    private double profitActual;
    private double weightedAverage;
    private double intercept;

    public boolean getInstanteneousSlope() {
        return instanteneousSlope;
    }

    public void setInstanteneousSlope(boolean instanteneousSlope) {
        this.instanteneousSlope = instanteneousSlope;
    }

    private boolean instanteneousSlope;

    public PriceInput(LocalDate valueDate, double purchasePrice, double MRP, double totalFixedExpensePerUnit, double variableExpensePerUnit, double offeredPrice, double quantityForecasted, double quantityActual) {
        this.valueDate=valueDate;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
        this.totalFixedExpensePerUnit = totalFixedExpensePerUnit;
        this.breakEvenPrice = this.purchasePrice + this.totalFixedExpensePerUnit + this.variableExpensePerUnit;
        this.variableExpensePerUnit = variableExpensePerUnit;
        this.quantityForecasted = quantityForecasted;
        this.quantityActual = quantityActual;
        this.offeredPrice = offeredPrice;
        this.instanteneousSlope=true;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public double getTotalFixedExpensePerUnit() {
        return totalFixedExpensePerUnit;
    }

    public void setTotalFixedExpensePerUnit(double totalFixedExpensePerUnit) {
        this.totalFixedExpensePerUnit = totalFixedExpensePerUnit;
    }

    public double getVariableExpensePerUnit() {
        return variableExpensePerUnit;
    }

    public void setVariableExpensePerUnit(double variableExpensePerUnit) {
        this.variableExpensePerUnit = variableExpensePerUnit;
    }

    public double getBreakEvenPrice() {
        return breakEvenPrice;
    }

    public void setBreakEvenPrice(double breakEvenPrice) {
        this.breakEvenPrice = breakEvenPrice;
    }

    public double getQuantityForecasted() {
        return quantityForecasted;
    }

    public void setQuantityForecasted(double quantityForecasted) {
        this.quantityForecasted = quantityForecasted;
    }

    public double getQuantityActual() {
        return quantityActual;
    }

    public void setQuantityActual(double quantityActual) {
        this.quantityActual = quantityActual;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public double getCostActual() {
        return costActual;
    }

    public void setCostActual(double costActual) {
        this.costActual = costActual;
    }

    public double getRevenueActual() {
        return revenueActual;
    }

    public void setRevenueActual(double revenueActual) {
        this.revenueActual = revenueActual;
    }

    public double getProfitActual() {
        return profitActual;
    }

    public void setProfitActual(double profitActual) {
        this.profitActual = profitActual;
    }

    public double getWeightedAverage() {
        return weightedAverage;
    }

    public void setWeightedAverage(double weightedAverage) {
        this.weightedAverage = weightedAverage;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public boolean isInstanteneousSlope() {
        return instanteneousSlope;
    }

    public double recalculateOfferPrice(){
        if(slope!= 0){
            return ( MRP+ slope*quantityActual);
        }else{
            return offeredPrice;
        }
    }

    public double recalculatePriceOnActuals () {
        if (slope != 0) {
            return MRP + (slope * quantityActual);
        } else {
            return offeredPrice;
        }
    }

    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }
    public double getIntercept(){
        return this.intercept;
    }
}
