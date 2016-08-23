package com.calculate.price;

import com.calculate.price.forecast.DemandForecasterChain;
import com.calculate.price.regression.FunctionCoefficients;
import com.calculate.price.regression.RegressionBasedDemandFunctionProcessor;

import java.util.*;

/**
 * Created by mandark on 12-04-2016.
 */
public class PrceTest2 {
    public static void main(String[] args) {
        try {
            calculatePriceSet1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*calculatePriceSet2();
        calculatePriceSet3();
        calculatePriceSet5();*/
    }

    public static double avg(double demand) {
        Random randomObj = new Random();
        double multiplier = randomObj.doubles(-0.3, 0.3).findFirst().getAsDouble();
        return Math.round(demand + demand * multiplier);
    }

    public static void calculatePriceSet1() throws Exception {
        PriceInput markPrice = new PriceInput(30.0, 54.0, 5.55, 2.0, 54, 0, 0);

        PriceInput pi2 = new PriceInput(30.0, 54.0, 5.55, 2.0, 47, 540, avg(540));
        pi2.setInstanteneousSlope(true);
        PriceInput pi3 = new PriceInput(30.0, 54.0, 2.54, 2.0, -1.0, 640, avg(640));
        pi3.setInstanteneousSlope(true);
        PriceInput pi4 = new PriceInput(30.0, 54.0, 1.60, 2.0, -1.0, 690, avg(690));
        pi4.setInstanteneousSlope(true);
        PriceInput pi5 = new PriceInput(30.0, 54.0, 1.23, 2.0, -1.0, 790, avg(790));
        pi5.setInstanteneousSlope(true);
        PriceInput pi6 = new PriceInput(30.0, 54.0, 0.83, 2.0, -1.0, 970, avg(970));
        pi6.setInstanteneousSlope(true);
        PriceInput pi7 = new PriceInput(30.0, 54.0, 0.67, 2.0, -1.0, 820, avg(820));
        pi7.setInstanteneousSlope(true);
        DoublyLinkedList<PriceInput> priceInputs = new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        priceInputs.add(pi7);

        List<Double> historicalSubscriptions = new ArrayList<>();
        DemandForecasterChain demandForecasterChain = new DemandForecasterChain();
        for (int i = 0; i < 200; i++) {
            priceInputs.forEach(pi -> historicalSubscriptions.add(pi.getQuantityActual()));
            double newforecastedSubscription = demandForecasterChain.forecast("1", historicalSubscriptions).get(0);
            PriceInput newforecastInput = new PriceInput(30.0, 54.0, 0.83, 2.0, -1.0, newforecastedSubscription, avg(newforecastedSubscription));
            newforecastInput.setInstanteneousSlope(true);
            priceInputs.add(newforecastInput);
        }
        DoublyLinkedList<PriceInput> outputs = PriceCalculator2.getInstance().calculatePrice(markPrice, priceInputs);

        Map<Double, Double> historicalPriceVsDemand = new HashMap<>();
        RegressionBasedDemandFunctionProcessor processor = new RegressionBasedDemandFunctionProcessor();
        FunctionCoefficients functionCoefficients = null;

        Iterator<PriceInput> iterator = priceInputs.iterator();
        while (iterator.hasNext()) {
            PriceInput input = iterator.next();
            historicalPriceVsDemand.put(input.getOfferedPrice(), input.getQuantityActual());
            if (historicalPriceVsDemand.size() > 20) {
                functionCoefficients = processor.processFunction(historicalPriceVsDemand);
                input.setInstanteneousSlope(false);
                input.setSlope(functionCoefficients.getSlope());
                input.setIntercept(functionCoefficients.getIntercept());
                System.out.printf("@@@@Slope:%f\n", functionCoefficients.getSlope());
            }

        }
        outputs = PriceCalculator2.getInstance().calculatePrice(markPrice, priceInputs);


        ListIterator<PriceInput> iterator2 = outputs.iterator();
        double accumulatedProfit = 0.0;
        while (iterator2.hasNext()) {
            PriceInput tempInput = iterator2.next();
            accumulatedProfit += tempInput.getProfitActual();
            System.out.printf("slope: %f", tempInput.getSlope());
            System.out.println("| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantityActual() + "| cost: " + tempInput.getCostActual() + "| Revenue: " + tempInput.getRevenueActual() + "| Profit: " + tempInput.getProfitActual());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }

/*
    public static void calculatePriceSet2() {
        PriceInput markPrice = new PriceInput(30.0, 54.0, 5.55, 2.0, 54, 0, 0);

        PriceInput pi2 = new PriceInput(30.0, 54.0, 5.55, 2.0, 45, 540, avg(540));
        PriceInput pi3 = new PriceInput(30.0, 54.0, 2.54, 2.0, -1.0, 640, avg(640));
        PriceInput pi4 = new PriceInput(30.0, 54.0, 1.60, 2.0, -1.0, 690, avg(690));
        PriceInput pi5 = new PriceInput(30.0, 54.0, 1.23, 2.0, -1.0, 790, avg(790));
        PriceInput pi6 = new PriceInput(30.0, 54.0, 0.83, 2.0, -1.0, 970, avg(970));
        PriceInput pi7 = new PriceInput(30.0, 54.0, 0.67, 2.0, -1.0, 820, avg(820));


        DoublyLinkedList<PriceInput> priceInputs = new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        priceInputs.add(pi7);
        DemandForecasterChain demandForecasterChain = new DemandForecasterChain();
        List<Double> historicalSubscriptions = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            priceInputs.forEach(pi -> historicalSubscriptions.add(pi.getQuantityActual()));
            double newforecastedSubscription = demandForecasterChain.forecast("1", historicalSubscriptions).get(0);
            PriceInput newofrecastInput = new PriceInput(30.0, 54.0, 0.83, 2.0, -1.0, newforecastedSubscription, avg(newforecastedSubscription));
            priceInputs.add(newofrecastInput);
        }
        DoublyLinkedList<PriceInput> outputs = PriceCalculator2.getInstance().calculatePrice(markPrice, priceInputs);

        ListIterator<PriceInput> iterator = outputs.iterator();
        double accumulatedProfit = 0.0;
        while (iterator.hasNext()) {
            PriceInput tempInput = iterator.next();
            accumulatedProfit += tempInput.getProfitActual();
            System.out.printf("slope: %f", tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantityActual() + "| cost: " + tempInput.getCostActual() + "| Revenue: " + tempInput.getRevenueActual() + "| Profit: " + tempInput.getProfitActual());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }

    public static void calculatePriceSet3() {
        PriceInput markPrice = new PriceInput(30.0, 54.0, 5.55, 2.0, 54, 0, 0);

        PriceInput pi2 = new PriceInput(30.0, 54.0, 5.55, 2.0, 50, 540, avg(540));
        PriceInput pi3 = new PriceInput(30.0, 54.0, 2.54, 2.0, -1.0, 640, avg(640));
        PriceInput pi4 = new PriceInput(30.0, 54.0, 1.60, 2.0, -1.0, 690, avg(690));
        PriceInput pi5 = new PriceInput(30.0, 54.0, 1.23, 2.0, -1.0, 790, avg(790));
        PriceInput pi6 = new PriceInput(30.0, 54.0, 0.83, 2.0, -1.0, 970, avg(970));
        PriceInput pi7 = new PriceInput(30.0, 54.0, 0.67, 2.0, -1.0, 820, avg(820));


        DoublyLinkedList<PriceInput> priceInputs = new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        priceInputs.add(pi7);
        DemandForecasterChain demandForecasterChain = new DemandForecasterChain();
        List<Double> historicalSubscriptions = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            priceInputs.forEach(pi -> historicalSubscriptions.add(pi.getQuantityActual()));
            double newforecastedSubscription = demandForecasterChain.forecast("1", historicalSubscriptions).get(0);
            PriceInput newofrecastInput = new PriceInput(30.0, 54.0, 0.83, 2.0, -1.0, newforecastedSubscription, avg(newforecastedSubscription));
            priceInputs.add(newofrecastInput);
        }
        DoublyLinkedList<PriceInput> outputs = PriceCalculator2.getInstance().calculatePrice(markPrice, priceInputs);

        ListIterator<PriceInput> iterator = outputs.iterator();
        double accumulatedProfit = 0.0;
        while (iterator.hasNext()) {
            PriceInput tempInput = iterator.next();
            accumulatedProfit += tempInput.getProfitActual();
            System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantityActual() + "| cost: " + tempInput.getCostActual() + "| Revenue: " + tempInput.getRevenueActual() + "| Profit: " + tempInput.getProfitActual());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }

    public static void calculatePriceSet4() {
        PriceInput markPrice = new PriceInput(30.0, 54.0, 5.55, 2.0, 54, 0, 0);

        PriceInput pi2 = new PriceInput(30.0, 54.0, 5.55, 2.0, 54, 540, avg(540));
        PriceInput pi3 = new PriceInput(30.0, 54.0, 2.54, 2.0, -1.0, 640, avg(640));
        PriceInput pi4 = new PriceInput(30.0, 54.0, 1.60, 2.0, -1.0, 690, avg(690));
        PriceInput pi5 = new PriceInput(30.0, 54.0, 1.23, 2.0, -1.0, 790, avg(790));
        PriceInput pi6 = new PriceInput(30.0, 54.0, 0.83, 2.0, -1.0, 970, avg(970));
        PriceInput pi7 = new PriceInput(30.0, 54.0, 0.67, 2.0, -1.0, 820, avg(820));


        DoublyLinkedList<PriceInput> priceInputs = new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        priceInputs.add(pi7);

        DemandForecasterChain demandForecasterChain = new DemandForecasterChain();
        List<Double> historicalSubscriptions = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            priceInputs.forEach(pi -> historicalSubscriptions.add(pi.getQuantityActual()));
            double newforecastedSubscription = demandForecasterChain.forecast("1", historicalSubscriptions).get(0);
            PriceInput newofrecastInput = new PriceInput(30.0, 54.0, 0.83, 2.0, -1.0, newforecastedSubscription, avg(newforecastedSubscription));
            priceInputs.add(newofrecastInput);
        }
        DoublyLinkedList<PriceInput> outputs = PriceCalculator2.getInstance().calculatePrice(markPrice, priceInputs);

        ListIterator<PriceInput> iterator = outputs.iterator();
        double accumulatedProfit = 0.0;
        while (iterator.hasNext()) {
            PriceInput tempInput = iterator.next();
            accumulatedProfit += tempInput.getProfitActual();
            System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantityActual() + "| cost: " + tempInput.getCostActual() + "| Revenue: " + tempInput.getRevenueActual() + "| Profit: " + tempInput.getProfitActual());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }

    public static void calculatePriceSet5() {
        PriceInput markPrice = new PriceInput(30.0, 54.0, 5.55, 2.0, 54, 0, 0);

        PriceInput pi2 = new PriceInput(30.0, 54, 5.55, 2.0, 50, 60, avg(60));
        PriceInput pi3 = new PriceInput(30.0, 54.0, 2.54, 2.0, -1.0, 63, avg(63));
        PriceInput pi4 = new PriceInput(30.0, 54.0, 1.60, 2.0, -1.0, 72, avg(72));
        PriceInput pi5 = new PriceInput(30.0, 54.0, 1.23, 2.0, -1.0, 86, avg(86));
        PriceInput pi6 = new PriceInput(30.0, 54.0, 0.83, 2.0, -1.0, 98, avg(98));


        DoublyLinkedList<PriceInput> priceInputs = new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        DemandForecasterChain demandForecasterChain = new DemandForecasterChain();
        List<Double> historicalSubscriptions = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            priceInputs.forEach(pi -> historicalSubscriptions.add(pi.getQuantityActual()));
            double newforecastedSubscription = demandForecasterChain.forecast("1", historicalSubscriptions).get(0);
            PriceInput newofrecastInput = new PriceInput(30.0, 54.0, 0.83, 2.0, -1.0, newforecastedSubscription, avg(newforecastedSubscription));
            priceInputs.add(newofrecastInput);
        }
        DoublyLinkedList<PriceInput> outputs = PriceCalculator2.getInstance().calculatePrice(markPrice, priceInputs);

        ListIterator<PriceInput> iterator = outputs.iterator();
        double accumulatedProfit = 0.0;
        while (iterator.hasNext()) {
            PriceInput tempInput = iterator.next();
            accumulatedProfit += tempInput.getProfitActual();
            System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantityActual() + "| cost: " + tempInput.getCostActual() + "| Revenue: " + tempInput.getRevenueActual() + "| Profit: " + tempInput.getProfitActual());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }
*/

}
