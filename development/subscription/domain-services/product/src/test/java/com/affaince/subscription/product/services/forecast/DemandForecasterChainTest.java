package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.configuration.Axon;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.query.view.ProductView;
import org.joda.time.LocalDateTime;
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
//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class DemandForecasterChainTest {
    @Autowired
    Axon.HistoryMinSizeConstraints minSizeConstraints;
    @Autowired
    Axon.HistoryMaxSizeConstraints maxSizeConstraints;
    @Mock
    private ProductViewRepository productViewRepository;
    @Mock
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;
    @Mock
    private ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    @InjectMocks
    @Autowired
    private DemandForecasterChain chain;

    @Before
    public void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        //chain = new DemandForecasterChain(productForecastMetricsViewRepository, productActualMetricsViewRepository);
    }

    @Test
    public void testForecastFor4HistoricalLinearRecords() throws FileNotFoundException, IOException {

        List<ProductActualMetricsView> productActualMetricsViewList;
        productActualMetricsViewList = new ArrayList<>();
        ProductVersionId productVersionId = new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0));
        ProductForecastMetricsView forecastView = new ProductForecastMetricsView(productVersionId, new LocalDateTime(9999, 12, 31, 0, 0, 0));
        forecastView.setTotalNumberOfExistingSubscriptions(1250);
        List<ProductForecastMetricsView> forecasts = new ArrayList<>();
        forecasts.add(forecastView);

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        try {
            long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

            for (int i = 0; i < 4; i++) {
                ProductActualMetricsView actualMetrics = new ProductActualMetricsView(productVersionId, new LocalDateTime(9999, 12, 31, 0, 0, 0));
                //actualMetrics.setTotalNumberOfExistingSubscriptions(readings[i][0]);
                actualMetrics.setNewSubscriptions(readings[i][0]);
                actualMetrics.setChurnedSubscriptions(readings[i][1]);
                productActualMetricsViewList.add(actualMetrics);
            }

            String productId = "1";
            ProductView product = new ProductView(productId, "Myproduct", "MyCat", "MySubCat", 100, QuantityUnit.GM, null, null, null);
            List<ProductView> allProducts = new ArrayList<ProductView>();
            allProducts.add(product);

            Mockito.when(productViewRepository.findAll()).thenReturn(allProducts);
            Mockito.when(productForecastMetricsViewRepository.findByProductVersionId_ProductId(product.getProductId(), new Sort(Sort.Direction.DESC, "productVersionId.fromDate"))).thenReturn(forecasts);
            Mockito.when(productActualMetricsViewRepository.findByProductVersionId_ProductId(product.getProductId())).thenReturn(productActualMetricsViewList);

            List<Double> historicalDailySubscriptionCountList = productActualMetricsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
            chain.forecast(productVersionId.getProductId(), historicalDailySubscriptionCountList);
        } finally {
            fileReader.close();
        }
    }

    @Test
    public void testForecastFor40HistoricalLinearRecords() throws FileNotFoundException, IOException {
        List<ProductActualMetricsView> productActualMetricsViewList;
        productActualMetricsViewList = new ArrayList<>();
        ProductVersionId productVersionId = new ProductVersionId("1", new LocalDateTime(2016, 1, 1, 0, 0, 0));
        ProductForecastMetricsView forecastView = new ProductForecastMetricsView(productVersionId, new LocalDateTime(9999, 12, 31, 0, 0, 0));
        forecastView.setTotalNumberOfExistingSubscriptions(1250);
        List<ProductForecastMetricsView> forecasts = new ArrayList<>();
        forecasts.add(forecastView);

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
        try {
            long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

            for (int i = 0; i < readings.length; i++) {
                ProductActualMetricsView actualMetrics = new ProductActualMetricsView(productVersionId, new LocalDateTime(9999, 12, 31, 0, 0, 0));
                //actualMetrics.setTotalNumberOfExistingSubscriptions(readings[i][0]);
                actualMetrics.setNewSubscriptions(readings[i][0]);
                actualMetrics.setChurnedSubscriptions(readings[i][1]);
                productActualMetricsViewList.add(actualMetrics);
            }

            String productId = "1";
            ProductView product = new ProductView(productId, "Myproduct", "MyCat", "MySubCat", 100, QuantityUnit.GM, null, null, null);
            List<ProductView> allProducts = new ArrayList<ProductView>();
            allProducts.add(product);

            Mockito.when(productViewRepository.findAll()).thenReturn(allProducts);
            Mockito.when(productForecastMetricsViewRepository.findByProductVersionId_ProductId(product.getProductId(), new Sort(Sort.Direction.DESC, "productVersionId.fromDate"))).thenReturn(forecasts);
            Mockito.when(productActualMetricsViewRepository.findByProductVersionId_ProductId(product.getProductId())).thenReturn(productActualMetricsViewList);
            List<Double> historicalDailySubscriptionCountList = productActualMetricsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
            chain.forecast(productVersionId.getProductId(), historicalDailySubscriptionCountList);
        } finally {
            fileReader.close();
        }

    }


}
