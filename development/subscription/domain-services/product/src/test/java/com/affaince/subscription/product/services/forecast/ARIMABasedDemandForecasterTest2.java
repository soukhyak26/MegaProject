package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.service.forecast.ARIMABasedDemandForecaster;
import com.affaince.subscription.common.service.forecast.config.HistoryMaxSizeConstraints;
import com.affaince.subscription.common.service.forecast.config.HistoryMinSizeConstraints;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.apache.spark.SparkContext;
import org.joda.time.LocalDate;
import org.junit.After;
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
@ContextConfiguration(classes = {Application.class})
public class ARIMABasedDemandForecasterTest2 {
    @Autowired
    ARIMABasedDemandForecaster forecaster;
    @Autowired
    private HistoryMinSizeConstraints historyMinSizeConstraints;
    @Autowired
    private HistoryMaxSizeConstraints historyMaxSizeConstraints;

    private List<ProductActualsView> productActualsViewList;

    @Autowired
    SparkContext sparkContext;
    @Before
    public void setUp() {
        sparkContext.getOrCreate();
    }

    @Test
    public void testPrecisePrediction() throws IOException {
        productActualsViewList = new ArrayList<>();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);
        ProductVersionId productVersionId = new ProductVersionId("1", new LocalDate(2016, 1, 1));
        long totalSubscriptions = 0;
        for (int i = 0; i < readings.length; i++) {
            totalSubscriptions = readings[i][0];
            ProductActualsView actualsView = new ProductActualsView(productVersionId, productVersionId.getFromDate(), readings[i][0], readings[i][1], totalSubscriptions);
            productVersionId.setFromDate(productVersionId.getFromDate().plusDays(1));
            //actualsView.setTotalNumberOfExistingSubscriptions(readings[i][0]);
            System.out.println("total subscription:" + readings[i][0]);
            System.out.println("churned subscription:" + readings[i][1]);
            productActualsViewList.add(actualsView);
        }
        // List<Double> historicalDailySubscriptionCountList = productActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<DataFrameVO> dataFrames = new ArrayList<>();
        for (ProductActualsView view : productActualsViewList) {
            DataFrameVO vo = new DataFrameVO(view.getEndDate(), "totalsubscriptioncount", view.getTotalNumberOfExistingSubscriptions());
            dataFrames.add(vo);
        }
        forecaster.setHistoryMinSizeConstraints(this.historyMinSizeConstraints);
        forecaster.setHistoryMaxSizeConstraints(this.historyMaxSizeConstraints);
        List<DataFrameVO> result = forecaster.forecast(productVersionId.getProductId(), dataFrames);
    }

    @After
    public void shutdown(){
        sparkContext.stop();
    }

}
