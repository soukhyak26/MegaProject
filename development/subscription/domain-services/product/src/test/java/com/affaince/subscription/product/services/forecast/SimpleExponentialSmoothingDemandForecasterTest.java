package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.service.forecast.SimpleExponentialSmoothingDemandForecaster;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.joda.time.LocalDate;
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
    @Autowired
    private SimpleExponentialSmoothingDemandForecaster forecaster;
    private List<ProductActualsView> productActualsViewList;
    @Before
    public void setUp(){
        productActualsViewList = new ArrayList<>();
        long totalSubscriptions=0;
        ProductActualsView view1 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),280,0,280);
        productActualsViewList.add(view1);

        totalSubscriptions=totalSubscriptions+288;
        ProductActualsView view2 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),288,0,totalSubscriptions);
        productActualsViewList.add(view2);

        totalSubscriptions=totalSubscriptions+266;
        ProductActualsView view3 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),266,0,totalSubscriptions);
        productActualsViewList.add(view3);

        totalSubscriptions=totalSubscriptions+295;
        ProductActualsView view4 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),295,0,totalSubscriptions);
        productActualsViewList.add(view4);

        totalSubscriptions=totalSubscriptions+302;
        ProductActualsView view5 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),302,0,totalSubscriptions);
        productActualsViewList.add(view5);

        totalSubscriptions=totalSubscriptions+310;
        ProductActualsView view6 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),310,0,totalSubscriptions);
        productActualsViewList.add(view6);

        totalSubscriptions=totalSubscriptions+303;
        ProductActualsView view7 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),303,0,totalSubscriptions);
        productActualsViewList.add(view7);

        totalSubscriptions=totalSubscriptions+328;
        ProductActualsView view8 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),328,0,totalSubscriptions);
        productActualsViewList.add(view8);

        totalSubscriptions=totalSubscriptions+309;
        ProductActualsView view9 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),309,0,totalSubscriptions);
        productActualsViewList.add(view9);

        totalSubscriptions=totalSubscriptions+315;
        ProductActualsView view10 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),315,0,totalSubscriptions);
        productActualsViewList.add(view10);

        totalSubscriptions=totalSubscriptions+320;
        ProductActualsView view11 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),320,0,totalSubscriptions);
        productActualsViewList.add(view11);

        totalSubscriptions=totalSubscriptions+332;
        ProductActualsView view12 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),332,0,totalSubscriptions);
        productActualsViewList.add(view12);

        totalSubscriptions=totalSubscriptions+310;
        ProductActualsView view13 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),310,0,totalSubscriptions);
        productActualsViewList.add(view13);

        totalSubscriptions=totalSubscriptions+308;
        ProductActualsView view14 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),308,0,totalSubscriptions);
        productActualsViewList.add(view14);

        totalSubscriptions=totalSubscriptions+320;
        ProductActualsView view15 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),320,0,totalSubscriptions);
        productActualsViewList.add(view15);

    }
    @Test
    public void testPrecisePrediction(){
        // TimeSeriesBasedForecaster forecaster= new SimpleExponentialSmoothingDemandForecaster();
        List<Double> historicalDailySubscriptionCountList = productActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        ProductVersionId productVersionId = new ProductVersionId("1", new LocalDate(2016, 1, 1));
        List<Double> result = forecaster.forecast(productVersionId.getProductId(), historicalDailySubscriptionCountList);
        System.out.println("Precise prediction: "+ (result == null ? "null" : result.get(0)));
    }
}
