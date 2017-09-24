package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.Application;
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
import java.util.stream.Stream;

/**
 * Created by mandar on 02-07-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ProductForecastViewRepositoryTest2 {
    private static final int QTY = 20;
    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;

    @Before
    public void init() throws FileNotFoundException {
        //MockitoAnnotations.initMocks(this);
        productForecastViewRepository.deleteAll();
        for (int k=0;k<=1000;k++) {
            List<ProductForecastView> productActualMetricsViewList = new ArrayList<>();

            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
            long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

            ProductForecastView forecastView = new ProductForecastView(new ForecastVersionId("product" + k, new LocalDate(2016, 1, 1),SysDate.now()), new LocalDate(9999, 12, 31),1250,0,1250);
            LocalDate localDate = new LocalDate(2016, 1, 1);
            long totalSubscriptions=1250;
            for (int i = 0; i < readings.length; i++) {
                localDate = localDate.plusDays(1);
                totalSubscriptions=totalSubscriptions+readings[i][0]-readings[i][1];
                ProductForecastView actualMetrics = new ProductForecastView(new ForecastVersionId("product" + k, localDate, SysDate.now()), new LocalDate(9999, 12, 31),readings[i][0],readings[i][1],totalSubscriptions);
                productActualMetricsViewList.add(actualMetrics);
                //ProductForecastViewRepository.save(actualMetrics);
            }
            productForecastViewRepository.save(productActualMetricsViewList);
        }
        //Mockito.when(ProductForecastViewRepository.findAll()).thenReturn(testViewList);
    }
    @Test
    public void findALLTest(){
        Iterable<ProductForecastView> forecasts= productForecastViewRepository.findAll();
        for(ProductForecastView view: forecasts){
            System.out.println("No of existing subscriptions:" + view.getTotalNumberOfExistingSubscriptions());
        }
    }
}
