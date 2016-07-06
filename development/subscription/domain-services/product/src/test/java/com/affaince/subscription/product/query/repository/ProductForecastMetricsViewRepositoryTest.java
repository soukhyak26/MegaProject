package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.web.controller.TestConfig;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 02-07-2016.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class ProductForecastMetricsViewRepositoryTest {
    @Mock
    private static ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    private static final int QTY = 20;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        productForecastMetricsViewRepository.deleteAll();
        int year = 2016;
        int month = 1;
        int day = 1;
        long totalSubscriptions = 100;
        long newSubscriptions = 10;
        long churnedSubscriptions = 3;
        ProductForecastMetricsView testView = null;
        List<ProductForecastMetricsView> testViewList = new ArrayList<>();
        for (int i = 0; i < QTY; i++) {
            testView = new ProductForecastMetricsView(new ProductVersionId("1", new LocalDate(year, month, day)), new LocalDate(year, month, day + 27));
            if (month < 12) {
                month++;
            } else {
                month = 1;
                year++;
            }
            testView.setTotalNumberOfExistingSubscriptions(totalSubscriptions);
            testView.setFixedOperatingExpense(2.00);
            testView.setNewSubscriptions(newSubscriptions);
            testView.setChurnedSubscriptions(churnedSubscriptions);
            testViewList.add(testView);
            totalSubscriptions = Math.round(totalSubscriptions + totalSubscriptions * 0.2);
            newSubscriptions = Math.round(newSubscriptions + newSubscriptions * 0.3);
            churnedSubscriptions = Math.round(churnedSubscriptions + churnedSubscriptions * 0.1);
        }
        Mockito.when(productForecastMetricsViewRepository.findAll()).thenReturn(testViewList);
    }
    @Test
    public void findALLTest(){
        Iterable<ProductForecastMetricsView> forecasts= productForecastMetricsViewRepository.findAll();
        for(ProductForecastMetricsView view: forecasts){
            System.out.println("No of existing subscriptions:" + view.getTotalNumberOfExistingSubscriptions());
        }
    }
}
