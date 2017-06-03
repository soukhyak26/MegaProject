package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.service.forecast.DemandForecasterChain;
import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.query.view.ProductView;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mandar on 24-06-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class DemandForecasterChainTest {
    @Mock
    private ProductViewRepository productViewRepository;
    @Mock
    private ProductActualsViewRepository productActualsViewRepository;
    @Mock
    private ProductForecastViewRepository productForecastViewRepository;
    //@InjectMocks
    @Autowired
    private DemandForecasterChain chain;

    @Before
    public void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        //chain = new DemandForecasterChain(productForecastMetricsViewRepository, productActualMetricsViewRepository);
    }

    @Test
    public void testForecastFor4HistoricalLinearRecords() throws IOException {

        List<ProductActualsView> productActualsViewList;
        productActualsViewList = new ArrayList<>();
        ProductVersionId productVersionId = new ProductVersionId("1", new LocalDate(2016, 1, 1));
        ProductForecastView forecastView = new ProductForecastView(productVersionId, new LocalDate(9999, 12, 31),1250,0,1250);
        //forecastView.setTotalNumberOfExistingSubscriptions(1250);
        List<ProductForecastView> forecasts = new ArrayList<>();
        forecasts.add(forecastView);

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        try {
            long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);
            long earlierTotalSubscriptionCount= 1250;
            for (int i = 0; i < 4; i++) {
                long totalSubscriptionCount =earlierTotalSubscriptionCount + readings[i][0] - readings[i][1] ;
                ProductActualsView actuals = new ProductActualsView(productVersionId, new LocalDate(9999, 12, 31),readings[i][0],readings[i][1],totalSubscriptionCount);
                //actualMetrics.setTotalNumberOfExistingSubscriptions(readings[i][0]);
/*
                actualMetrics.setNewSubscriptions(readings[i][0]);
                actualMetrics.setChurnedSubscriptions(readings[i][1]);
*/
                productActualsViewList.add(actuals);
                System.out.println("historicalData: " + readings[i][0] + ":" + readings[i][1]);
                earlierTotalSubscriptionCount=totalSubscriptionCount;
            }

            String productId = "1";
            ProductView product = new ProductView(productId, "Myproduct", "MyCat", "MySubCat", 100, QuantityUnit.GM, null, null, null);
            List<ProductView> allProducts = new ArrayList<ProductView>();
            allProducts.add(product);

            Mockito.when(productViewRepository.findAll()).thenReturn(allProducts);
            Mockito.when(productForecastViewRepository.findByProductVersionId_ProductId(product.getProductId(), new Sort(Sort.Direction.DESC, "productVersionId.fromDate"))).thenReturn(forecasts);
            Mockito.when(productActualsViewRepository.findByProductVersionId_ProductId(product.getProductId())).thenReturn(productActualsViewList);

            List<Double> historicalDailySubscriptionCountList = productActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
            List<Double> forecast = chain.forecast(productVersionId.getProductId(), historicalDailySubscriptionCountList, null, historicalDailySubscriptionCountList.size() / 2);
            for (Double forecastValue : forecast) {
                System.out.println("::::forecast::::" + forecastValue);
            }
        } finally {
            fileReader.close();
        }
    }

    @Test
    public void testForecastFor40HistoricalLinearRecords() throws IOException {
        List<ProductActualsView> productActualsViewList;
        productActualsViewList = new ArrayList<>();
        ProductVersionId productVersionId = new ProductVersionId("1", new LocalDate(2016, 1, 1));
        ProductForecastView forecastView = new ProductForecastView(productVersionId, new LocalDate(9999, 12, 31),1250,0,1250);
        //forecastView.setTotalNumberOfExistingSubscriptions(1250);
        List<ProductForecastView> forecasts = new ArrayList<>();
        forecasts.add(forecastView);

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        try {
            long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);
            long totalSubscriptionCount=0;
            for (int i = 0; i < readings.length; i++) {
                totalSubscriptionCount = totalSubscriptionCount + readings[i][0] - readings[i][1];
                ProductActualsView actuals = new ProductActualsView(productVersionId, new LocalDate(9999, 12, 31),readings[i][0], readings[i][1],totalSubscriptionCount);
                //actualMetrics.setTotalNumberOfExistingSubscriptions(readings[i][0]);
/*
                actualMetrics.setNewSubscriptions(readings[i][0]);
                actualMetrics.setChurnedSubscriptions(readings[i][1]);
*/
                productActualsViewList.add(actuals);
                System.out.println("historicalData: " + readings[i][0] + ":" + readings[i][1]);
            }

            String productId = "1";
            ProductView product = new ProductView(productId, "Myproduct", "MyCat", "MySubCat", 100, QuantityUnit.GM, null, null, null);
            List<ProductView> allProducts = new ArrayList<ProductView>();
            allProducts.add(product);

            Mockito.when(productViewRepository.findAll()).thenReturn(allProducts);
            Mockito.when(productForecastViewRepository.findByProductVersionId_ProductId(product.getProductId(), new Sort(Sort.Direction.DESC, "productVersionId.fromDate"))).thenReturn(forecasts);
            Mockito.when(productActualsViewRepository.findByProductVersionId_ProductId(product.getProductId())).thenReturn(productActualsViewList);
            List<Double> historicalDailySubscriptionCountList = productActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
            List<Double> forecast = chain.forecast(productVersionId.getProductId(), historicalDailySubscriptionCountList, null, historicalDailySubscriptionCountList.size() / 2);
            for (Double forecastValue : forecast) {
                System.out.println("::::forecast::::" + forecastValue);
            }
        } finally {
            fileReader.close();
        }

    }


}
