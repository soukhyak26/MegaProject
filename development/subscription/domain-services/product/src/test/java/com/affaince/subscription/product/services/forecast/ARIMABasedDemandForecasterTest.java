package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mandar on 19-06-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Application.class})
public class ARIMABasedDemandForecasterTest {
    @Autowired
    ARIMABasedDemandForecaster forecaster;
    private List<ProductActualsView> ProductActualsViewList;

    @Before
    public void setUp(){

    }
    @Test
    public void testPrecisePrediction() throws IOException {
        ProductActualsViewList= new ArrayList<>();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);
        ProductVersionId productVersionId = new ProductVersionId("1", new LocalDate(2016, 1, 1));
        ProductForecastView forecastView = new ProductForecastView(productVersionId, new LocalDate(9999, 12, 31),1250,0,1250);
        List<ProductForecastView> forecasts = new ArrayList<>();
        forecasts.add(forecastView);
        long totalSusbcriptions=1250;
        for (int i = 0; i < readings.length; i++) {
            totalSusbcriptions=totalSusbcriptions+readings[i][0]-readings[i][1];
            ProductActualsView actualsView = new ProductActualsView(productVersionId, new LocalDate(9999, 12, 31),readings[i][0],readings[i][1],totalSusbcriptions);
            //actualsView.setTotalNumberOfExistingSubscriptions(readings[i][0]);
            ProductActualsViewList.add(actualsView);
        }
        List<Double> historicalDailySubscriptionCountList = ProductActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));

        List<Double> result = forecaster.forecast(productVersionId.getProductId(), historicalDailySubscriptionCountList);
        System.out.println("ARIMA Precise prediction: " + (result == null ? "null" : result.get(0)));
    }

}
