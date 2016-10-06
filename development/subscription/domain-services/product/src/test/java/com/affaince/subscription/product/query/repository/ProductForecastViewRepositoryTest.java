package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDateTime;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 16-08-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ProductForecastViewRepositoryTest {
    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;
    private LocalDateTime localDate;
    @Before
    public void init() throws FileNotFoundException {
        // productForecastViewRepository.deleteAll();

        localDate = new LocalDateTime(2016, 1, 1, 0, 0, 0);
        ProductForecastView productForecastView1 = new ProductForecastView(
                new ProductVersionId("1", localDate),
                localDate.plusDays(30),
                100, 20, 110, ProductForecastStatus.ACTIVE
        );
        productForecastViewRepository.save(productForecastView1);

        ProductForecastView productForecastView2 = new ProductForecastView(
                new ProductVersionId("1", localDate.plusDays(31)),
                localDate.plusDays(51),
                100, 20, 110, ProductForecastStatus.ACTIVE
        );

        productForecastViewRepository.save(productForecastView2);

        ProductForecastView productForecastView3 = new ProductForecastView(
                new ProductVersionId("1", localDate.plusDays(52)),
                localDate.plusDays(74),
                100, 20, 110, ProductForecastStatus.ACTIVE
        );

        productForecastViewRepository.save(productForecastView3);

        for (int k = 0; k <= 1000; k++) {
            List<ProductForecastView> productForecastViewList = new ArrayList<>();

            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/demands2.tsv"))));
            long[][] readings = fileReader.lines().map(l -> l.trim().split("\t")).map(sa -> Stream.of(sa).mapToLong(Long::parseLong).toArray()).toArray(long[][]::new);

/*
            ProductForecastMetricsView forecastView = new ProductForecastMetricsView(new ProductVersionId("product" + k, new LocalDateTime(2016, 1, 1, 0, 0, 0)), new LocalDateTime(9999, 12, 31, 0, 0, 0));
            forecastView.setTotalNumberOfExistingSubscriptions(1250);
*/
            //localDate = new LocalDateTime(2016, 1, 1, 0, 0, 0);
            for (int i = 0; i < readings.length; i++) {
                LocalDateTime newDate = localDate.plusDays(i + 1);
                ProductForecastView productForecastView = new ProductForecastView(new ProductVersionId("product" + k, newDate),
                        new LocalDateTime(9999, 12, 31, 0, 0, 0), readings[i][0], readings[i][1], 1000, ProductForecastStatus.ACTIVE);
                productForecastViewList.add(productForecastView);
                //productForecastMetricsViewRepository.save(actualMetrics);
            }
            productForecastViewRepository.save(productForecastViewList);
        }
    }

    @Test
    public void testFindFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc () {
        ProductForecastView productForecastView =
                productForecastViewRepository.
                        findFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc("1");
        ProductVersionId productVersionId = new ProductVersionId("1", localDate.plusDays(52));
        assertThat (productForecastView.getProductVersionId(), is (productVersionId));
    }

    @Test
    public void testFindByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc () {
        List<ProductForecastView> productForecastViewList = productForecastViewRepository.
                findByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc("1", ProductForecastStatus.ACTIVE);
        assertThat(productForecastViewList.size(), is(3));
        ProductVersionId productVersionId = new ProductVersionId("1", localDate.plusDays(52));
        assertThat (productForecastViewList.get(0).getProductVersionId(), is (productVersionId));
    }

    @After
    public void shutdown () {
        productForecastViewRepository.deleteAll();
    }
}
