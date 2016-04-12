package com.calculate.price;

/**
 * Created by mandark on 12-04-2016.
 */
public class PriceOutput {
    private double slope;
    private double breakevenPrice;
    private double offeredPrice;
    private double cost;
    private double revenue;
    private double profit;

    public double getSlope() {
        return this.slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getBreakevenPrice() {
        return this.breakevenPrice;
    }

    public void setBreakevenPrice(double breakevenPrice) {
        this.breakevenPrice = breakevenPrice;
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
