package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
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
public class TripleExponentialSmoothingDemandForecasterTest {
    @Autowired
    TripleExponentialSmoothingDemandForecaster forecaster;
    private List<ProductActualMetricsView> productActualMetricsViewList;

    @Before
    public void setUp(){
        productActualMetricsViewList= new ArrayList<>();

        ProductActualMetricsView view1 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view1.setNewSubscriptions(280);
        productActualMetricsViewList.add(view1);

        ProductActualMetricsView view2 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view2.setNewSubscriptions(288);
        productActualMetricsViewList.add(view2);

        ProductActualMetricsView view3 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view3.setNewSubscriptions(266);
        productActualMetricsViewList.add(view3);

        ProductActualMetricsView view4 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view4.setNewSubscriptions(295);
        productActualMetricsViewList.add(view4);

        ProductActualMetricsView view5 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view5.setNewSubscriptions(302);
        productActualMetricsViewList.add(view5);

        ProductActualMetricsView view6 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view6.setNewSubscriptions(310);
        productActualMetricsViewList.add(view6);

        ProductActualMetricsView view7 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view7.setNewSubscriptions(303);
        productActualMetricsViewList.add(view7);

        ProductActualMetricsView view8 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view8.setNewSubscriptions(328);
        productActualMetricsViewList.add(view8);

        ProductActualMetricsView view9 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view9.setNewSubscriptions(309);
        productActualMetricsViewList.add(view9);

        ProductActualMetricsView view10 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view10.setNewSubscriptions(315);
        productActualMetricsViewList.add(view10);

        ProductActualMetricsView view11 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view11.setNewSubscriptions(320);
        productActualMetricsViewList.add(view11);

        ProductActualMetricsView view12 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view12.setNewSubscriptions(332);
        productActualMetricsViewList.add(view12);

        ProductActualMetricsView view13 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view13.setNewSubscriptions(310);
        productActualMetricsViewList.add(view13);

        ProductActualMetricsView view14 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view14.setNewSubscriptions(308);
        productActualMetricsViewList.add(view14);

        ProductActualMetricsView view15 = new ProductActualMetricsView(new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
        view15.setNewSubscriptions(320);
        productActualMetricsViewList.add(view15);

    }
    @Test
    public void testPrecisePrediction(){
        // TimeSeriesBasedForecaster forecaster= new TripleExponentialSmoothingDemandForecaster();
        List<Double> historicalDailySubscriptionCountList = productActualMetricsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        ProductVersionId productVersionId = new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0));

        List<Double> result = forecaster.forecast(productVersionId.getProductId(), historicalDailySubscriptionCountList);
        System.out.println("Triple exponential Precise prediction: "+ (result == null ? "null" : result.get(0)));
    }
}
