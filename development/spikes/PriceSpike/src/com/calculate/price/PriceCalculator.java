package com.calculate.price;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mandark on 12-04-2016.
 */
public class PriceCalculator {
    private static PriceCalculator instance = new PriceCalculator();

    private PriceCalculator() {
    }

    public static PriceCalculator getInstance() {
        return instance;
    }

    public DoublyLinkedList<PriceInput> calculatePrice(DoublyLinkedList<PriceInput> priceInputs) {
        for (PriceInput tempInput : priceInputs) {
            if (tempInput.getOfferedPrice() > -1.0) {
                double revenue = calculateRevenue(tempInput.getOfferedPrice(), tempInput.getQuantity());
                double cost = calculateCost(tempInput.getBreakEvenPrice(), tempInput.getQuantity());
                double profit=calculateProfit(revenue,cost);
                tempInput.setCost(cost);
                tempInput.setProfit(profit);
                tempInput.setRevenue(revenue);
            }else{

            }
        }
        return null;
    }

    private double calculateSlope(double y2, double y1, double x2, double x1) {
        return (y2 - y1) / (x2 - x1);
    }

    private double calculateRevenue(double offeredPrice, double quantity) {
        return offeredPrice * quantity;
    }

    private double calculateCost(double breakEvenPrice, double quantity) {
        return breakEvenPrice * quantity;
    }

    private double calculateProfit(double revenue, double cost) {
        return revenue - cost;
    }
}
