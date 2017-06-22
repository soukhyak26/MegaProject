package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.service.forecast.SimpleMovingAverageDemandForecaster;
import com.affaince.subscription.common.service.forecast.config.HistoryMaxSizeConstraints;
import com.affaince.subscription.common.service.forecast.config.HistoryMinSizeConstraints;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Ignore;
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
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Application.class})
public class SimpleMovingAverageDemandForecasterTest {
    @Autowired
    SimpleMovingAverageDemandForecaster forecaster;
    @Autowired
    private HistoryMinSizeConstraints historyMinSizeConstraints;
    @Autowired
    private HistoryMaxSizeConstraints historyMaxSizeConstraints;

    private List<ProductActualsView> ProductActualsViewList;

    @Before
    public void setUp(){
        ProductActualsViewList= new ArrayList<>();
        long totalSubscriptions=0;
        ProductActualsView view1 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),280,0,280);
        ProductActualsViewList.add(view1);

        totalSubscriptions=totalSubscriptions+288;
        ProductActualsView view2 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),288,0,totalSubscriptions);
        ProductActualsViewList.add(view2);

        totalSubscriptions=totalSubscriptions+266;
        ProductActualsView view3 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),266,0,totalSubscriptions);
        ProductActualsViewList.add(view3);

        totalSubscriptions=totalSubscriptions+295;
        ProductActualsView view4 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),295,0,totalSubscriptions);
        ProductActualsViewList.add(view4);

        totalSubscriptions=totalSubscriptions+302;
        ProductActualsView view5 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),302,0,totalSubscriptions);
        ProductActualsViewList.add(view5);

        totalSubscriptions=totalSubscriptions+310;
        ProductActualsView view6 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),310,0,totalSubscriptions);
        ProductActualsViewList.add(view6);

        totalSubscriptions=totalSubscriptions+303;
        ProductActualsView view7 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),303,0,totalSubscriptions);
        ProductActualsViewList.add(view7);

        totalSubscriptions=totalSubscriptions+328;
        ProductActualsView view8 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),328,0,totalSubscriptions);
        ProductActualsViewList.add(view8);

        totalSubscriptions=totalSubscriptions+309;
        ProductActualsView view9 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),309,0,totalSubscriptions);
        ProductActualsViewList.add(view9);

        totalSubscriptions=totalSubscriptions+315;
        ProductActualsView view10 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),315,0,totalSubscriptions);
        ProductActualsViewList.add(view10);

        totalSubscriptions=totalSubscriptions+320;
        ProductActualsView view11 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),320,0,totalSubscriptions);
        ProductActualsViewList.add(view11);

        totalSubscriptions=totalSubscriptions+332;
        ProductActualsView view12 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),332,0,totalSubscriptions);
        ProductActualsViewList.add(view12);

        totalSubscriptions=totalSubscriptions+310;
        ProductActualsView view13 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),310,0,totalSubscriptions);
        ProductActualsViewList.add(view13);

        totalSubscriptions=totalSubscriptions+308;
        ProductActualsView view14 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),308,0,totalSubscriptions);
        ProductActualsViewList.add(view14);

        totalSubscriptions=totalSubscriptions+320;
        ProductActualsView view15 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),320,0,totalSubscriptions);
        ProductActualsViewList.add(view15);

    }
    @Test
    public void testPrecisePrediction(){
        // TimeSeriesBasedForecaster forecaster= new SimpleMovingAverageDemandForecaster();
        List<Double> historicalDailySubscriptionCountList = ProductActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        ProductVersionId productVersionId = new ProductVersionId("1", new LocalDate(2016, 1, 1));
        forecaster.setHistoryMinSizeConstraints(this.historyMinSizeConstraints);
        forecaster.setHistoryMaxSizeConstraints(this.historyMaxSizeConstraints);

        List<Double> result = forecaster.forecast(productVersionId.getProductId(), historicalDailySubscriptionCountList);
        System.out.println("Precise prediction: "+ result.get(0));
    }
}
