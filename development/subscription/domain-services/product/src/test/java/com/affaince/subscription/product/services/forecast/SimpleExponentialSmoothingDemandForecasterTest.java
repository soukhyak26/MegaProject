package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 29-05-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Application.class})
public class SimpleExponentialSmoothingDemandForecasterTest {
    private List<ProductActualsView> productActualMetricsViewList;
    @Autowired
    private SimpleExponentialSmoothingDemandForecaster forecaster;
    @Before
    public void setUp(){
        productActualMetricsViewList= new ArrayList<>();

        ProductActualsView view1 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view1.setNewSubscriptions(280);
        productActualMetricsViewList.add(view1);

        ProductActualsView view2 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view2.setNewSubscriptions(288);
        productActualMetricsViewList.add(view2);

        ProductActualsView view3 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view3.setNewSubscriptions(266);
        productActualMetricsViewList.add(view3);

        ProductActualsView view4 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view4.setNewSubscriptions(295);
        productActualMetricsViewList.add(view4);

        ProductActualsView view5 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view5.setNewSubscriptions(302);
        productActualMetricsViewList.add(view5);

        ProductActualsView view6 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view6.setNewSubscriptions(310);
        productActualMetricsViewList.add(view6);

        ProductActualsView view7 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view7.setNewSubscriptions(303);
        productActualMetricsViewList.add(view7);

        ProductActualsView view8 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view8.setNewSubscriptions(328);
        productActualMetricsViewList.add(view8);

        ProductActualsView view9 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view9.setNewSubscriptions(309);
        productActualMetricsViewList.add(view9);

        ProductActualsView view10 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view10.setNewSubscriptions(315);
        productActualMetricsViewList.add(view10);

        ProductActualsView view11 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view11.setNewSubscriptions(320);
        productActualMetricsViewList.add(view11);

        ProductActualsView view12 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view12.setNewSubscriptions(332);
        productActualMetricsViewList.add(view12);

        ProductActualsView view13 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view13.setNewSubscriptions(310);
        productActualMetricsViewList.add(view13);

        ProductActualsView view14 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view14.setNewSubscriptions(308);
        productActualMetricsViewList.add(view14);

        ProductActualsView view15 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view15.setNewSubscriptions(320);
        productActualMetricsViewList.add(view15);

    }
    @Test
    public void testPrecisePrediction(){
        // TimeSeriesBasedForecaster forecaster= new SimpleExponentialSmoothingDemandForecaster();
        List<Double> historicalDailySubscriptionCountList = productActualMetricsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        ProductVersionId productVersionId = new ProductVersionId("1", new LocalDate(2016, 1, 1));
        List<Double> result = forecaster.forecast(productVersionId.getProductId(), historicalDailySubscriptionCountList);
        System.out.println("Precise prediction: "+ (result == null ? "null" : result.get(0)));
    }
}
