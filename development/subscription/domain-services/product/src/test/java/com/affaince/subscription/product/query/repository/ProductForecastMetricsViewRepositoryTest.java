package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
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
import java.util.stream.Stream;

/**
 * Created by mandar on 02-07-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ProductForecastMetricsViewRepositoryTest {
    private static final int QTY = 20;
    @Autowired
    private ProductForecastMetricsViewRepository productForecastMetricsViewRepository;

    @Before
    public void init() throws FileNotFoundException {
        //MockitoAnnotations.initMocks(this);
        productForecastMetricsViewRepository.deleteAll();
        for (int k=0;k<=1000;k++) {
            List<ProductForecastMetricsView> productActualMetricsViewList = new ArrayList<>();

            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
            long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

            ProductForecastMetricsView forecastView = new ProductForecastMetricsView(new ProductVersionId("product" + k, new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
            forecastView.setTotalNumberOfExistingSubscriptions(1250);
            LocalDateTime localDate = new LocalDateTime(2016, 1, 1, 0, 0, 0);
            for (int i = 0; i < readings.length; i++) {
                localDate = localDate.plusDays(1);
                ProductForecastMetricsView actualMetrics = new ProductForecastMetricsView(new ProductVersionId("product" + k, localDate), new LocalDateTime(9999, 12, 31, 0, 0, 0));
                actualMetrics.setNewSubscriptions(readings[i][0]);
                actualMetrics.setChurnedSubscriptions(readings[i][1]);
                productActualMetricsViewList.add(actualMetrics);
                //productForecastMetricsViewRepository.save(actualMetrics);
            }
            productForecastMetricsViewRepository.save(productActualMetricsViewList);
        }
        //Mockito.when(productForecastMetricsViewRepository.findAll()).thenReturn(testViewList);
    }
    @Test
    public void findALLTest(){
        Iterable<ProductForecastMetricsView> forecasts= productForecastMetricsViewRepository.findAll();
        for(ProductForecastMetricsView view: forecasts){
            System.out.println("No of existing subscriptions:" + view.getTotalNumberOfExistingSubscriptions());
        }
    }
}
