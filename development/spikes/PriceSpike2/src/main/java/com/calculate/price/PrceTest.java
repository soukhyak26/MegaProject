package com.calculate.price;


import com.calculate.price.forecast.AggregationType;
import com.calculate.price.forecast.DataFrameVO;
import com.calculate.price.forecast.DemandForecasterChain;
import org.joda.time.LocalDate;

import java.util.*;

/**
 * Created by mandark on 12-04-2016.
 */
public class PrceTest {
    public static void main(String[] args){
        calculatePriceSet1();
/*
        calculatePriceSet2();
        calculatePriceSet3();
        calculatePriceSet5();
*/
    }

    public  static double avg(double demand){
        Random randomObj = new Random();
        double multiplier = randomObj.doubles(-0.2, 0.2).findFirst().getAsDouble();
        return Math.round(demand + demand*multiplier);
    }
    private static List<DataFrameVO> convertPriceInputsToDataFrameVos(DoublyLinkedList<PriceInput> inputs){
        List<DataFrameVO> vos = new ArrayList<>();
        Iterator<PriceInput>  priceInputIterator = inputs.iterator();
        while(priceInputIterator.hasNext()){
            PriceInput input = priceInputIterator.next();
            DataFrameVO vo = new DataFrameVO(input.getValueDate(),"demand", input.getQuantityActual(), AggregationType.INCREMENTAL);
            vos.add(vo);
        }
        return vos;
    }

    private static List<PriceInput> convertDataFrameVosToPriceInputs(PriceInput samplePriceInput, List<DataFrameVO> outputs){
        List<PriceInput> vos = new ArrayList<>();
        for(DataFrameVO output: outputs){
            PriceInput vo = new PriceInput(output.getDate(), samplePriceInput.getPurchasePrice(), samplePriceInput.getMRP(), samplePriceInput.getTotalFixedExpensePerUnit(), samplePriceInput.getVariableExpensePerUnit(), samplePriceInput.getOfferedPrice(),output.getValue(), avg(output.getValue()) );
            vos.add(vo);
        }
        return vos;
    }

