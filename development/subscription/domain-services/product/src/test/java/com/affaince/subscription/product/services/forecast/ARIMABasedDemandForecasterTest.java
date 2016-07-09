package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 19-06-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Application.class})
public class ARIMABasedDemandForecasterTest {
    private List<ProductActualMetricsView> productActualMetricsViewList;
    @Autowired
    ARIMABasedDemandForecaster forecaster;
    @Before
    public void setUp(){
/*
        productActualMetricsViewList= new ArrayList<>();

        ProductActualMetricsView view1= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view1.setTotalNumberOfExistingSubscriptions(500);
        productActualMetricsViewList.add(view1);

        ProductActualMetricsView view2= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view2.setTotalNumberOfExistingSubscriptions(750);
        productActualMetricsViewList.add(view2);

        ProductActualMetricsView view3= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view3.setTotalNumberOfExistingSubscriptions(1000);
        productActualMetricsViewList.add(view3);

        ProductActualMetricsView view4= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view4.setTotalNumberOfExistingSubscriptions(1250);
        productActualMetricsViewList.add(view4);

        ProductActualMetricsView view5= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view5.setTotalNumberOfExistingSubscriptions(1500);
        productActualMetricsViewList.add(view5);

        ProductActualMetricsView view6= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view6.setTotalNumberOfExistingSubscriptions(1750);
        productActualMetricsViewList.add(view6);

        ProductActualMetricsView view7= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view7.setTotalNumberOfExistingSubscriptions(2000);
        productActualMetricsViewList.add(view7);

        ProductActualMetricsView view8= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view8.setTotalNumberOfExistingSubscriptions(2250);
        productActualMetricsViewList.add(view8);

        ProductActualMetricsView view9= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view9.setTotalNumberOfExistingSubscriptions(2500);
        productActualMetricsViewList.add(view9);

        ProductActualMetricsView view10= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view10.setTotalNumberOfExistingSubscriptions(2750);
        productActualMetricsViewList.add(view10);

        ProductActualMetricsView view11= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view11.setTotalNumberOfExistingSubscriptions(3000);
        productActualMetricsViewList.add(view11);

        ProductActualMetricsView view12= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view12.setTotalNumberOfExistingSubscriptions(3250);
        productActualMetricsViewList.add(view12);

        ProductActualMetricsView view13= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view13.setTotalNumberOfExistingSubscriptions(3500);
        productActualMetricsViewList.add(view13);

        ProductActualMetricsView view14= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view14.setTotalNumberOfExistingSubscriptions(3750);
        productActualMetricsViewList.add(view14);

        ProductActualMetricsView view15= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view15.setTotalNumberOfExistingSubscriptions(4000);
        productActualMetricsViewList.add(view15);

        ProductActualMetricsView view16= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view16.setTotalNumberOfExistingSubscriptions(4250);
        productActualMetricsViewList.add(view16);

        ProductActualMetricsView view17= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view17.setTotalNumberOfExistingSubscriptions(4500);
        productActualMetricsViewList.add(view17);

        ProductActualMetricsView view18= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view18.setTotalNumberOfExistingSubscriptions(4750);
        productActualMetricsViewList.add(view18);

        ProductActualMetricsView view19= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view19.setTotalNumberOfExistingSubscriptions(5000);
        productActualMetricsViewList.add(view19);

        ProductActualMetricsView view20= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view20.setTotalNumberOfExistingSubscriptions(5250);
        productActualMetricsViewList.add(view20);

        ProductActualMetricsView view21= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view21.setTotalNumberOfExistingSubscriptions(5500);
        productActualMetricsViewList.add(view21);

        ProductActualMetricsView view22= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view22.setTotalNumberOfExistingSubscriptions(5750);
        productActualMetricsViewList.add(view22);

        ProductActualMetricsView view23= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view23.setTotalNumberOfExistingSubscriptions(6000);
        productActualMetricsViewList.add(view23);

        ProductActualMetricsView view24= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view24.setTotalNumberOfExistingSubscriptions(6250);
        productActualMetricsViewList.add(view24);

        ProductActualMetricsView view25= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view25.setTotalNumberOfExistingSubscriptions(6500);
        productActualMetricsViewList.add(view25);

        ProductActualMetricsView view26= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view26.setTotalNumberOfExistingSubscriptions(6750);
        productActualMetricsViewList.add(view26);

        ProductActualMetricsView view27= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view27.setTotalNumberOfExistingSubscriptions(7000);
        productActualMetricsViewList.add(view27);

        ProductActualMetricsView view28= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view28.setTotalNumberOfExistingSubscriptions(7250);
        productActualMetricsViewList.add(view28);

        ProductActualMetricsView view29= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view29.setTotalNumberOfExistingSubscriptions(7500);
        productActualMetricsViewList.add(view29);

        ProductActualMetricsView view30= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view30.setTotalNumberOfExistingSubscriptions(7750);
        productActualMetricsViewList.add(view30);

        ProductActualMetricsView view31= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view31.setTotalNumberOfExistingSubscriptions(8000);
        productActualMetricsViewList.add(view31);

        ProductActualMetricsView view32= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view32.setTotalNumberOfExistingSubscriptions(8250);
        productActualMetricsViewList.add(view32);

        ProductActualMetricsView view33= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view33.setTotalNumberOfExistingSubscriptions(8500);
        productActualMetricsViewList.add(view33);

        ProductActualMetricsView view34= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view34.setTotalNumberOfExistingSubscriptions(8750);
        productActualMetricsViewList.add(view34);

        ProductActualMetricsView view35= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view35.setTotalNumberOfExistingSubscriptions(9000);
        productActualMetricsViewList.add(view35);

        ProductActualMetricsView view36= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view36.setTotalNumberOfExistingSubscriptions(9250);
        productActualMetricsViewList.add(view36);

        ProductActualMetricsView view37= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view37.setTotalNumberOfExistingSubscriptions(9500);
        productActualMetricsViewList.add(view37);

        ProductActualMetricsView view38= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view38.setTotalNumberOfExistingSubscriptions(9750);
        productActualMetricsViewList.add(view38);

        ProductActualMetricsView view39= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view39.setTotalNumberOfExistingSubscriptions(10000);
        productActualMetricsViewList.add(view39);

        ProductActualMetricsView view40= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view40.setTotalNumberOfExistingSubscriptions(10250);
        productActualMetricsViewList.add(view40);

        ProductActualMetricsView view41= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view41.setTotalNumberOfExistingSubscriptions(10500);
        productActualMetricsViewList.add(view41);

        ProductActualMetricsView view42= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view42.setTotalNumberOfExistingSubscriptions(10750);
        productActualMetricsViewList.add(view42);

        ProductActualMetricsView view43= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view43.setTotalNumberOfExistingSubscriptions(11000);
        productActualMetricsViewList.add(view43);

        ProductActualMetricsView view44= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view44.setTotalNumberOfExistingSubscriptions(11250);
        productActualMetricsViewList.add(view44);

        ProductActualMetricsView view45= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view45.setTotalNumberOfExistingSubscriptions(11500);
        productActualMetricsViewList.add(view45);

        ProductActualMetricsView view46= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view46.setTotalNumberOfExistingSubscriptions(11750);
        productActualMetricsViewList.add(view46);

        ProductActualMetricsView view47= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view47.setTotalNumberOfExistingSubscriptions(12000);
        productActualMetricsViewList.add(view47);

        ProductActualMetricsView view48= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view48.setTotalNumberOfExistingSubscriptions(12250);
        productActualMetricsViewList.add(view48);

        ProductActualMetricsView view49= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view49.setTotalNumberOfExistingSubscriptions(12500);
        productActualMetricsViewList.add(view49);

        ProductActualMetricsView view50= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view50.setTotalNumberOfExistingSubscriptions(12750);
        productActualMetricsViewList.add(view50);

        ProductActualMetricsView view51= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view51.setTotalNumberOfExistingSubscriptions(13000);
        productActualMetricsViewList.add(view51);

        ProductActualMetricsView view52= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view52.setTotalNumberOfExistingSubscriptions(13250);
        productActualMetricsViewList.add(view52);

        ProductActualMetricsView view53= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view53.setTotalNumberOfExistingSubscriptions(13500);
        productActualMetricsViewList.add(view53);

        ProductActualMetricsView view54= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view54.setTotalNumberOfExistingSubscriptions(13750);
        productActualMetricsViewList.add(view54);

        ProductActualMetricsView view55= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view55.setTotalNumberOfExistingSubscriptions(14000);
        productActualMetricsViewList.add(view55);

        ProductActualMetricsView view56= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view56.setTotalNumberOfExistingSubscriptions(14250);
        productActualMetricsViewList.add(view56);

        ProductActualMetricsView view57= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view57.setTotalNumberOfExistingSubscriptions(14500);
        productActualMetricsViewList.add(view57);

        ProductActualMetricsView view58= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view58.setTotalNumberOfExistingSubscriptions(14750);
        productActualMetricsViewList.add(view58);

        ProductActualMetricsView view59= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view59.setTotalNumberOfExistingSubscriptions(15000);
        productActualMetricsViewList.add(view59);

        ProductActualMetricsView view60= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view60.setTotalNumberOfExistingSubscriptions(15250);
        productActualMetricsViewList.add(view60);

        ProductActualMetricsView view61= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view61.setTotalNumberOfExistingSubscriptions(15500);
        productActualMetricsViewList.add(view61);
*/

    }
    @Test
    public void testPrecisePrediction() throws FileNotFoundException,IOException{
    //    ProductDemandForecaster forecaster= new ARIMABasedDemandForecaster();
        productActualMetricsViewList= new ArrayList<>();

        BufferedReader fileReader= new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        double[] values= fileReader.lines().mapToDouble(n->Double.parseDouble(n)).toArray();
        for(Double value: values){
            ProductActualMetricsView view= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
            view.setTotalNumberOfExistingSubscriptions(Double.valueOf(value).longValue());
            productActualMetricsViewList.add(view);
        }
        List<Double> result=forecaster.forecastDemandGrowth(productActualMetricsViewList);
        System.out.println("ARIMA Precise prediction: " + (result == null ? "null" : result.get(0)));
    }

}
