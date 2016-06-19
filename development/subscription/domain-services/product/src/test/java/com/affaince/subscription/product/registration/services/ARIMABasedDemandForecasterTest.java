package com.affaince.subscription.product.registration.services;

import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.registration.services.forecast.ARIMABasedDemandForecaster;
import com.affaince.subscription.product.registration.services.forecast.ProductDemandForecaster;
import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 19-06-2016.
 */
public class ARIMABasedDemandForecasterTest {
    private List<ProductActualMetricsView> productActualMetricsViewList;
    @Before
    public void setUp(){
        productActualMetricsViewList= new ArrayList<>();

        ProductActualMetricsView view1= new ProductActualMetricsView("1",new YearMonth(2016,1));
        view1.setTotalNumberOfExistingSubscriptions(500);
        productActualMetricsViewList.add(view1);

        ProductActualMetricsView view2= new ProductActualMetricsView("1",new YearMonth(2016,2));
        view2.setTotalNumberOfExistingSubscriptions(750);
        productActualMetricsViewList.add(view2);

        ProductActualMetricsView view3= new ProductActualMetricsView("1",new YearMonth(2016,3));
        view3.setTotalNumberOfExistingSubscriptions(1000);
        productActualMetricsViewList.add(view3);

        ProductActualMetricsView view4= new ProductActualMetricsView("1",new YearMonth(2016,4));
        view4.setTotalNumberOfExistingSubscriptions(1250);
        productActualMetricsViewList.add(view4);

        ProductActualMetricsView view5= new ProductActualMetricsView("1",new YearMonth(2016,5));
        view5.setTotalNumberOfExistingSubscriptions(1500);
        productActualMetricsViewList.add(view5);

        ProductActualMetricsView view6= new ProductActualMetricsView("1",new YearMonth(2016,6));
        view6.setTotalNumberOfExistingSubscriptions(1750);
        productActualMetricsViewList.add(view6);

        ProductActualMetricsView view7= new ProductActualMetricsView("1",new YearMonth(2016,7));
        view7.setTotalNumberOfExistingSubscriptions(2000);
        productActualMetricsViewList.add(view7);

        ProductActualMetricsView view8= new ProductActualMetricsView("1",new YearMonth(2016,8));
        view8.setTotalNumberOfExistingSubscriptions(2250);
        productActualMetricsViewList.add(view8);

        ProductActualMetricsView view9= new ProductActualMetricsView("1",new YearMonth(2016,9));
        view9.setTotalNumberOfExistingSubscriptions(2500);
        productActualMetricsViewList.add(view9);

        ProductActualMetricsView view10= new ProductActualMetricsView("1",new YearMonth(2016,10));
        view10.setTotalNumberOfExistingSubscriptions(2750);
        productActualMetricsViewList.add(view10);

        ProductActualMetricsView view11= new ProductActualMetricsView("1",new YearMonth(2016,11));
        view11.setTotalNumberOfExistingSubscriptions(3000);
        productActualMetricsViewList.add(view11);

        ProductActualMetricsView view12= new ProductActualMetricsView("1",new YearMonth(2016,12));
        view12.setTotalNumberOfExistingSubscriptions(3250);
        productActualMetricsViewList.add(view12);

        ProductActualMetricsView view13= new ProductActualMetricsView("1",new YearMonth(2017,01));
        view13.setTotalNumberOfExistingSubscriptions(3500);
        productActualMetricsViewList.add(view13);

        ProductActualMetricsView view14= new ProductActualMetricsView("1",new YearMonth(2017,02));
        view14.setTotalNumberOfExistingSubscriptions(3750);
        productActualMetricsViewList.add(view14);

        ProductActualMetricsView view15= new ProductActualMetricsView("1",new YearMonth(2017,03));
        view15.setTotalNumberOfExistingSubscriptions(4000);
        productActualMetricsViewList.add(view15);

        ProductActualMetricsView view16= new ProductActualMetricsView("1",new YearMonth(2017,04));
        view16.setTotalNumberOfExistingSubscriptions(4250);
        productActualMetricsViewList.add(view16);

        ProductActualMetricsView view17= new ProductActualMetricsView("1",new YearMonth(2017,05));
        view17.setTotalNumberOfExistingSubscriptions(4500);
        productActualMetricsViewList.add(view17);

        ProductActualMetricsView view18= new ProductActualMetricsView("1",new YearMonth(2017,06));
        view18.setTotalNumberOfExistingSubscriptions(4750);
        productActualMetricsViewList.add(view18);

        ProductActualMetricsView view19= new ProductActualMetricsView("1",new YearMonth(2017,07));
        view19.setTotalNumberOfExistingSubscriptions(5000);
        productActualMetricsViewList.add(view19);

        ProductActualMetricsView view20= new ProductActualMetricsView("1",new YearMonth(2017,8));
        view20.setTotalNumberOfExistingSubscriptions(5250);
        productActualMetricsViewList.add(view20);

        ProductActualMetricsView view21= new ProductActualMetricsView("1",new YearMonth(2017,9));
        view21.setTotalNumberOfExistingSubscriptions(5500);
        productActualMetricsViewList.add(view21);

        ProductActualMetricsView view22= new ProductActualMetricsView("1",new YearMonth(2017,10));
        view22.setTotalNumberOfExistingSubscriptions(5750);
        productActualMetricsViewList.add(view22);

        ProductActualMetricsView view23= new ProductActualMetricsView("1",new YearMonth(2017,11));
        view23.setTotalNumberOfExistingSubscriptions(6000);
        productActualMetricsViewList.add(view23);

        ProductActualMetricsView view24= new ProductActualMetricsView("1",new YearMonth(2017,12));
        view24.setTotalNumberOfExistingSubscriptions(6250);
        productActualMetricsViewList.add(view24);

        ProductActualMetricsView view25= new ProductActualMetricsView("1",new YearMonth(2018,1));
        view25.setTotalNumberOfExistingSubscriptions(6500);
        productActualMetricsViewList.add(view25);

        ProductActualMetricsView view26= new ProductActualMetricsView("1",new YearMonth(2018,2));
        view26.setTotalNumberOfExistingSubscriptions(6750);
        productActualMetricsViewList.add(view26);

        ProductActualMetricsView view27= new ProductActualMetricsView("1",new YearMonth(2018,3));
        view27.setTotalNumberOfExistingSubscriptions(7000);
        productActualMetricsViewList.add(view27);

        ProductActualMetricsView view28= new ProductActualMetricsView("1",new YearMonth(2018,4));
        view28.setTotalNumberOfExistingSubscriptions(7250);
        productActualMetricsViewList.add(view28);

        ProductActualMetricsView view29= new ProductActualMetricsView("1",new YearMonth(2018,5));
        view29.setTotalNumberOfExistingSubscriptions(7500);
        productActualMetricsViewList.add(view29);

        ProductActualMetricsView view30= new ProductActualMetricsView("1",new YearMonth(2018,6));
        view30.setTotalNumberOfExistingSubscriptions(7750);
        productActualMetricsViewList.add(view30);

        ProductActualMetricsView view31= new ProductActualMetricsView("1",new YearMonth(2018,7));
        view31.setTotalNumberOfExistingSubscriptions(8000);
        productActualMetricsViewList.add(view31);

        ProductActualMetricsView view32= new ProductActualMetricsView("1",new YearMonth(2018,8));
        view32.setTotalNumberOfExistingSubscriptions(8250);
        productActualMetricsViewList.add(view32);

        ProductActualMetricsView view33= new ProductActualMetricsView("1",new YearMonth(2018,9));
        view33.setTotalNumberOfExistingSubscriptions(8500);
        productActualMetricsViewList.add(view33);

        ProductActualMetricsView view34= new ProductActualMetricsView("1",new YearMonth(2018,10));
        view34.setTotalNumberOfExistingSubscriptions(8750);
        productActualMetricsViewList.add(view34);

        ProductActualMetricsView view35= new ProductActualMetricsView("1",new YearMonth(2018,11));
        view35.setTotalNumberOfExistingSubscriptions(9000);
        productActualMetricsViewList.add(view35);

        ProductActualMetricsView view36= new ProductActualMetricsView("1",new YearMonth(2018,12));
        view36.setTotalNumberOfExistingSubscriptions(9250);
        productActualMetricsViewList.add(view36);

        ProductActualMetricsView view37= new ProductActualMetricsView("1",new YearMonth(2019,01));
        view37.setTotalNumberOfExistingSubscriptions(9500);
        productActualMetricsViewList.add(view37);

        ProductActualMetricsView view38= new ProductActualMetricsView("1",new YearMonth(2019,2));
        view38.setTotalNumberOfExistingSubscriptions(9750);
        productActualMetricsViewList.add(view38);

        ProductActualMetricsView view39= new ProductActualMetricsView("1",new YearMonth(2019,3));
        view39.setTotalNumberOfExistingSubscriptions(10000);
        productActualMetricsViewList.add(view39);

        ProductActualMetricsView view40= new ProductActualMetricsView("1",new YearMonth(2019,4));
        view40.setTotalNumberOfExistingSubscriptions(10250);
        productActualMetricsViewList.add(view40);

    }
    @Test
    public void testPrecisePrediction(){
        ProductDemandForecaster forecaster= new ARIMABasedDemandForecaster();
        List<Double> result=forecaster.forecastDemandGrowth(productActualMetricsViewList);
        System.out.println("ARIMA Precise prediction: "+ result.get(0));
    }

}
