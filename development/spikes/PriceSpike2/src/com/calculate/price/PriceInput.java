package com.calculate.price;

import java.util.List;

/**
 * Created by mandark on 12-04-2016.
 */
public class PriceInput {
    private double purchasePrice;
    private double MRP;
    private double totalFixedExpensePerUnit;
    private double variableExpensePerUnit;
    private double breakEvenPrice;
    private double quantityForecasted;
    private double quantityActual;
    private double slope;
    private double offeredPrice;
    private double cost;
    private double revenue;
    private double profit;
    private double weightedAverage;

    public PriceInput(double purchasePrice, double MRP,double totalFixedExpensePerUnit, double variableExpensePerUnit,double offeredPrice, double quantityForecasted,double quantityActual) {
        this.purchasePrice = purchasePrice;
        this.MRP=MRP;
        this.totalFixedExpensePerUnit = totalFixedExpensePerUnit;
        this.variableExpensePerUnit = variableExpensePerUnit;
        this.breakEvenPrice=this.purchasePrice+this.totalFixedExpensePerUnit+this.variableExpensePerUnit;
        this.quantityForecasted = quantityForecasted;
        this.quantityActual=quantityActual;
        this.offeredPrice=offeredPrice;
    }

    public double getPurchasePrice() {
        return this.purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getTotalFixedExpensePerUnit() {
        return this.totalFixedExpensePerUnit;
    }

    public void setTotalFixedExpensePerUnit(double totalFixedExpensePerUnit) {
        this.totalFixedExpensePerUnit = totalFixedExpensePerUnit;
    }

    public double getQuantityForecasted() {
        return this.quantityForecasted;
    }

    public void setQuantityForecasted(double quantity) {
        this.quantityForecasted = quantity;
    }

    public double getVariableExpensePerUnit() {
        return this.variableExpensePerUnit;
    }

    public void setVariableExpensePerUnit(double variableExpensePerUnit) {
        this.variableExpensePerUnit = variableExpensePerUnit;
    }

    public double getBreakEvenPrice() {
        return this.breakEvenPrice;
    }

    public void setBreakEvenPrice(double breakEvenPrice) {
        this.breakEvenPrice = breakEvenPrice;
    }

    public double getSlope() {
        return this.slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getOfferedPrice() {
        return this.offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getProfit() {
        return this.profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getMRP() {
        return this.MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public double getWeightedAverage() {
        return this.weightedAverage;
    }

    public void setWeightedAverage(double weightedAverage) {
        this.weightedAverage = weightedAverage;
    }

    public double getQuantityActual() {
        return quantityActual;
    }

    public void setQuantityActual(double quantityActual) {
        this.quantityActual = quantityActual;
    }

    public double recalculateOfferPrice(){
        if(slope!= 0){
            return ( MRP+ slope*quantityActual);
        }else{
            return offeredPrice;
        }
    }
}
