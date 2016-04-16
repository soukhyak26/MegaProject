package com.calculate.price;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mandark on 12-04-2016.
 */
//WILPDM31305
public class PriceCalculator {
    private static PriceCalculator instance = new PriceCalculator();

    private PriceCalculator() {
    }

    public static PriceCalculator getInstance() {
        return instance;
    }

    public DoublyLinkedList<PriceInput> calculatePrice(PriceInput markPrice, DoublyLinkedList<PriceInput> priceInputs) {
        PriceInput minusOneInput=null;
        PriceInput minusTwoInput=null;
        ListIterator<PriceInput> iterator = priceInputs.iterator();
        int previousCount=0;
        List<PriceInput> traversedInputs= new ArrayList<PriceInput>();

        while (iterator.hasNext()) {
            PriceInput tempInput= iterator.next();
            traversedInputs.add(tempInput);
            if(iterator.hasPrevious()){
                minusOneInput=iterator.previous();
                if(null!= minusOneInput){
                    previousCount++;
                }
            }
            if(iterator.hasPrevious()){
                minusTwoInput=iterator.previous();
                if(null!=minusTwoInput){
                    previousCount++;
                }
            }
            if(null == minusOneInput && null== minusTwoInput && tempInput.getOfferedPrice()< markPrice.getOfferedPrice()){
                double revenue=calculateRevenue(tempInput.getOfferedPrice(), tempInput.getQuantity());
                double cost=calculateCost(tempInput.getBreakEvenPrice(), tempInput.getQuantity());
                double profit=calculateProfit(revenue, cost);
                tempInput.setCost(cost);
                tempInput.setProfit(profit);
                tempInput.setRevenue(revenue);
                tempInput.setWeightedAverage(tempInput.getOfferedPrice());
            }else if(null !=minusOneInput && null != minusTwoInput && minusOneInput.getProfit()> minusTwoInput.getProfit()
                    && minusOneInput.getQuantity() < minusTwoInput.getQuantity()) {
                double y2=minusOneInput.getOfferedPrice();
                double y1=minusTwoInput.getOfferedPrice();
                double x2=minusOneInput.getQuantity();
                double x1=minusTwoInput.getQuantity();
                double slope=calculateSlope(y2,y1,x2,x1);
                double intercept=minusOneInput.getMRP();
                double offeredPrice=calculateOfferedPrice(intercept,slope,tempInput.getQuantity());
                tempInput.setOfferedPrice(offeredPrice);
                double revenue=calculateRevenue(tempInput.getOfferedPrice(),tempInput.getQuantity());
                double cost=calculateCost(tempInput.getBreakEvenPrice(),tempInput.getQuantity());
                double profit=calculateProfit(revenue,cost);
                tempInput.setSlope(slope);
                tempInput.setCost(cost);
                tempInput.setProfit(profit);
                tempInput.setRevenue(revenue);
                tempInput.setWeightedAverage(calculateWeightedAverage(traversedInputs));
            }else if(null !=minusOneInput && null != minusTwoInput && minusOneInput.getProfit()< minusTwoInput.getProfit()){
                if(minusOneInput.getQuantity() < minusTwoInput.getQuantity()){
                    double y2=minusOneInput.getOfferedPrice();
                    double y1=minusTwoInput.getOfferedPrice();
                    double x2=minusOneInput.getQuantity();
                    double x1=minusTwoInput.getQuantity();
                    double slope=calculateSlope(y2,y1,x2,x1);
                    double intercept=minusOneInput.getMRP();
                    double offeredPrice=calculateOfferedPrice(intercept,slope,tempInput.getQuantity());
                    tempInput.setOfferedPrice(offeredPrice);
                    double revenue=calculateRevenue(tempInput.getOfferedPrice(),tempInput.getQuantity());
                    double cost=calculateCost(tempInput.getBreakEvenPrice(),tempInput.getQuantity());
                    double profit=calculateProfit(revenue,cost);
                    tempInput.setSlope(slope);
                    tempInput.setCost(cost);
                    tempInput.setProfit(profit);
                    tempInput.setRevenue(revenue);
                    tempInput.setWeightedAverage(calculateWeightedAverage(traversedInputs));
                }else if(minusOneInput.getQuantity()> minusTwoInput.getQuantity()){
                    double slope=minusOneInput.getSlope() - (minusOneInput.getSlope()*minusOneInput.getWeightedAverage()/100);
                    double intercept = minusOneInput.getMRP ();
                    double offeredPrice = calculateOfferedPrice(intercept, slope, tempInput.getQuantity());
                    tempInput.setOfferedPrice(offeredPrice);
                    double revenue = calculateRevenue(tempInput.getOfferedPrice(), tempInput.getQuantity());
                    double cost = calculateCost(tempInput.getBreakEvenPrice(), tempInput.getQuantity());
                    double profit = calculateProfit(revenue, cost);

                    tempInput.setSlope(slope);
                    tempInput.setCost(cost);
                    tempInput.setProfit(profit);
                    tempInput.setRevenue(revenue);
                    tempInput.setWeightedAverage (calculateWeightedAverage(traversedInputs));
                }
            } else if (null !=minusOneInput && null != minusTwoInput && minusOneInput.getOfferedPrice() > minusTwoInput.getOfferedPrice() && minusOneInput.getQuantity()> minusTwoInput.getQuantity()) {
                double y2 = minusOneInput.getOfferedPrice();
                double y1 = markPrice.getOfferedPrice();
                double x2 = minusOneInput.getQuantity();
                double x1 = markPrice.getQuantity();

                //double slope=minusOneInput.getSlope() - (minusOneInput.getSlope()*minusOneInput.getWeightedAverage()/100);
                double slope= calculateSlope(y2,y1,x2,x1);
                double intercept = minusOneInput.getMRP ();
                double offeredPrice = calculateOfferedPrice(intercept, slope, tempInput.getQuantity());
                double revenue = calculateRevenue(tempInput.getOfferedPrice(), tempInput.getQuantity());
                double cost = calculateCost(tempInput.getBreakEvenPrice(), tempInput.getQuantity());
                double profit = calculateProfit(revenue, cost);

                tempInput.setOfferedPrice(offeredPrice);
                tempInput.setSlope(slope);
                tempInput.setCost(cost);
                tempInput.setProfit(profit);
                tempInput.setRevenue(revenue);
                tempInput.setWeightedAverage (calculateWeightedAverage(traversedInputs));
            }else {
                double y2 = minusOneInput.getOfferedPrice();
                double y1 = markPrice.getOfferedPrice();
                double x2 = minusOneInput.getQuantity();
                double x1 =markPrice.getQuantity();

                double slope=calculateSlope(y2,y1,x2,x1);
                double intercept = minusOneInput.getMRP ();
                double offeredPrice = calculateOfferedPrice(intercept, slope, tempInput.getQuantity());
                tempInput.setOfferedPrice(offeredPrice);
                double revenue = calculateRevenue(tempInput.getOfferedPrice(), tempInput.getQuantity());
                double cost = calculateCost(tempInput.getBreakEvenPrice(), tempInput.getQuantity());
                double profit = calculateProfit(revenue, cost);

                tempInput.setSlope(slope);
                tempInput.setCost(cost);
                tempInput.setProfit(profit);
                tempInput.setRevenue(revenue);
                tempInput.setWeightedAverage (calculateWeightedAverage(traversedInputs));

            }
            for(int i=previousCount;i>0;i--){
                iterator.next();
            }
            previousCount=0;
        }
        return priceInputs;
    }

    private double calculateSlope(double y2, double y1, double x2, double x1) {
        return (y2 - y1) / (x2 - x1);
    }

    private double calculateRevenue(double offeredPrice, double quantity) {
        return offeredPrice * quantity;
    }
//aaa
    private double calculateCost(double breakEvenPrice, double quantity) {
        return breakEvenPrice * quantity;
    }

    private double calculateProfit(double revenue, double cost) {
        return revenue - cost;
    }
    private double calculateWeightedAverage(List<PriceInput> traversedInputs){
        double weightedProduct=0.0;
        double quantitySum=0.0;
        for( PriceInput tempInput: traversedInputs){
            weightedProduct += tempInput.getOfferedPrice()*tempInput.getQuantity();
            quantitySum += tempInput.getQuantity();
        }
        return weightedProduct/quantitySum;
    }
    private double calculateOfferedPrice(double intercept,double slope,double quantity){
        return intercept +(slope*quantity);
    }
}
