package com.calculate.price;

import java.util.List;

/**
 * Created by mandark on 12-04-2016.
 */
public class PriceInput {
    private double purchasePrice;
    private double totalFixedExpensePerUnit;
    private double variableExpensePerUnit;
    private double breakEvenPrice;
    private double quantity;
    private double slope;
    private double offeredPrice;
    private double cost;
    private double revenue;
    private double profit;


    public PriceInput(double purchasePrice, double totalFixedExpensePerUnit, double variableExpensePerUnit,double offeredPrice, double quantity) {
        this.purchasePrice = purchasePrice;
        this.totalFixedExpensePerUnit = totalFixedExpensePerUnit;
        this.variableExpensePerUnit = variableExpensePerUnit;
        this.breakEvenPrice=this.purchasePrice+this.totalFixedExpensePerUnit+this.variableExpensePerUnit;
        this.quantity = quantity;
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

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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
}