    private static PriceInput convertDataFrameVoToPriceInput(PriceInput samplePriceInput, DataFrameVO input){
        PriceInput vo = new PriceInput(input.getDate(), samplePriceInput.getPurchasePrice(), samplePriceInput.getMRP(), samplePriceInput.getTotalFixedExpensePerUnit(), samplePriceInput.getVariableExpensePerUnit(), samplePriceInput.getOfferedPrice(),input.getValue(), avg(input.getValue()) );
        return vo;
    }
    public static void calculatePriceSet1(){
        PriceInput markPrice= new PriceInput(LocalDate.parse("2015-1-1"),30.0,54.0,5.55,2.0,54,0,0);
        PriceInput pi2= new PriceInput(LocalDate.parse("2015-1-2"),30.0,54.0,5.55,2.0,45,540,avg(540));
        PriceInput pi3= new PriceInput(LocalDate.parse("2015-1-3"),30.0,54.0,2.54,2.0,-1.0,640,avg(640));
        PriceInput pi4= new PriceInput(LocalDate.parse("2015-1-4"),30.0,54.0,1.60,2.0,-1.0,690,avg(690));
        PriceInput pi5= new PriceInput(LocalDate.parse("2015-1-5"),30.0,54.0,1.23,2.0,-1.0,790,avg(790));
        PriceInput pi6= new PriceInput(LocalDate.parse("2015-1-6"),30.0,54.0,0.83,2.0,-1.0,970,avg(970));
        PriceInput pi7= new PriceInput(LocalDate.parse("2015-1-7"),30.0,54.0,0.67,2.0,-1.0,820,avg(820));
        DoublyLinkedList<PriceInput> priceInputs= new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        priceInputs.add(pi7);
        DemandForecasterChain demandForecasterChain= new DemandForecasterChain();
        List<DataFrameVO> historicalSubscriptions= new ArrayList<>();
        for(int i=0;i<50;i++){
            //priceInputs.forEach(pi-> historicalSubscriptions.add(pi.getQuantityActual()));
            historicalSubscriptions = convertPriceInputsToDataFrameVos(priceInputs);
            List<DataFrameVO> newForecasts = demandForecasterChain.forecast("1",historicalSubscriptions,1);
            //PriceInput newforecastInput = new PriceInput(30.0,54.0,0.83,2.0,-1.0,newforecastedSubscription,avg(newforecastedSubscription));
            priceInputs.addAll(convertDataFrameVosToPriceInputs(markPrice,newForecasts));
            //printInputs(priceInputs);
            System.out.println("****************************");
        }
        printInputs(priceInputs);


        DoublyLinkedList<PriceInput> outputs= PriceCalculator2.getInstance().calculatePrice(markPrice,priceInputs);

        ListIterator<PriceInput> iterator= outputs.iterator();
        double accumulatedProfit=0.0;
        while(iterator.hasNext() ){
            PriceInput tempOutput=iterator.next();
            accumulatedProfit +=tempOutput.getProfitActual();
            System.out.println("slope: " + tempOutput.getSlope() + "| offeredPrice: " + tempOutput.getOfferedPrice() + "| qunatity: " + tempOutput.getQuantityActual() + "| cost: " + tempOutput.getCostActual() + "| Revenue: " + tempOutput.getRevenueActual() + "| Profit: " + tempOutput.getProfitActual());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }
    public static void printInputs(DoublyLinkedList<PriceInput> priceInputs){
        Iterator<PriceInput> inputsIterator = priceInputs.iterator();
        System.out.println("******FORECASTED VALUES*******");
        while(inputsIterator.hasNext() ){
            PriceInput tempInput=inputsIterator.next();
            System.out.println("Date: " + tempInput.getValueDate() +  "slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantityActual() + "| cost: " + tempInput.getCostActual() + "| Revenue: " + tempInput.getRevenueActual() + "| Profit: " + tempInput.getProfitActual());
        }
        System.out.println("******END- FORECASTED VALUES*******");
    }
/*
    public static void calculatePriceSet2(){
        PriceInput markPrice= new PriceInput(30.0,54.0,5.55,2.0,54,0,0);
        PriceInput pi2= new PriceInput(30.0,54.0,5.55,2.0,45,540,avg(540));
        PriceInput pi3= new PriceInput(30.0,54.0,2.54,2.0,-1.0,640,avg(640));
        PriceInput pi4= new PriceInput(30.0,54.0,1.60,2.0,-1.0,690,avg(690));
        PriceInput pi5= new PriceInput(30.0,54.0,1.23,2.0,-1.0,790,avg(790));
        PriceInput pi6= new PriceInput(30.0,54.0,0.83,2.0,-1.0,970,avg(970));
        PriceInput pi7= new PriceInput(30.0,54.0,0.67,2.0,-1.0,820,avg(820));


        DoublyLinkedList<PriceInput> priceInputs= new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        priceInputs.add(pi7);
        DemandForecasterChain demandForecasterChain= new DemandForecasterChain();
        List<Double> historicalSubscriptions= new ArrayList<>();

        for(int i=0;i<50;i++){
            priceInputs.forEach(pi-> historicalSubscriptions.add(pi.getQuantityActual()));
            double newforecastedSubscription = demandForecasterChain.forecast("1",historicalSubscriptions).get(0);
            PriceInput newofrecastInput = new PriceInput(30.0,54.0,0.83,2.0,-1.0,newforecastedSubscription,avg(newforecastedSubscription));
            priceInputs.add(newofrecastInput);
        }
        DoublyLinkedList<PriceInput> outputs= PriceCalculator2.getInstance().calculatePrice(markPrice,priceInputs);

        ListIterator<PriceInput> iterator= outputs.iterator();
        double accumulatedProfit=0.0;
        while(iterator.hasNext() ){
            PriceInput tempInput=iterator.next();
            accumulatedProfit +=tempInput.getProfitActual();
            System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantityActual() + "| cost: " + tempInput.getCostActual() + "| Revenue: " + tempInput.getRevenueActual() + "| Profit: " + tempInput.getProfitActual());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }

    public static void calculatePriceSet3(){
        PriceInput markPrice= new PriceInput(30.0,54.0,5.55,2.0,54,0,0);

        PriceInput pi2= new PriceInput(30.0,54.0,5.55,2.0,50,540,avg(540));
        PriceInput pi3= new PriceInput(30.0,54.0,2.54,2.0,-1.0,640,avg(640));
        PriceInput pi4= new PriceInput(30.0,54.0,1.60,2.0,-1.0,690,avg(690));
        PriceInput pi5= new PriceInput(30.0,54.0,1.23,2.0,-1.0,790,avg(790));
        PriceInput pi6= new PriceInput(30.0,54.0,0.83,2.0,-1.0,970,avg(970));
        PriceInput pi7= new PriceInput(30.0,54.0,0.67,2.0,-1.0,820,avg(820));


        DoublyLinkedList<PriceInput> priceInputs= new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        priceInputs.add(pi7);
        DemandForecasterChain demandForecasterChain= new DemandForecasterChain();
        List<Double> historicalSubscriptions= new ArrayList<>();

        for(int i=0;i<50;i++){
            priceInputs.forEach(pi-> historicalSubscriptions.add(pi.getQuantityActual()));
            double newforecastedSubscription = demandForecasterChain.forecast("1",historicalSubscriptions).get(0);
            PriceInput newofrecastInput = new PriceInput(30.0,54.0,0.83,2.0,-1.0,newforecastedSubscription,avg(newforecastedSubscription));
            priceInputs.add(newofrecastInput);
        }
        DoublyLinkedList<PriceInput> outputs= PriceCalculator2.getInstance().calculatePrice(markPrice,priceInputs);

        ListIterator<PriceInput> iterator= outputs.iterator();
        double accumulatedProfit=0.0;
        while(iterator.hasNext() ){
            PriceInput tempInput=iterator.next();
            accumulatedProfit +=tempInput.getProfitActual();
            System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantityActual() + "| cost: " + tempInput.getCostActual() + "| Revenue: " + tempInput.getRevenueActual() + "| Profit: " + tempInput.getProfitActual());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }

    public static void calculatePriceSet4(){
        PriceInput markPrice= new PriceInput(30.0,54.0,5.55,2.0,54,0,0);

        PriceInput pi2= new PriceInput(30.0,54.0,5.55,2.0,54,540,avg(540));
        PriceInput pi3= new PriceInput(30.0,54.0,2.54,2.0,-1.0,640,avg(640));
        PriceInput pi4= new PriceInput(30.0,54.0,1.60,2.0,-1.0,690,avg(690));
        PriceInput pi5= new PriceInput(30.0,54.0,1.23,2.0,-1.0,790,avg(790));
        PriceInput pi6= new PriceInput(30.0,54.0,0.83,2.0,-1.0,970,avg(970));
        PriceInput pi7= new PriceInput(30.0,54.0,0.67,2.0,-1.0,820,avg(820));


        DoublyLinkedList<PriceInput> priceInputs= new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        priceInputs.add(pi7);

        DemandForecasterChain demandForecasterChain= new DemandForecasterChain();
        List<Double> historicalSubscriptions= new ArrayList<>();

        for(int i=0;i<50;i++){
            priceInputs.forEach(pi-> historicalSubscriptions.add(pi.getQuantityActual()));
            double newforecastedSubscription = demandForecasterChain.forecast("1",historicalSubscriptions).get(0);
            PriceInput newofrecastInput = new PriceInput(30.0,54.0,0.83,2.0,-1.0,newforecastedSubscription,avg(newforecastedSubscription));
            priceInputs.add(newofrecastInput);
        }
        DoublyLinkedList<PriceInput> outputs= PriceCalculator2.getInstance().calculatePrice(markPrice,priceInputs);

        ListIterator<PriceInput> iterator= outputs.iterator();
        double accumulatedProfit=0.0;
        while(iterator.hasNext() ){
            PriceInput tempInput=iterator.next();
            accumulatedProfit +=tempInput.getProfitActual();
            System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantityActual() + "| cost: " + tempInput.getCostActual() + "| Revenue: " + tempInput.getRevenueActual() + "| Profit: " + tempInput.getProfitActual());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }

    public static void calculatePriceSet5(){
        PriceInput markPrice= new PriceInput(30.0,54.0,5.55,2.0,54,0,0);

        PriceInput pi2= new PriceInput(30.0,54,5.55,2.0,50,60,avg(60));
        PriceInput pi3= new PriceInput(30.0,54.0,2.54,2.0,-1.0,63,avg(63));
        PriceInput pi4= new PriceInput(30.0,54.0,1.60,2.0,-1.0,72,avg(72));
        PriceInput pi5= new PriceInput(30.0,54.0,1.23,2.0,-1.0,86,avg(86));
        PriceInput pi6= new PriceInput(30.0,54.0,0.83,2.0,-1.0,98,avg(98));


        DoublyLinkedList<PriceInput> priceInputs= new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        DemandForecasterChain demandForecasterChain= new DemandForecasterChain();
        List<Double> historicalSubscriptions= new ArrayList<>();

        for(int i=0;i<50;i++){
            priceInputs.forEach(pi-> historicalSubscriptions.add(pi.getQuantityActual()));
            double newforecastedSubscription = demandForecasterChain.forecast("1",historicalSubscriptions).get(0);
            PriceInput newofrecastInput = new PriceInput(30.0,54.0,0.83,2.0,-1.0,newforecastedSubscription,avg(newforecastedSubscription));
            priceInputs.add(newofrecastInput);
        }
        DoublyLinkedList<PriceInput> outputs= PriceCalculator2.getInstance().calculatePrice(markPrice,priceInputs);

        ListIterator<PriceInput> iterator= outputs.iterator();
        double accumulatedProfit=0.0;
        while(iterator.hasNext() ){
            PriceInput tempInput=iterator.next();
            accumulatedProfit +=tempInput.getProfitActual();
            System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantityActual() + "| cost: " + tempInput.getCostActual() + "| Revenue: " + tempInput.getRevenueActual() + "| Profit: " + tempInput.getProfitActual());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }
*/

}
