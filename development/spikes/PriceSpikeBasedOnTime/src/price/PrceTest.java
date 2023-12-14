package price;


import java.util.ListIterator;

/**
 * Created by mandark on 12-04-2016.
 */
public class PrceTest {
    public static void main(String[] args){
        calculatePriceSet1();
        /*calculatePriceSet2();
        calculatePriceSet3();
        calculatePriceSet4();*/
    }

    public static void calculatePriceSet1(){
        LocalDate startDate = new LocalDate().withYear(2016).withMonthOfYear(1).withDayOfMonth(1);
        price.PriceInput markPrice= new PriceInput(30.0,54.0,5.55,2.0,54,0,startDate,490);

        PriceInput pi2= new PriceInput(30.0,54.0,5.55,2.0,50,540,startDate.withFieldAdded(DurationFieldType.days(),10),10);
        PriceInput pi3= new PriceInput(30.0,54.0,2.54,2.0,-1.0,640,startDate.withFieldAdded(DurationFieldType.days(),10),10);
        PriceInput pi4= new PriceInput(30.0,54.0,1.60,2.0,-1.0,690,startDate.withFieldAdded(DurationFieldType.days(),10),10);
        PriceInput pi5= new PriceInput(30.0,54.0,1.23,2.0,-1.0,790,startDate.withFieldAdded(DurationFieldType.days(),10),10);
        PriceInput pi6= new PriceInput(30.0,54.0,0.83,2.0,-1.0,970,startDate.withFieldAdded(DurationFieldType.days(),9),9);
        PriceInput pi7= new PriceInput(30.0,54.0,0.67,2.0,-1.0,820,startDate.withFieldAdded(DurationFieldType.days(),9),9);
        PriceInput pi8= new PriceInput(30.0,54.0,0.53,2.0,-1.0,1250,startDate.withFieldAdded(DurationFieldType.days(),9),9);
        PriceInput pi9= new PriceInput(30.0,54.0,0.42,2.0,-1.0,1450,startDate.withFieldAdded(DurationFieldType.days(),8),8);
        PriceInput pi10= new PriceInput(30.0,54.0,0.35,2.0,-1.0,1320,startDate.withFieldAdded(DurationFieldType.days(),8),8);
        PriceInput pi11= new PriceInput(30.0,54.0,0.29,2.0,-1.0,1860,startDate.withFieldAdded(DurationFieldType.days(),8),8);
        PriceInput pi12= new PriceInput(30.0,54.0,0.25,2.0,-1.0,1740,startDate.withFieldAdded(DurationFieldType.days(),7),7);
        PriceInput pi13= new PriceInput(30.0,54.0,0.22,2.0,-1.0,1780,startDate.withFieldAdded(DurationFieldType.days(),7),7);
        PriceInput pi14= new PriceInput(30.0,54.0,0.19,2.0,-1.0,2230,startDate.withFieldAdded(DurationFieldType.days(),7),7);
        PriceInput pi15= new PriceInput(30.0,54.0,0.16,2.0,-1.0,2430,startDate.withFieldAdded(DurationFieldType.days(),7),7);
        PriceInput pi16= new PriceInput(30.0,54.0,0.14,2.0,-1.0,2760,startDate.withFieldAdded(DurationFieldType.days(),6),6);
        PriceInput pi17= new PriceInput(30.0,54.0,0.12,2.0,-1.0,3120,startDate.withFieldAdded(DurationFieldType.days(),6),6);
        PriceInput pi18= new PriceInput(30.0,54.0,0.11,2.0,-1.0,2930,startDate.withFieldAdded(DurationFieldType.days(),6),6);
        PriceInput pi19= new PriceInput(30.0,54.0,0.1,2.0,-1.0,3450,startDate.withFieldAdded(DurationFieldType.days(),5),5);
        PriceInput pi20= new PriceInput(30.0,54.0,0.09,2.0,-1.0,3760,startDate.withFieldAdded(DurationFieldType.days(),5),5);
        PriceInput pi21= new PriceInput(30.0,54.0,0.08,2.0,-1.0,3560,startDate.withFieldAdded(DurationFieldType.days(),4),4);
        PriceInput pi22= new PriceInput(30.0,54.0,0.07,2.0,-1.0,3940,startDate.withFieldAdded(DurationFieldType.days(),4),4);
        PriceInput pi23= new PriceInput(30.0,54.0,0.06,2.0,-1.0,4270,startDate.withFieldAdded(DurationFieldType.days(),4),4);
        PriceInput pi24= new PriceInput(30.0,54.0,0.06,2.0,-1.0,3980,startDate.withFieldAdded(DurationFieldType.days(),3),3);
        PriceInput pi25= new PriceInput(30.0,54.0,0.05,2.0,-1.0,4320,startDate.withFieldAdded(DurationFieldType.days(),3),3);
        PriceInput pi26= new PriceInput(30.0,54.0,0.05,2.0,-1.0,5100,startDate.withFieldAdded(DurationFieldType.days(),3),3);
        PriceInput pi27= new PriceInput(30.0,54.0,0.05,2.0,-1.0,5670,startDate.withFieldAdded(DurationFieldType.days(),3),3);
        PriceInput pi28= new PriceInput(30.0,54.0,0.05,2.0,-1.0,5430,startDate.withFieldAdded(DurationFieldType.days(),2), 2);
        PriceInput pi29= new PriceInput(30.0,54.0,0.04,2.0,-1.0,5790,startDate.withFieldAdded(DurationFieldType.days(),2),2);
        PriceInput pi30= new PriceInput(30.0,54.0,0.04,2.0,-1.0,6360,startDate.withFieldAdded(DurationFieldType.days(),2),2);
        PriceInput pi31= new PriceInput(30.0,54.0,0.04,2.0,-1.0,6130,startDate.withFieldAdded(DurationFieldType.days(),2),2);
        PriceInput pi32= new PriceInput(30.0,54.0,0.03,2.0,-1.0,6430,startDate.withFieldAdded(DurationFieldType.days(),2),2);
        PriceInput pi33= new PriceInput(30.0,54.0,0.03,2.0,-1.0,6890,startDate.withFieldAdded(DurationFieldType.days(),2),2);
        PriceInput pi34= new PriceInput(30.0,54.0,0.03,2.0,-1.0,7260,startDate.withFieldAdded(DurationFieldType.days(),2),2);
        PriceInput pi35= new PriceInput(30.0,54.0,0.03,2.0,-1.0,6840,startDate.withFieldAdded(DurationFieldType.days(),2),2);
        PriceInput pi36= new PriceInput(30.0,54.0,0.03,2.0,-1.0,8190,startDate.withFieldAdded(DurationFieldType.days(),1),1);
        PriceInput pi37= new PriceInput(30.0,54.0,0.02,2.0,-1.0,8100,startDate.withFieldAdded(DurationFieldType.days(),1),1);
        PriceInput pi38= new PriceInput(30.0,54.0,0.02,2.0,-1.0,9450,startDate.withFieldAdded(DurationFieldType.days(),1),1);
        PriceInput pi39= new PriceInput(30.0,54.0,0.02,2.0,-1.0,10000,startDate.withFieldAdded(DurationFieldType.days(),1),1);


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
        double accumulatedProfit=0.0;
        while(iterator.hasNext() ){
            PriceInput tempInput=iterator.next();
            accumulatedProfit +=tempInput.getProfit();
            System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantity() + "| cost: " + tempInput.getCost() + "| Revenue: " + tempInput.getRevenue() + "| Profit: " + tempInput.getProfit());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 34: " + accumulatedProfit);

    }
    /*public static void calculatePriceSet2(){
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
        double accumulatedProfit=0.0;
        while(iterator.hasNext() ){
            PriceInput tempInput=iterator.next();
            accumulatedProfit +=tempInput.getProfit();
           // System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantity() + "| cost: " + tempInput.getCost() + "| Revenue: " + tempInput.getRevenue() + "| Profit: " + tempInput.getProfit());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 45: " + accumulatedProfit);

    }

    public static void calculatePriceSet3(){
        PriceInput markPrice= new PriceInput(30.0,54.0,5.55,2.0,54,0);

        PriceInput pi2= new PriceInput(30.0,54.0,5.55,2.0,50,540);
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
        double accumulatedProfit=0.0;
        while(iterator.hasNext() ){
            PriceInput tempInput=iterator.next();
            accumulatedProfit +=tempInput.getProfit();
            //System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantity() + "| cost: " + tempInput.getCost() + "| Revenue: " + tempInput.getRevenue() + "| Profit: " + tempInput.getProfit());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 50: " + accumulatedProfit);

    }

    public static void calculatePriceSet4(){
        PriceInput markPrice= new PriceInput(30.0,54.0,5.55,2.0,54,0);

        PriceInput pi2= new PriceInput(30.0,54.0,5.55,2.0,53,540);
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
        double accumulatedProfit=0.0;
        while(iterator.hasNext() ){
            PriceInput tempInput=iterator.next();
            accumulatedProfit +=tempInput.getProfit();
            //System.out.println("slope: " + tempInput.getSlope() + "| offeredPrice: " + tempInput.getOfferedPrice() + "| qunatity: " + tempInput.getQuantity() + "| cost: " + tempInput.getCost() + "| Revenue: " + tempInput.getRevenue() + "| Profit: " + tempInput.getProfit());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("accumulted profit for 54: " + accumulatedProfit);

    }*/

}
