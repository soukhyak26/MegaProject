package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
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
import java.util.stream.Stream;

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

    }
    @Test
    public void testPrecisePrediction() throws FileNotFoundException,IOException{
        productActualMetricsViewList= new ArrayList<>();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

        ProductForecastMetricsView forecastView = new ProductForecastMetricsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        forecastView.setTotalNumberOfExistingSubscriptions(1250);
        List<ProductForecastMetricsView> forecasts = new ArrayList<>();
        forecasts.add(forecastView);
        for (int i = 0; i < readings.length; i++) {
            ProductActualMetricsView actualMetrics = new ProductActualMetricsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
            actualMetrics.setTotalNumberOfExistingSubscriptions(readings[i][0]);
            actualMetrics.setChurnedSubscriptions(readings[i][1]);
            productActualMetricsViewList.add(actualMetrics);
        }
        List<Double> result=forecaster.forecastDemandGrowth(productActualMetricsViewList);
        System.out.println("ARIMA Precise prediction: " + (result == null ? "null" : result.get(0)));
    }

}
