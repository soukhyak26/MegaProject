package com.affaince.subscription.product.query.repository;

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
 * Created by mandar on 02-07-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ProductActualMetricsViewRepositoryTest {
    @Autowired
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;
    private static final int QTY = 20;

    @Before
    public void init() throws FileNotFoundException {
        //MockitoAnnotations.initMocks(this);
        productActualMetricsViewRepository.deleteAll();
        List <ProductActualMetricsView> productActualMetricsViewList= new ArrayList<>();

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

        ProductForecastMetricsView forecastView = new ProductForecastMetricsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        forecastView.setTotalNumberOfExistingSubscriptions(1250);
        LocalDate localDate = new LocalDate(2016, 1, 1);
        for (int i = 0; i < readings.length; i++) {
            localDate = localDate.plusDays(1);
            ProductActualMetricsView actualMetrics = new ProductActualMetricsView(new ProductVersionId("1", localDate), new LocalDate(9999, 12, 31));
            actualMetrics.setTotalNumberOfExistingSubscriptions(readings[i][0]);
            actualMetrics.setChurnedSubscriptions(readings[i][1]);
            productActualMetricsViewList.add(actualMetrics);
            //productForecastMetricsViewRepository.save(actualMetrics);
        }
        productActualMetricsViewRepository.save(productActualMetricsViewList);
        //Mockito.when(productForecastMetricsViewRepository.findAll()).thenReturn(testViewList);
    }
    @Test
    public void findALLTest(){
        Iterable<ProductActualMetricsView> forecasts= productActualMetricsViewRepository.findAll();
        for(ProductActualMetricsView view: forecasts){
            System.out.println("No of existing subscriptions:" + view.getTotalNumberOfExistingSubscriptions());
        }
    }
}
