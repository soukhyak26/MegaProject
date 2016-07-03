package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by mandar on 02-07-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMongoConfiguration.class})
public class ProductForecastMetricsViewRepositoryTest {
    @Autowired
    ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    static final int QTY = 20;

    @Before
    public void init() {
        productForecastMetricsViewRepository.deleteAll();
        int year = 2016;
        int month = 1;
        int day = 1;
        long totalSubscriptions = 100;
        long newSubscriptions = 10;
        long churnedSubscriptions = 3;
        ProductForecastMetricsView testView = null;
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
            productForecastMetricsViewRepository.save(testView);
            totalSubscriptions = Math.round(totalSubscriptions + totalSubscriptions * 0.2);
            newSubscriptions = Math.round(newSubscriptions + newSubscriptions * 0.3);
            churnedSubscriptions = Math.round(churnedSubscriptions + churnedSubscriptions * 0.1);
        }
    }
    @Test
    public void findALLTest(){
        Iterable<ProductForecastMetricsView> forecasts= productForecastMetricsViewRepository.findAll();
        for(ProductForecastMetricsView view: forecasts){
            System.out.println("No of existing subscriptions:" + view.getTotalNumberOfExistingSubscriptions());
        }
    }
}
