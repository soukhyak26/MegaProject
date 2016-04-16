package com.calculate.price;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mandark on 12-04-2016.
 */
public class PrceTest {
    public static void main(String[] args){
        PriceInput markPrice= new PriceInput(30.0,54.0,5.55,2.0,54,0);

        PriceInput pi2= new PriceInput(30.0,54.0,5.55,2.0,45,540);
        PriceInput pi3= new PriceInput(30.0,54.0,2.54,2.0,-1.0,640);
        PriceInput pi4= new PriceInput(30.0,54.0,1.60,2.0,-1.0,690);
        PriceInput pi5= new PriceInput(30.0,54.0,1.23,2.0,-1.0,790);
        PriceInput pi6= new PriceInput(30.0,54.0,0.83,2.0,-1.0,970);
        PriceInput pi7= new PriceInput(30.0,54.0,0.67,2.0,-1.0,820);
        PriceInput pi8= new PriceInput(30.0,54.0,0.53,2.0,-1.0,1250);
        PriceInput pi9= new PriceInput(30.0,54.0,0.42,2.0,-1.0,1450);
        PriceInput pi10= new PriceInput(30.0,54.0,0.35,2.0,-1.0,1320);
        PriceInput pi11= new PriceInput(30.0,54.0,0.29,2.0,-1.0,1860);
        PriceInput pi12= new PriceInput(30.0,54.0,0.25,2.0,-1.0,1740);
        PriceInput pi13= new PriceInput(30.0,54.0,0.22,2.0,-1.0,1780);
        PriceInput pi14= new PriceInput(30.0,54.0,0.19,2.0,-1.0,2230);
        PriceInput pi15= new PriceInput(30.0,54.0,0.16,2.0,-1.0,2430);
        PriceInput pi16= new PriceInput(30.0,54.0,0.14,2.0,-1.0,2760);
        PriceInput pi17= new PriceInput(30.0,54.0,0.12,2.0,-1.0,3120);
        PriceInput pi18= new PriceInput(30.0,54.0,0.11,2.0,-1.0,2930);
        PriceInput pi19= new PriceInput(30.0,54.0,0.1,2.0,-1.0,3450);
        PriceInput pi20= new PriceInput(30.0,54.0,0.09,2.0,-1.0,3760);
        PriceInput pi21= new PriceInput(30.0,54.0,0.08,2.0,-1.0,3560);
        PriceInput pi22= new PriceInput(30.0,54.0,0.07,2.0,-1.0,3940);
        PriceInput pi23= new PriceInput(30.0,54.0,0.06,2.0,-1.0,4270);
        PriceInput pi24= new PriceInput(30.0,54.0,0.06,2.0,-1.0,3980);
        PriceInput pi25= new PriceInput(30.0,54.0,0.05,2.0,-1.0,4320);
        PriceInput pi26= new PriceInput(30.0,54.0,0.05,2.0,-1.0,5100);
        PriceInput pi27= new PriceInput(30.0,54.0,0.05,2.0,-1.0,5670);
        PriceInput pi28= new PriceInput(30.0,54.0,0.05,2.0,-1.0,5430);
        PriceInput pi29= new PriceInput(30.0,54.0,0.04,2.0,-1.0,5790);
        PriceInput pi30= new PriceInput(30.0,54.0,0.04,2.0,-1.0,6360);
        PriceInput pi31= new PriceInput(30.0,54.0,0.04,2.0,-1.0,6130);
        PriceInput pi32= new PriceInput(30.0,54.0,0.03,2.0,-1.0,6430);
        PriceInput pi33= new PriceInput(30.0,54.0,0.03,2.0,-1.0,6890);
        PriceInput pi34= new PriceInput(30.0,54.0,0.03,2.0,-1.0,7260);
        PriceInput pi35= new PriceInput(30.0,54.0,0.03,2.0,-1.0,6840);
        PriceInput pi36= new PriceInput(30.0,54.0,0.03,2.0,-1.0,8190);
        PriceInput pi37= new PriceInput(30.0,54.0,0.02,2.0,-1.0,8100);
        PriceInput pi38= new PriceInput(30.0,54.0,0.02,2.0,-1.0,9450);
        PriceInput pi39= new PriceInput(30.0,54.0,0.02,2.0,-1.0,10000);


        DoublyLinkedList<PriceInput> priceInputs= new DoublyLinkedList<>();
        priceInputs.add(pi2);
        priceInputs.add(pi3);
        priceInputs.add(pi4);
        priceInputs.add(pi5);
        priceInputs.add(pi6);
        priceInputs.add(pi7);
        priceInputs.add(pi8);
        priceInputs.add(pi9);
        priceInputs.add(pi10);
        priceInputs.add(pi11);
        priceInputs.add(pi12);
        priceInputs.add(pi13);
        priceInputs.add(pi14);
        priceInputs.add(pi15);
        priceInputs.add(pi16);
        priceInputs.add(pi17);
        priceInputs.add(pi18);
        priceInputs.add(pi19);
        priceInputs.add(pi20);
        priceInputs.add(pi21);
        priceInputs.add(pi22);
        priceInputs.add(pi23);
        priceInputs.add(pi24);
        priceInputs.add(pi25);
        priceInputs.add(pi26);
        priceInputs.add(pi27);
        priceInputs.add(pi28);
        priceInputs.add(pi29);
        priceInputs.add(pi30);
        priceInputs.add(pi31);
        priceInputs.add(pi32);
        priceInputs.add(pi33);
        priceInputs.add(pi34);
        priceInputs.add(pi35);
        priceInputs.add(pi36);
        priceInputs.add(pi37);
        priceInputs.add(pi38);
        priceInputs.add(pi39);

        DoublyLinkedList<PriceInput> outputs= PriceCalculator.getInstance().calculatePrice(markPrice,priceInputs);

        ListIterator<PriceInput> iterator= outputs.iterator();
        while(iterator.hasNext() ){
            PriceInput tempInput=iterator.next();
            System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantity() + "| Profit: " + tempInput.getProfit());
        }
    }
}
