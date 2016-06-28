package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 29-05-2016.
 */
public class TripleExponentialSmoothingDemandForecasterTest {
    private List<ProductActualMetricsView> productActualMetricsViewList;

    @Before
    public void setUp(){
        productActualMetricsViewList= new ArrayList<>();

        ProductActualMetricsView view1= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view1.setTotalNumberOfExistingSubscriptions(280);
        productActualMetricsViewList.add(view1);

        ProductActualMetricsView view2= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view2.setTotalNumberOfExistingSubscriptions(288);
        productActualMetricsViewList.add(view2);

        ProductActualMetricsView view3= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view3.setTotalNumberOfExistingSubscriptions(266);
        productActualMetricsViewList.add(view3);

        ProductActualMetricsView view4= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view4.setTotalNumberOfExistingSubscriptions(295);
        productActualMetricsViewList.add(view4);

        ProductActualMetricsView view5= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view5.setTotalNumberOfExistingSubscriptions(302);
        productActualMetricsViewList.add(view5);

        ProductActualMetricsView view6= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view6.setTotalNumberOfExistingSubscriptions(310);
        productActualMetricsViewList.add(view6);

        ProductActualMetricsView view7= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view7.setTotalNumberOfExistingSubscriptions(303);
        productActualMetricsViewList.add(view7);

        ProductActualMetricsView view8= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view8.setTotalNumberOfExistingSubscriptions(328);
        productActualMetricsViewList.add(view8);

        ProductActualMetricsView view9= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view9.setTotalNumberOfExistingSubscriptions(309);
        productActualMetricsViewList.add(view9);

        ProductActualMetricsView view10= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view10.setTotalNumberOfExistingSubscriptions(315);
        productActualMetricsViewList.add(view10);

        ProductActualMetricsView view11= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view11.setTotalNumberOfExistingSubscriptions(320);
        productActualMetricsViewList.add(view11);

        ProductActualMetricsView view12= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view12.setTotalNumberOfExistingSubscriptions(332);
        productActualMetricsViewList.add(view12);

        ProductActualMetricsView view13= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view13.setTotalNumberOfExistingSubscriptions(310);
        productActualMetricsViewList.add(view13);

        ProductActualMetricsView view14= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view14.setTotalNumberOfExistingSubscriptions(308);
        productActualMetricsViewList.add(view14);

        ProductActualMetricsView view15= new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view15.setTotalNumberOfExistingSubscriptions(320);
        productActualMetricsViewList.add(view15);

    }
    @Test
    public void testPrecisePrediction(){
        ProductDemandForecaster forecaster= new TripleExponentialSmoothingDemandForecaster();
        List<Double> result=forecaster.forecastDemandGrowth(productActualMetricsViewList);
        System.out.println("Triple exponential Precise prediction: "+ (result == null ? "null" : result.get(0)));
    }
}
